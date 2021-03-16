package com.yao;

import com.yao.common.util.IdWorker;
import com.yao.common.util.JwtUtil;
import com.yao.interceptor.XssFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@MapperScan({"com.yao.dao"})
public class XAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(XAdminApplication.class, args);
	}
	@Bean
	public IdWorker idWorker() {
		return new IdWorker();
	}

	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}

	/**
	 * xss过滤拦截器
	 */
	@Bean
	public FilterRegistrationBean xssFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(Integer.MAX_VALUE-1);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = new HashMap();
		//excludes用于配置不需要参数过滤的请求url
		initParameters.put("excludes", "/static/*");
		//isIncludeRichText主要用于设置富文本内容是否需要过滤
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}
}
