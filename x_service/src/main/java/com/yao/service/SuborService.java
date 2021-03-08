package com.yao.service;
/**
 * @author 妖妖
 * @date 11:23 2021/3/5
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yao.bean.model.XRoleModel;
import com.yao.bean.model.XServiceModel;
import com.yao.bean.model.tab.XServiceTab;
import com.yao.bean.pojo.XManagerPojo;
import com.yao.bean.pojo.XManagerRolePojo;
import com.yao.bean.pojo.XRolePojo;
import com.yao.bean.pojo.XServicePojo;
import com.yao.bean.vo.ResObj;
import com.yao.common.util.IdWorker;
import com.yao.common.util.MD5Util;
import com.yao.common.util.Tool;
import com.yao.dao.XManagerDao;
import com.yao.dao.XManagerRoleDao;
import com.yao.dao.XRoleDao;
import com.yao.dao.XServiceDao;
import com.yao.service.exception.CustException;
import com.yao.service.session.LoginUser;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SuborService {
    private static Log log = LogFactory.getLog(SuborService.class);

    @Autowired
    private XServiceDao xServiceDao;
    @Autowired
    private XManagerDao xManagerDao;
    @Autowired
    private XRoleDao xRoleDao;
    @Autowired
    private XManagerRoleDao xManagerRoleDao;
    @Autowired
    private IdWorker idWorker;


    public ResObj suborData(XServiceTab tab) {
        String id = tab.getId();
        String name = tab.getName();
        String realName = tab.getRealName();
        String mobileNo = tab.getMobileNo();
        String state = tab.getState();
        XServicePojo sel = new XServicePojo();
        if (StringUtils.isBlank(state))
            sel.setInState(Arrays.asList(new String[]{"1", "0"}));
        else
            sel.setState(state);
        if (StringUtils.isNotBlank(id))
            sel.setId(id);
        if (StringUtils.isNotBlank(name))
            sel.setName(name);
        if (StringUtils.isNotBlank(realName))
            sel.setRealName(realName);
        if (StringUtils.isNotBlank(mobileNo))
            sel.setMobileNo(mobileNo);

        if (StringUtils.isNotBlank(tab.getOrder()))
            PageHelper.orderBy(Tool.humpToLine(tab.getSort().replaceAll("Name","Id"))+" "+tab.getOrder());
        else
            PageHelper.orderBy("id desc");
        Page<XServicePojo> page = PageHelper.startPage(tab.getPage(),tab.getLimit(),true);
        xServiceDao.getRecordListByWhere(sel);
        List<XServiceModel> models = new ArrayList<>();
        for (XServicePojo pojo : page.getResult()) {
            XServiceModel model = new XServiceModel(pojo);
            String creOperId = model.getCreOperId();
            String lastOperId = model.getLastOperId();
            if (StringUtils.isNotBlank(creOperId)){
                model.setCreOperName(xManagerDao.getRecordByKey(new XManagerPojo().setId(creOperId)).getNickname());
            }
            if (StringUtils.isNotBlank(lastOperId)){
                model.setLastOperName(xManagerDao.getRecordByKey(new XManagerPojo().setId(lastOperId)).getNickname());
            }
            models.add(model);
        }
        return new ResObj().setState(true).setCount(page.getTotal()).setData(models);
    }

    public ResObj addData() {
        String serviceId = LoginUser.getServiceId();
        List<XRolePojo> roles = xRoleDao.getRecordListByWhere(new XRolePojo().setServiceId(serviceId).setState("1"));
        List<XRoleModel> models = new ArrayList<>();
        for (XRolePojo role : roles) {
            models.add(new XRoleModel(role));
        }
        return new ResObj().setState(true).setData(models);
    }

    public void add(XServiceModel model) {
        String name = model.getName();
        String realName = model.getRealName();
        String mobileNo = model.getMobileNo();
        String password = model.getPassword();
        List<String> roleIds = model.getRoleIds();
        if (StringUtils.isBlank(name))
            throw new CustException("名称不能空");
        if (StringUtils.isBlank(realName))
            throw new CustException("姓名不能空");
        if (StringUtils.isBlank(mobileNo) || mobileNo.length() > 11)
            throw new CustException("手机号不能空并且长度不能小于11");
        if (StringUtils.isBlank(password) || password.length() < 6)
            throw new CustException("密码不能空并且长度不能小于6");
        if (roleIds == null || roleIds.size() == 0)
            throw new CustException("角色至少选择一个");
        String serviceId = LoginUser.getServiceId();
        String managerId = LoginUser.getId();
        for (String roleId : roleIds) {
            if (xRoleDao.getRecordByWhere(new XRolePojo().setId(roleId).setState("1").setServiceId(serviceId)) == null)
                throw new CustException("角色不存在或已删除");
        }
        String sid = idWorker.nextId();
        String mid = idWorker.nextId();
        xServiceDao.insertRecord(new XServicePojo().setId(sid).setName(name).setRealName(realName)
                .setMobileNo(mobileNo).setState("1").setRegDate(new Date()).setCreOperId(managerId).setCreOperDate(new Date()));
        xManagerDao.insertRecord(new XManagerPojo().setId(mid).setServiceId(sid).setNickname(name)
                .setMobileNo(mobileNo).setPassword(MD5Util.getMD5Str(password)).setRegDate(new Date())
                .setState("1").setDefaults("1"));
        for (String roleId : roleIds) {
            xManagerRoleDao.insertRecord(new XManagerRolePojo().setManagerId(mid).setRoleId(roleId).setCreOperId(managerId).setCreOperDate(new Date()));
        }
    }

    public ResObj modifyData(XServiceModel model) {
        String id = model.getId();
        if (StringUtils.isBlank(id))
            throw new CustException("参数异常");
        XServicePojo service = xServiceDao.getRecordByWhere(new XServicePojo().setId(id).setInState(Arrays.asList(new String[]{"1", "0"})));
        if (service == null)
            throw new CustException("数据不存在或已删除");
        XServiceModel serviceModel = new XServiceModel(service);

        List<String> roleIds = xManagerRoleDao.getRoleIdListByWhere(new XManagerRolePojo().setManagerId(xManagerDao.getRecordByWhere(new XManagerPojo().setDefaults("1").setServiceId(id)).getId()));

        String serviceId = LoginUser.getServiceId();
        List<XRolePojo> roles = xRoleDao.getRecordListByWhere(new XRolePojo().setServiceId(serviceId).setState("1"));
        List<XRoleModel> models = new ArrayList<>();
        for (XRolePojo role : roles) {
            if (roleIds.contains(role.getId())) {
                models.add(new XRoleModel(role).setChecked(true));
            } else {
                models.add(new XRoleModel(role).setChecked(false));
            }
        }
        serviceModel.setRoles(models);
        return new ResObj().setState(true).setData(serviceModel);
    }

    public void modify(XServiceModel model) {
        String id = model.getId();
        String name = model.getName();
        String realName = model.getRealName();
        String mobileNo = model.getMobileNo();
        List<String> roleIds = model.getRoleIds();

        if (StringUtils.isBlank(id))
            throw new CustException("参数异常");
        if (StringUtils.isBlank(name))
            throw new CustException("服务商名称不能空");
        if (StringUtils.isBlank(realName))
            throw new CustException("姓名不能空");
        if (StringUtils.isBlank(mobileNo) || mobileNo.length() > 11)
            throw new CustException("手机号不能空并且长度不能大于11");
        if (roleIds == null || roleIds.size() == 0)
            throw new CustException("角色至少选择一个");

        if (xServiceDao.getRecordByWhere(new XServicePojo().setId(id).setInState(Arrays.asList(new String[]{"1", "0"}))) == null){
            throw new CustException("修改的数据不存在或已删除");
        }
        xServiceDao.updateRecordByKey(new XServicePojo().setId(id).setName(name).setRealName(realName).setMobileNo(mobileNo).setLastOperId(LoginUser.getId()).setLastOperDate(new Date()));
        String mId = xManagerDao.getRecordByWhere(new XManagerPojo().setServiceId(id).setDefaults("1")).getId();
        xManagerRoleDao.deleteRecordByKey(new XManagerRolePojo().setManagerId(mId));
        for (String roleId : roleIds) {
            xManagerRoleDao.insertRecord(new XManagerRolePojo().setManagerId(mId).setRoleId(roleId).setCreOperDate(new Date()).setCreOperId(LoginUser.getId()));
        }

    }

    public void upState(XServiceModel model) {
        String id = model.getId();
        String state = model.getState();
        if (StringUtils.isBlank(id))
            throw new CustException("数据异常");
        if (StringUtils.isBlank(state))
            throw new CustException("参数异常");
        if (xServiceDao.getRecordByWhere(new XServicePojo().setId(id).setInState(Arrays.asList(new String[]{"1", "0"}))) == null)
            throw new CustException("数据已删除或不存在");
        xServiceDao.updateRecordByKey(new XServicePojo().setId(id).setState(state).setLastOperDate(new Date()).setLastOperId(LoginUser.getId()));
        xManagerDao.updateRecordByKey(new XManagerPojo().setId(
                xServiceDao.getRecordByKey(new XServicePojo().setId(id)).getId()).setState(state).setLastOperId(LoginUser.getId()).setLastOperDate(new Date()));
    }

    public void resetPass(XServiceModel model) {
        String id = model.getId();
        if (StringUtils.isBlank(id))
            throw new CustException("数据异常");
        XServicePojo service = xServiceDao.getRecordByWhere(new XServicePojo().setId(id).setInState(Arrays.asList(new String[]{"1", "0"})));
        if (service == null)
            throw new CustException("数据不存在或已删除");
        XManagerPojo manager = xManagerDao.getRecordByWhere(new XManagerPojo().setServiceId(service.getId()).setInState(Arrays.asList(new String[]{"1", "0"})).setDefaults("1"));
        xManagerDao.updateRecordByKey(new XManagerPojo().setId(manager.getId()).setPassword(MD5Util.getMD5Str("qaz123")).setLastOperDate(new Date()).setLastOperId(LoginUser.getId()));
    }
}
