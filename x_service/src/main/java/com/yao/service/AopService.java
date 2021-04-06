package com.yao.service;
/**
 * @author 妖妖
 * @date 11:40 2021/3/3
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yao.bean.LoginInfo;
import com.yao.bean.model.XSystemLogModel;
import com.yao.bean.model.tab.XSystemLogTab;
import com.yao.bean.pojo.XManagerPojo;
import com.yao.bean.pojo.XSystemLogPojo;
import com.yao.bean.vo.ResObj;
import com.yao.common.Consts;
import com.yao.common.util.DateUtil;
import com.yao.common.util.IdWorker;
import com.yao.common.util.Tool;
import com.yao.dao.XManagerDao;
import com.yao.dao.XSystemLogDao;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    @Autowired
    private XManagerDao xManagerDao;

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

    public ResObj journalData(XSystemLogTab tab) {
        Long id = tab.getId();
        String requestUrl = tab.getRequestUrl();
        String method = tab.getMethod();
        String ip = tab.getIp();
        Long managerId = tab.getManagerId();
        XSystemLogPojo sel = new XSystemLogPojo();
        if (id != null)
            sel.setId(id);
        if (StringUtils.isNotBlank(requestUrl))
            sel.setRequestUrl(requestUrl);
        if (StringUtils.isNotBlank(method))
            sel.setMethod(method);
        if (StringUtils.isNotBlank(ip))
            sel.setIp(ip);
        if (managerId != null)
            sel.setManagerId(managerId);
        if (StringUtils.isNotBlank(tab.getOrder()))
            PageHelper.orderBy(Tool.humpToLine(tab.getSort().replaceAll("Name","Id"))+" "+tab.getOrder());
        else
            PageHelper.orderBy("id desc");
        Page<XSystemLogPojo> page = PageHelper.startPage(tab.getPage(),tab.getLimit(),true);
        xSystemLogDao.getRecordListByWhere(sel);
        List<XSystemLogModel> models = new ArrayList<>();
        for (XSystemLogPojo pojo : page.getResult()) {
            XSystemLogModel model = new XSystemLogModel(pojo);
            if (model.getManagerId() != null)
                model.setManagerName(xManagerDao.getRecordByKey(new XManagerPojo().setId(model.getManagerId())).getNickname());
            models.add(model);
        }
        return new ResObj().setState(true).setData(models).setCount(page.getTotal());
    }
}
