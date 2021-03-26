package com.yao.controller;
/**
 * @author 妖妖
 * @date 17:19 2021/3/2
 */

import com.yao.bean.LoginInfo;
import com.yao.common.Consts;
import com.yao.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping
@Controller
public class BaseController {
    private static Log log = LogFactory.getLog(BaseController.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public String main(){
        return "redirect:/index";
    }
    @GetMapping(value = "login")
    public String login(){
        Object o = session.getAttribute(Consts.LOGIN_INFO);
        if (o != null){
            LoginInfo loginInfo = (LoginInfo) o;
            try {
                Claims claims = jwtUtil.parseJWT(loginInfo.getToken());
                if (claims != null && !claims.getId().equals(loginInfo.getId()))
                    return "redirect:/index";
            } catch (Exception e) {
            }
            session.removeAttribute(Consts.LOGIN_INFO);
        }
        return "login";
    }

    /**
     * 全匹配请求url排除static静态路径
     * @return 映射页面
     */
//    @GetMapping(value = {"{path:\\b(?!static\\b)\\w+}/**"})
    @GetMapping(value = {"{path:(?!static\\b)\\w+}/**"})
    public String path(){
        return request.getServletPath();
    }

    @GetMapping(value = "loginOut")
    public String loginOut(){
        //全部删除
//        Enumeration em = session.getAttributeNames();
//        while (em.hasMoreElements()){
//            session.removeAttribute(em.nextElement().toString());
//        }
        session.removeAttribute(Consts.LOGIN_INFO);
        return "redirect:/login";
    }
}
