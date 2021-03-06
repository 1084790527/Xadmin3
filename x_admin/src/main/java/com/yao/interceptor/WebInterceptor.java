package com.yao.interceptor;
/**
 * @author 妖妖
 * @date 17:23 2021/3/2
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yao.bean.LoginInfo;
import com.yao.bean.pojo.XPrivilegesPojo;
import com.yao.common.Consts;
import com.yao.common.util.IpAddress;
import com.yao.common.util.JwtUtil;
import com.yao.common.util.RequestUtil;
import com.yao.service.AuthorityService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Component
public class WebInterceptor implements HandlerInterceptor {
    private static Log log = LogFactory.getLog(WebInterceptor.class);

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthorityService authorityService;

    @Value("${interceptor.noVerify}")
    private List<String> noVerify;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IpAddress.getIpAddress(request);
        log.info(request.getServletPath()+"   "+request.getMethod()+"   "+ip);
        HttpSession session = request.getSession();
        String path = request.getServletPath();
        String method = request.getMethod();

        try {
            Object o = session.getAttribute(Consts.LOGIN_INFO);
            if (o == null){
                response.sendRedirect(request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/login");
                return false;
            }
            LoginInfo info = (LoginInfo) o;
            Claims claims = jwtUtil.parseJWT(info.getToken());
            if (claims == null || !claims.getId().equals(info.getId())){
                response.sendRedirect(request.getScheme()+"://"+request.getServerName()+"/"+request.getContextPath()+"/login");
                return false;
            }
            if (noVerify.contains(path)){
                return true;
            }
            //验证是否有这个权限
            Boolean authority = false;
            //判断权限
            List<XPrivilegesPojo> privilegesPojos = authorityService.obtainPriAuthoritys();
            for (XPrivilegesPojo pojo : privilegesPojos){
                if (path.equals(pojo.getUrl()) && method.equals(pojo.getMethodType())){
                    authority = true;
                    if (StringUtils.isNotBlank(pojo.getParam())){
                        Map<String,String> param = RequestUtil.getSignMap(request);
                        JSONObject json = JSON.parseObject(pojo.getParam());
                        for (Map.Entry<String, Object> entry : json.entrySet()) {
                            String value = param.get(entry.getKey());
                            if (value == null || !value.equals(entry.getValue()))
                                authority = false;
                        }
                    }
                }
                if (authority)
                    return true;
            }
            response.sendRedirect(request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/error/404");
            return false;
        }catch (Exception e){
            log.error("拦截器异常："+e);
            response.sendRedirect(request.getScheme()+"://"+request.getServerName()+"/"+request.getContextPath()+"/login");
            return false;
        }
    }
}
