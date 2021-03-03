package com.yao.aop;
/**
 * @author 妖妖
 * @date 17:18 2021/3/2
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yao.common.util.IpAddress;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class Aop {
    private static Log log = LogFactory.getLog(Aop.class);
    @Pointcut("execution(* com.yao.controller.*.*(..))")
    public void log(){

    }
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        logs(joinPoint);
    }

    public void logs(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        String url = request.getRequestURI();
        log.info("url={"+url+"}");
        //method
        String method = request.getMethod();
        log.info("method={"+method+"}");
        //ip
        String ip = IpAddress.getIpAddress(request);
//        log.info("ip={"+request.getRemoteAddr()+"}");
        log.info("ip={"+ip+"}");
        //类方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        log.info("class_method={"+classMethod+"}");
        //参数
        JSONArray args = new JSONArray();
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof MultipartFile){
                args.add("file");
            }else if (arg instanceof HttpServletRequest || arg instanceof ServletRequest){
                args.add("request");
            }else if (arg instanceof HttpServletResponse || arg instanceof ServletResponse){
                args.add("response");
            }else {
                args.add(JSON.toJSONString(arg));
            }
        }
        log.info("args={"+ args.toJSONString()+"}");
    }
}
