package com.yao.service;
/**
 * @author 妖妖
 * @date 15:01 2021/3/6
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yao.bean.model.XManagerModel;
import com.yao.bean.model.XRoleModel;
import com.yao.bean.model.tab.XManagerTab;
import com.yao.bean.pojo.XManagerPojo;
import com.yao.bean.pojo.XManagerRolePojo;
import com.yao.bean.pojo.XRolePojo;
import com.yao.bean.vo.ResObj;
import com.yao.common.util.IdWorker;
import com.yao.common.util.MD5Util;
import com.yao.common.util.Tool;
import com.yao.dao.XManagerDao;
import com.yao.dao.XManagerRoleDao;
import com.yao.dao.XRoleDao;
import com.yao.dao.XRolePrivilegesDao;
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
public class ManagerService {
    private static Log log = LogFactory.getLog(ManagerService.class);

    @Autowired
    private XManagerDao xManagerDao;
    @Autowired
    private XRoleDao xRoleDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private XManagerRoleDao xManagerRoleDao;

    public ResObj managerData(XManagerTab tab) {
        String id = tab.getId();
        String nickname = tab.getNickname();
        String mobileNo = tab.getMobileNo();
        String state = tab.getState();
        XManagerPojo sel = new XManagerPojo();
        if (StringUtils.isNotBlank(id))
            sel.setId(id);
        if (StringUtils.isNotBlank(nickname))
            sel.setNickname(nickname);
        if (StringUtils.isNotBlank(mobileNo))
            sel.setMobileNo(mobileNo);
        if (StringUtils.isNotBlank(state))
            sel.setState(state);
        else
            sel.setInState(Arrays.asList(new String[]{"1", "0"}));
        if (StringUtils.isNotBlank(tab.getOrder()))
            PageHelper.orderBy(Tool.humpToLine(tab.getSort().replaceAll("Name","Id"))+" "+tab.getOrder());
        else
            PageHelper.orderBy("id desc");
        Page<XManagerPojo> page = PageHelper.startPage(tab.getPage(),tab.getLimit(),true);
        xManagerDao.getRecordListByWhere(sel);
        List<XManagerModel> models = new ArrayList<>();
        for (XManagerPojo pojo : page.getResult()) {
            XManagerModel model = new XManagerModel(pojo);
            String lastOperId = pojo.getLastOperId();
            if (StringUtils.isNotBlank(lastOperId))
                model.setLastOperName(xManagerDao.getRecordByKey(new XManagerPojo().setId(lastOperId)).getNickname());
            models.add(model);
        }
        return new ResObj().setState(true).setCount(page.getTotal()).setData(models);
    }

    public ResObj addData() {
        List<XRolePojo> roles = xRoleDao.getRecordListByWhere(new XRolePojo().setState("1").setServiceId(LoginUser.getServiceId()));
        return new ResObj().setState(true).setData(roles);
    }

    public void add(XManagerModel model) {
        String nickname = model.getNickname();
        String mobileNo = model.getMobileNo();
        String password = model.getPassword();
        List<String> roleIds = model.getRoleIds();
        if (StringUtils.isBlank(nickname))
            throw new CustException("昵称不能空");
        if (StringUtils.isBlank(mobileNo) || mobileNo.length() > 11)
            throw new CustException("手机号不能空并且长度不能大于11");
        if (StringUtils.isBlank(password) || password.length() < 6)
            throw new CustException("密码不能空并且长度不能小于6");
        if (roleIds == null || roleIds.size() == 0)
            throw new CustException("角色必须选择");
        String managerId = LoginUser.getId();
        String serviceId = LoginUser.getServiceId();
        for (String roleId : roleIds) {
            if (xRoleDao.getRecordByWhere(new XRolePojo().setId(roleId).setState("1").setServiceId(serviceId)) == null)
                throw new CustException("选择的角色不存在或者已删除");
        }
        String id = idWorker.nextId();
        xManagerDao.insertRecord(new XManagerPojo().setId(id).setServiceId(serviceId).setNickname(nickname)
                .setMobileNo(mobileNo).setPassword(MD5Util.getMD5Str(password)).setRegDate(new Date()).setState("1").setDefaults("0"));
        for (String roleId : roleIds) {
            xManagerRoleDao.insertRecord(new XManagerRolePojo().setManagerId(id).setRoleId(roleId).setCreOperId(managerId).setCreOperDate(new Date()));
        }

    }

    public ResObj modifyData(XManagerModel model) {
        String id = model.getId();
        if (StringUtils.isBlank(id))
            throw new CustException("修改异常");
        String service = LoginUser.getServiceId();
        XManagerPojo manager = xManagerDao.getRecordByWhere(new XManagerPojo().setId(id).setInState(Arrays.asList(new String[]{"1", "0"})).setServiceId(service).setDefaults("0"));
        if (manager == null)
            throw new CustException("修改的数据不存在或者已删除");
        XManagerModel retModel = new XManagerModel(manager);
        List<XRolePojo> roles = xRoleDao.getRecordListByWhere(new XRolePojo().setState("1").setServiceId(LoginUser.getServiceId()));
        List<String> roleIds = xManagerRoleDao.getRoleIdListByWhere(new XManagerRolePojo().setManagerId(id));
        List<XRoleModel> xRoleModels = new ArrayList<>();
        for (XRolePojo role : roles) {
            if (roleIds.contains(role.getId()))
                xRoleModels.add(new XRoleModel(role).setChecked(true));
            else
                xRoleModels.add(new XRoleModel(role).setChecked(false));
        }
        retModel.setRoles(xRoleModels);
        return new ResObj().setState(true).setData(retModel);
    }

    public void modify(XManagerModel model) {
        String nickname = model.getNickname();
        String mobileNo = model.getMobileNo();
        List<String> roleIds = model.getRoleIds();
        String id = model.getId();
        if (StringUtils.isBlank(id))
            throw new CustException("修改异常");
        if (StringUtils.isBlank(nickname))
            throw new CustException("昵称不能空");
        if (StringUtils.isBlank(mobileNo) || mobileNo.length() > 11)
            throw new CustException("手机号不能空并且长度不能大于11");
        if (roleIds == null || roleIds.size() == 0)
            throw new CustException("角色必须选择");

        String managerId = LoginUser.getId();
        String serviceId = LoginUser.getServiceId();
        XManagerPojo manager = xManagerDao.getRecordByWhere(new XManagerPojo().setId(id).setInState(Arrays.asList(new String[]{"1", "0"})).setServiceId(serviceId).setDefaults("0"));
        if (manager == null)
            throw new CustException("修改的数据不存在或者已删除");
        for (String roleId : roleIds) {
            if (xRoleDao.getRecordByWhere(new XRolePojo().setId(roleId).setState("1").setServiceId(serviceId)) == null)
                throw new CustException("选择的角色不存在或者已删除");
        }
        xManagerDao.updateRecordByKey(new XManagerPojo().setId(id).setNickname(nickname).setMobileNo(mobileNo).setLastLoginDate(new Date()).setLastOperId(managerId));
        xManagerRoleDao.deleteRecordByKey(new XManagerRolePojo().setManagerId(id));
        for (String roleId : roleIds) {
            xManagerRoleDao.insertRecord(new XManagerRolePojo().setManagerId(id).setRoleId(roleId).setCreOperDate(new Date()).setCreOperId(managerId));
        }

    }

    public void upState(XManagerModel model) {
        String id = model.getId();
        String state = model.getState();
        if (StringUtils.isBlank(id))
            throw new CustException("数据异常");
        if (StringUtils.isBlank(state))
            throw new CustException("参数异常");
        String managerId = LoginUser.getId();
        String serviceId = LoginUser.getServiceId();
        if (xManagerDao.getRecordByWhere(new XManagerPojo().setId(id).setInState(Arrays.asList(new String[]{"0", "1"})).setServiceId(serviceId)) == null)
            throw new CustException("数据不存在或已删除");
        xManagerDao.updateRecordByKey(new XManagerPojo().setId(id).setState(state).setLastOperId(managerId).setLastOperDate(new Date()));
        if ("2".equals(state))
            xManagerRoleDao.deleteRecordByKey(new XManagerRolePojo().setManagerId(id));
    }

    public void resetPass(XManagerModel model) {
        String id = model.getId();
        if (StringUtils.isBlank(id))
            throw new CustException("数据异常");
        String managerId = LoginUser.getId();
        String serviceId = LoginUser.getServiceId();
        if (xManagerDao.getRecordByWhere(new XManagerPojo().setId(id).setInState(Arrays.asList(new String[]{"0", "1"})).setServiceId(serviceId)) == null)
            throw new CustException("数据不存在或已删除");
        xManagerDao.updateRecordByKey(new XManagerPojo().setId(id).setPassword(MD5Util.getMD5Str("qaz123")).setLastOperDate(new Date()).setLastOperId(managerId));
    }
}
