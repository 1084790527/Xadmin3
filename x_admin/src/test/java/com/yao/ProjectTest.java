package com.yao;
/**
 * @author 妖妖
 * @date 17:39 2021/3/2
 */

import com.yao.bean.pojo.XServicePojo;
import com.yao.common.util.IdWorker;
import com.yao.common.util.JwtUtil;
import com.yao.dao.XServiceDao;
import io.jsonwebtoken.Claims;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 生成id算法参数
     */
    @Test
    public void t1(){
        log.info("------------------------");
        String id = idWorker.nextId();
        log.info(id);
        log.info(id.length());
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
    }

    /**
     * jwt 生成解析测试
     */
    @Test
    public void t3(){
        log.info("------------------------");
        String id = idWorker.nextId();
        Map<String,Object> args = new HashMap<>();
        args.put("yy","uu");
        args.put("xx","cc");
        String token = jwtUtil.createJWT(id,"",args);
        log.info("token: "+token);
        Claims claims = jwtUtil.parseJWT(token);
        log.info(claims.get("yy"));
        log.info("------------------------");
    }
}
