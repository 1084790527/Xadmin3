package com.yao;
/**
 * @author 妖妖
 * @date 9:39 2021/3/6
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yao.bean.RoleTreeBean;
import com.yao.bean.pojo.XManagerRolePojo;
import com.yao.bean.pojo.XPrivilegesPojo;
import com.yao.bean.pojo.XRolePojo;
import com.yao.bean.pojo.XRolePrivilegesPojo;
import com.yao.dao.*;
import com.yao.service.AuthorityService;
import com.yao.service.session.LoginUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest(classes = XAdminApplication.class)
@RunWith(SpringRunner.class)
public class AddRoleTest {
    private static Log log = LogFactory.getLog(AddRoleTest.class);


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
    @Autowired
    private AuthorityService authorityService;

    @Test
    public void t1(){
        String managerId = "2221";
        List<String> roleIds = xManagerRoleDao.getRoleIdListByWhere(new XManagerRolePojo().setManagerId(managerId));
        roleIds = xRoleDao.getIdsListByWhere(new XRolePojo().setIds(roleIds).setServiceId("1111").setState("1"));
        List<String> privilegesIds = xRolePrivilegesDao.getPriIdsListByWhere(new XRolePrivilegesPojo().setRoleIds(roleIds));
        List<XPrivilegesPojo> privileges = xPrivilegesDao.getTreePrivileges(new XPrivilegesPojo().setIds(privilegesIds).setMenuLevel(0L).setState("1"));
//        log.info(JSON.toJSONString(privileges));
        authorityService.menuExclusion(privileges,privilegesIds,false);
        log.info(JSON.toJSONString(privileges));

        List<RoleTreeBean> beans = new ArrayList<RoleTreeBean>();
        authorityService.treePriToRoleBean(beans,privileges);
        log.info(JSON.toJSONString(beans));
    }
}
