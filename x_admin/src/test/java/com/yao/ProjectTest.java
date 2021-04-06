package com.yao;
/**
 * @author 妖妖
 * @date 17:39 2021/3/2
 */

import com.alibaba.fastjson.JSON;
import com.yao.bean.pojo.XManagerRolePojo;
import com.yao.bean.pojo.XPrivilegesPojo;
import com.yao.bean.pojo.XRolePrivilegesPojo;
import com.yao.bean.pojo.XServicePojo;
import com.yao.common.util.IdWorker;
import com.yao.common.util.JwtUtil;
import com.yao.dao.XManagerRoleDao;
import com.yao.dao.XPrivilegesDao;
import com.yao.dao.XRolePrivilegesDao;
import com.yao.dao.XServiceDao;
import io.jsonwebtoken.Claims;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@SpringBootTest(classes = XAdminApplication.class)
@RunWith(SpringRunner.class)
public class ProjectTest {
    private static Log log = LogFactory.getLog(ProjectTest.class);

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private XServiceDao xServiceDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private XManagerRoleDao xManagerRoleDao;
    @Autowired
    private XRolePrivilegesDao xRolePrivilegesDao;
    @Autowired
    private XPrivilegesDao xPrivilegesDao;

    /**
     * 生成id算法参数
     */
    @Test
    public void t1(){
        log.info("------------------------");
        Long id = idWorker.nextId();
        log.info(id);
//        log.info(id.length());
        log.info("------------------------");
    }

    /**
     * mysql测试 日志打印
     */
    @Test
    public void t2(){
        log.info("------------------------");
        xServiceDao.getRecordListByWhere(new XServicePojo());
        log.info("------------------------");
        log.info(JSON.toJSONString(xManagerRoleDao.getRoleIdListByWhere(new XManagerRolePojo().setManagerId(2221L))));
        log.info(JSON.toJSONString(xRolePrivilegesDao.getPriIdsListByWhere(new XRolePrivilegesPojo().setRoleIds(Arrays.asList(new Long[]{0001L})))));
    }

    /**
     * jwt 生成解析测试
     */
    @Test
    public void t3(){
        log.info("------------------------");
        Long id = idWorker.nextId();
        Map<String,Object> args = new HashMap<>();
        args.put("yy","uu");
        args.put("xx","cc");
        String token = jwtUtil.createJWT(id+"","",args);
        log.info("token: "+token);
        Claims claims = jwtUtil.parseJWT(token);
        log.info(claims.get("yy"));
        log.info("------------------------");
    }

//    public void t4t1(List<XPrivilegesPojo> privileges,List<String> priIds){
//        List<XPrivilegesPojo> removes = new ArrayList<>();
//        privileges.forEach(privilege -> {
//            if (priIds.contains(privilege.getId())){
//                if (privilege.getxPrivileges() != null && privilege.getxPrivileges().size() > 0)
//                    t4t1(privilege.getxPrivileges(),priIds);
//            }else {
//                removes.add(privilege);
//            }
//        });
//        privileges.removeAll(removes);
//    }

    @Test
    public void t4(){
        log.info("------------------------------");
        List<Long> priIds = xRolePrivilegesDao.getPriIdsListByWhere(new XRolePrivilegesPojo().setRoleIds(Arrays.asList(new Long[]{0001L})));
        List<XPrivilegesPojo> privileges = xPrivilegesDao.getTreePrivileges(new XPrivilegesPojo().setIds(priIds).setMenuLevel(0L).setPermissionType(1L));
//        t4t1(privileges,priIds);
        log.info(JSON.toJSONString(privileges));
        log.info("------------------------------");
    }
}
