package com.yao.interceptor;
/**
 * @author 妖妖
 * @date 17:23 2021/3/2
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MvcInterceptorConfig extends WebMvcConfigurationSupport {
    private static Log log = LogFactory.getLog(MvcInterceptorConfig.class);

    @Autowired
    private WebInterceptor webInterceptor;

    @Value("${spring.resources.static-locations}")
    private String staticLocations;
    @Value("${spring.mvc.static-path-pattern}")
    private String staticPathPattern;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticPathPattern).addResourceLocations(staticLocations);
        //addResourceHandler 拦截路径
        //addResourceLocations  配置系统静态文件路径文件  file:绝对路径   配置系统外的文件
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(webInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/static")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/error/**")
                .excludePathPatterns("/text/api")
                .excludePathPatterns("/text/api/**")
        ;
        super.addInterceptors(registry);
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
    }
}
