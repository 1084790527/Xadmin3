package com.yao.interceptor;
/**
 * xss过滤拦截器
 * @author 妖妖
 * @date 16:17 2021/3/8
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssFilter implements Filter {
    private static Log log = LogFactory.getLog(XssFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Xss保护过滤器 Init.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssHttpWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
        log.info("Xss保护过滤器 destroy.");
    }
}
