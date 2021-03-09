package com.yao;
/**
 * @author 妖妖
 * @date 14:25 2021/3/9
 */

import com.alibaba.fastjson.JSON;
import com.yao.common.util.DateUtil;
import com.yao.dao.XSystemLogDao;
import com.yao.service.AopService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest(classes = XAdminApplication.class)
@RunWith(SpringRunner.class)
public class LogTest {
    private static Log log = LogFactory.getLog(LogTest.class);

    @Autowired
    private AopService aopService;
    @Autowired
    private XSystemLogDao xSystemLogDao;

    @Value("${spring.datasource.url}")
    public String datasourceUrl;

    @Test
    public void t1(){

        aopService.creLogTable();
    }
}
