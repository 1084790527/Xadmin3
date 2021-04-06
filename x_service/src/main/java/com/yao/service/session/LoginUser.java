package com.yao.service.session;
/**
 * @author 妖妖
 * @date 16:17 2021/3/3
 */

import com.yao.bean.LoginInfo;
import com.yao.common.Consts;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class LoginUser {
    private static Log log = LogFactory.getLog(LoginUser.class);

    public static LoginInfo getLoginInfo(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute(Consts.LOGIN_INFO);
        return loginInfo;
    }

    public static Long getId(){
        return getLoginInfo().getId();
    }

    public static String getNickname() {
        return getLoginInfo().getNickname();
    }

    public static String getMobileNo() {
        return getLoginInfo().getMobileNo();
    }

    public static String getEmail() {
        return getLoginInfo().getEmail();
    }

    public static String getIp() {
        return getLoginInfo().getIp();
    }

    public static Date getLoginDate() {
        return getLoginInfo().getLoginDate();
    }

    public static String getToken() {
        return getLoginInfo().getToken();
    }

    public static String getDefaults() {
        return getLoginInfo().getDefaults();
    }

    public static Long getServiceId() {
        return getLoginInfo().getServiceId();
    }
}
