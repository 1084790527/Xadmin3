package com.yao.service;
/**
 * @author 妖妖
 * @date 14:42 2021/3/5
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yao.bean.LoginInfo;
import com.yao.bean.RoleTreeBean;
import com.yao.bean.model.XRoleModel;
import com.yao.bean.model.tab.XRoleTab;
import com.yao.bean.pojo.XManagerPojo;
import com.yao.bean.pojo.XPrivilegesPojo;
import com.yao.bean.pojo.XRolePojo;
import com.yao.bean.pojo.XRolePrivilegesPojo;
import com.yao.bean.vo.ResObj;
import com.yao.common.Consts;
import com.yao.common.util.IdWorker;
import com.yao.common.util.Tool;
import com.yao.dao.XManagerDao;
import com.yao.dao.XRoleDao;
import com.yao.dao.XRolePrivilegesDao;
import com.yao.service.exception.CustException;
import com.yao.service.session.LoginUser;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoleService {
    private static Log log = LogFactory.getLog(RoleService.class);

    @Autowired
    private XRoleDao xRoleDao;
    @Autowired
    private XManagerDao xManagerDao;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private XRolePrivilegesDao xRolePrivilegesDao;

    public ResObj roleData(XRoleTab tab) {
        LoginInfo loginInfo = LoginUser.getLoginInfo();
        String id = tab.getId();
        String name = tab.getName();
        String state = tab.getState();
        XRolePojo sel = new XRolePojo();
        sel.setServiceId(loginInfo.getServiceId());
        if (StringUtils.isNotBlank(state))
            sel.setState(state);
        else
            sel.setInState(Arrays.asList(new String[]{"1", "0"}));
        if (StringUtils.isNotBlank(id))
            sel.setId(id);
        if (StringUtils.isNotBlank(name))
            sel.setName(name);
        if (StringUtils.isNotBlank(tab.getOrder()))
            PageHelper.orderBy(Tool.humpToLine(tab.getSort().replaceAll("Name","Id"))+" "+tab.getOrder());
        else
            PageHelper.orderBy("id desc");
        Page<XRolePojo> page = PageHelper.startPage(tab.getPage(),tab.getLimit(),true);
        xRoleDao.getRecordListByWhere(sel);
        List<XRoleModel> models = new ArrayList<>();
        for (XRolePojo pojo : page.getResult()) {
            XRoleModel model = new XRoleModel(pojo);
            String creOperId = model.getCreOperId();
            if (StringUtils.isNotBlank(creOperId))
                model.setCreOperName(xManagerDao.getRecordByKey(new XManagerPojo().setId(creOperId)).getNickname());
            String lastOperId = model.getLastOperId();
            if (StringUtils.isNotBlank(lastOperId))
                model.setLastOperName(xManagerDao.getRecordByKey(new XManagerPojo().setId(lastOperId)).getNickname());
            models.add(model);
        }
        return new ResObj().setState(true).setData(models).setCount(page.getTotal());
    }

    public ResObj addData() {
        List<XPrivilegesPojo> privileges = authorityService.getTreeMenuList(false);
        List<RoleTreeBean> roleTrees = new ArrayList<>();
        authorityService.treePriToRoleBean(roleTrees,privileges);
        return new ResObj().setState(true).setData(roleTrees);
    }

    public void add(XRoleModel model) {
        String description = model.getDescription();
        String name = model.getName();
        List<String> priIds = model.getPriIds();
        if (StringUtils.isBlank(name))
            throw new CustException("角色名称不能空");
        if (StringUtils.isBlank(description))
            throw new CustException("角色描述不能空");
        if (priIds == null || priIds.size() == 0)
            throw new CustException("角色权限至少选择一个");
        String creOpenId = LoginUser.getId();
        String serviceId = LoginUser.getServiceId();

        String roleId = idWorker.nextId();
        xRoleDao.insertRecord(new XRolePojo().setId(roleId).setName(name).setServiceId(serviceId)
                .setDescription(description).setState("1").setDefaults("0").setCreOperId(creOpenId).setCreOperDate(new Date()));
        for (String priId : priIds) {
            xRolePrivilegesDao.insertRecord(new XRolePrivilegesPojo().setRoleId(roleId).setPrivilegesId(priId).setCreOperId(creOpenId).setCreOperDate(new Date()));
        }
    }

    public ResObj modifyData(XRoleModel model) {
        String id = model.getId();
        if (StringUtils.isBlank(id))
            throw new CustException("数据异常");
        XRolePojo role = xRoleDao.getRecordByWhere(new XRolePojo().setId(id).setInState(Arrays.asList(new String[]{"1", "0"})).setServiceId(LoginUser.getServiceId()));
        if (role == null)
            throw new CustException("修改的数据不存在或者已删除");
        if (!"0".equals(role.getDefaults()))
            throw new CustException("系统默认不可修改");
        List<XPrivilegesPojo> privileges = authorityService.getTreeMenuList(false);
        List<RoleTreeBean> roleTrees = new ArrayList<>();
        authorityService.treePriToRoleBean(roleTrees,privileges);
        List<String> priIds = authorityService.getPriEndIds(id);
        XRoleModel xRole = new XRoleModel();
        xRole.setName(role.getName());
        xRole.setDescription(role.getDescription());
        xRole.setRoleTrees(roleTrees);
        xRole.setPriIds(priIds);
        return new ResObj().setState(true).setData(xRole);
    }

    public void modify(XRoleModel model) {
        String id = model.getId();
        String name = model.getName();
        String description = model.getDescription();
        List<String> priIds = model.getPriIds();
        if (StringUtils.isBlank(id))
            throw new CustException("数据异常");
        if (StringUtils.isBlank(name))
            throw new CustException("角色名称不能空");
        if (StringUtils.isBlank(description))
            throw new CustException("角色描述不能空");
        if (priIds == null || priIds.size() == 0)
            throw new CustException("角色权限至少选择一个");
        String serviceId = LoginUser.getServiceId();
        String managerId = LoginUser.getId();
        XRolePojo role = xRoleDao.getRecordByWhere(new XRolePojo().setId(id).setServiceId(serviceId).setInState(Arrays.asList(new String[]{"1", "0"})).setDefaults("0"));
        if (role == null)
            throw new CustException("角色状态异常");
        xRoleDao.updateRecordByKey(new XRolePojo().setId(id).setName(name).setDescription(description));
        xRolePrivilegesDao.deleteRecordByKey(new XRolePrivilegesPojo().setRoleId(id));
        for (String priId : priIds) {
            xRolePrivilegesDao.insertRecord(new XRolePrivilegesPojo().setRoleId(id).setPrivilegesId(priId).setCreOperId(managerId).setCreOperDate(new Date()));
        }

    }

    public void upState(XRoleModel model) {
        String id = model.getId();
        String state = model.getState();
        if (StringUtils.isBlank(id))
            throw new CustException("数据异常");
        if (StringUtils.isBlank(state))
            throw new CustException("操作异常");
        String managerId = LoginUser.getId();
        String serviceId = LoginUser.getServiceId();
        if (xRoleDao.getRecordByWhere(new XRolePojo().setId(id).setServiceId(serviceId).setInState(Arrays.asList(new String[]{"1", "0"}))) == null)
            throw new CustException("操作的数据不存在");
        xRoleDao.updateRecordByKey(new XRolePojo().setId(id).setState(state).setLastOperDate(new Date()).setLastOperId(managerId));
        if ("2".equals(state)){
            xRolePrivilegesDao.deleteRecordByKey(new XRolePrivilegesPojo().setRoleId(id));
        }
    }
}
