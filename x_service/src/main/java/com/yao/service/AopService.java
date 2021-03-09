package com.yao.service;
/**
 * @author 妖妖
 * @date 11:40 2021/3/3
 */

import com.yao.bean.LoginInfo;
import com.yao.bean.pojo.XSystemLogPojo;
import com.yao.common.Consts;
import com.yao.common.util.DateUtil;
import com.yao.common.util.IdWorker;
import com.yao.dao.XSystemLogDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

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
    @Value("${spring.datasource.url}")
    public String datasourceUrl;

    public void journal(XSystemLogPojo systemLog) {
        Date date = new Date();
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
        systemLog.setCreDate(date);
        systemLog.setId(idWorker.nextId());
        systemLog.setTableName(DateUtil.getyyyyMM(date));
        xSystemLogDao.insertRecord(systemLog);
    }

    /**
     * 人工维护
     */
    public void creLogTable(){
        String tableName = "x_system_log_"+DateUtil.getyyyyMM(1);//下个月
        String dbName = datasourceUrl.substring(0,datasourceUrl.indexOf("?"));
        dbName = dbName.substring(dbName.lastIndexOf("/")+1);
        List<String> tableNames = xSystemLogDao.getDbNames(dbName);
        if (!tableNames.contains(tableName)){
            //1.新增表格
            xSystemLogDao.insertTable(tableName);
            StringBuilder tables = new StringBuilder();
            for (String name : tableNames) {
                if (name.contains("x_system_log_")){
                    tables.append("`").append(name).append("`,");
                }
            }
            tables.append("`").append(tableName).append("`");
            //2.修改子表
//            xSystemLogDao.updateEngine(tables.toString());
        }
    }
}
