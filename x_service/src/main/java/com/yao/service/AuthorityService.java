package com.yao.service;
/**
 * @author 妖妖
 * @date 14:09 2021/3/3
 */

import com.alibaba.fastjson.JSON;
import com.yao.bean.LoginInfo;
import com.yao.bean.RoleTreeBean;
import com.yao.bean.pojo.*;
import com.yao.bean.vo.ResObj;
import com.yao.dao.*;
import com.yao.service.session.LoginUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthorityService {
    private static Log log = LogFactory.getLog(AuthorityService.class);

    @Autowired
    private XManagerDao xManagerDao;
    @Autowired
    private XManagerRoleDao xManagerRoleDao;
    @Autowired
    private XPrivilegesDao xPrivilegesDao;
    @Autowired
    private XRoleDao xRoleDao;
    @Autowired
    private XRolePrivilegesDao xRolePrivilegesDao;

    public void menuExclusion(List<XPrivilegesPojo> privileges,List<Long> priIds,boolean b){
        List<XPrivilegesPojo> removes = new ArrayList<>();
        privileges.forEach(privilege -> {
            if (b){
                if (priIds.contains(privilege.getId()) && privilege.getPermissionType() == 1L){
                    if (privilege.getxPrivileges() != null && privilege.getxPrivileges().size() > 0)
                        menuExclusion(privilege.getxPrivileges(),priIds,b);
                }else {
                    removes.add(privilege);
                }
            }else {
                if (priIds.contains(privilege.getId())){
                    if (privilege.getxPrivileges() != null && privilege.getxPrivileges().size() > 0)
                        menuExclusion(privilege.getxPrivileges(),priIds,b);
                }else {
                    removes.add(privilege);
                }
            }

        });
        privileges.removeAll(removes);
    }

    public ResObj getTreeMenu(){
        return new ResObj().setState(true).setData(getTreeMenuList(true));
    }

    public  List<XPrivilegesPojo> getTreeMenuList(boolean b) {
        List<Long> privilegesIds = getPriIds();
        List<XPrivilegesPojo> privileges = xPrivilegesDao.getTreePrivileges(new XPrivilegesPojo().setIds(privilegesIds).setMenuLevel(0L).setState("1"));
        menuExclusion(privileges,privilegesIds,b);
        return privileges;
    }
    public List<Long> getPriIds(){
        Long managerId = LoginUser.getId();
        List<Long> roleIds = xManagerRoleDao.getRoleIdListByWhere(new XManagerRolePojo().setManagerId(managerId));
        return getPriIds(roleIds);
    }

    public List<Long> getPriIds(List<Long> roleIds){
        roleIds = xRoleDao.getIdsListByWhere(new XRolePojo().setIds(roleIds).setState("1"));
        List<Long> privilegesIds = xRolePrivilegesDao.getPriIdsListByWhere(new XRolePrivilegesPojo().setRoleIds(roleIds));
        return privilegesIds;
    }
    public void treePriToRoleBean(List<RoleTreeBean> retBean, List<XPrivilegesPojo> allTreePojo){
        for (XPrivilegesPojo all : allTreePojo) {
            RoleTreeBean bean = new RoleTreeBean();
            bean.setId(all.getId());
            bean.setTitle(all.getName());
            bean.setField("priIds");
            if (all.getxPrivileges() != null && all.getxPrivileges().size() != 0)
                treePriToRoleBean(bean.getChildren(),all.getxPrivileges());
            retBean.add(bean);
        }
    }

    /**
     * 获取全部权限  不是树
     * @return
     */
    public List<XPrivilegesPojo> obtainPriAuthoritys() {
        List<Long> privilegesIds = getPriIds();
        List<XPrivilegesPojo> privileges = xPrivilegesDao.getRecordListByWhere(new XPrivilegesPojo().setIds(privilegesIds).setState("1"));
        return privileges;
    }

    public boolean isPri(String perm){
        List<Long> privilegesIds = getPriIds();
        List<XPrivilegesPojo> privileges = xPrivilegesDao.getRecordListByWhere(new XPrivilegesPojo().setIds(privilegesIds).setState("1").setPermission(perm));
        if (privileges == null || privileges.size() == 0)
            return false;
        return true;
    }

    public List<Long> getPriEndIds(Long roleId) {
        List<Long> privilegesIds = xRolePrivilegesDao.getPriIdsListByWhere(new XRolePrivilegesPojo().setRoleId(roleId));
        List<XPrivilegesPojo> privileges = xPrivilegesDao.getTreePrivileges(new XPrivilegesPojo().setIds(privilegesIds).setMenuLevel(0L).setState("1"));
        menuExclusion(privileges,privilegesIds,false);
        List<Long> retList = new ArrayList<>();
        getPriEndIds(retList,privileges);
        return retList;
    }
    public void getPriEndIds(List<Long> retList,List<XPrivilegesPojo> privileges) {
        for (XPrivilegesPojo privilege : privileges) {
            if (privilege.getxPrivileges() == null || privilege.getxPrivileges().size()==0){
                retList.add(privilege.getId());
            }else {
                getPriEndIds(retList,privilege.getxPrivileges());
            }
        }
    }
}
