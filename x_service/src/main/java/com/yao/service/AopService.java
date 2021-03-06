package com.yao.service;
/**
 * @author 妖妖
 * @date 11:40 2021/3/3
 */

import com.yao.bean.LoginInfo;
import com.yao.bean.pojo.XSystemLogPojo;
import com.yao.common.Consts;
import com.yao.common.util.IdWorker;
import com.yao.dao.XSystemLogDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
@Transactional
public class AopService {
    private static Log log = LogFactory.getLog(AopService.class);

    @Autowired
    private XSystemLogDao xSystemLogDao;
    @Autowired
    private HttpSession session;
    @Autowired
    private IdWorker idWorker;

    public void journal(XSystemLogPojo systemLog) {
        Object o = session.getAttribute(Consts.LOGIN_INFO);
        if (o != null){
            try {
                LoginInfo loginInfo = (LoginInfo) o;
                systemLog.setManagerId(loginInfo.getId());
                systemLog.setType("1");
            } catch (Exception e) {
                systemLog.setType("0");
            }
        }else {
            systemLog.setType("0");
        }
        systemLog.setCreDate(new Date());
        systemLog.setId(idWorker.nextId());
        xSystemLogDao.insertRecord(systemLog);
    }
}
