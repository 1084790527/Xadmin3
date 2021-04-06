package com.yao.service;
/**
 * @author 妖妖
 * @date 14:21 2021/3/3
 */

import com.yao.bean.LoginInfo;
import com.yao.bean.model.LoginModel;
import com.yao.bean.pojo.XManagerPojo;
import com.yao.bean.pojo.XServicePojo;
import com.yao.common.Consts;
import com.yao.common.util.IpAddress;
import com.yao.common.util.JwtUtil;
import com.yao.common.util.MD5Util;
import com.yao.dao.XManagerDao;
import com.yao.dao.XServiceDao;
import com.yao.service.exception.CustException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class LoginService {
    private static Log log = LogFactory.getLog(LoginService.class);

    @Autowired
    private XManagerDao xManagerDao;
    @Autowired
    private XServiceDao xServiceDao;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    @Autowired
    private JwtUtil jwtUtil;

    public void login(LoginModel model) {
        String mobileNo = model.getMobileNo();
        String password = model.getPassword();
        String machineCode = model.getMachineCode();
        String ip = IpAddress.getIpAddress(request);
        XManagerPojo manager = xManagerDao.getRecordByWhere(new XManagerPojo().setMobileNo(mobileNo).setState("1"));
        if (manager == null)
            throw new CustException("账号不存在");
        if (!manager.getPassword().equals(MD5Util.getMD5Str(password)))
            throw new CustException("登入密码错误");
        XServicePojo service = xServiceDao.getRecordByWhere(new XServicePojo().setId(manager.getServiceId()).setState("1"));
        if (service == null)
            throw new CustException("主账号已关闭");

        xManagerDao.updateRecordByKey(new XManagerPojo().setId(manager.getId()).setLastLoginIp(ip).setLastLoginDate(new Date()));

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(manager.getId());
        loginInfo.setNickname(manager.getNickname());
        loginInfo.setMobileNo(manager.getMobileNo());
        loginInfo.setIp(ip);
        loginInfo.setLoginDate(new Date());
        loginInfo.setDefaults(manager.getDefaults());
        loginInfo.setServiceId(manager.getServiceId());
        Map<String,Object> args = new HashMap<>();
        args.put(Consts.LOGIN_INFO,loginInfo);
        String token = jwtUtil.createJWT(manager.getId()+"","",args);
        loginInfo.setToken(token);
        session.setAttribute(Consts.LOGIN_INFO,loginInfo);
    }

    /*@Test
    public void ttPass(){
        //34f85ca80ec353d3052b8a2d3973a0c5
        System.out.println(MD5Util.getMD5Str("qaz123"));
    }*/
}
