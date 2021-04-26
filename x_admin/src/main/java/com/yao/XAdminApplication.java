package com.yao;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yao.common.util.IdWorker;
import com.yao.common.util.JwtUtil;
import com.yao.interceptor.XssFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@MapperScan({"com.yao.dao"})
@EnableOpenApi
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
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.OAS_30)
				.enable(true)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.yao.controller.api"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("接口文档")
				.description("描述")
				.contact(new Contact("联系人", "www.baidu.com", "***@qq.com"))
				.version("1.0")
				.build();
	}

	@Bean("jackson2ObjectMapperBuilderCustomizer")
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		Jackson2ObjectMapperBuilderCustomizer customizer = new Jackson2ObjectMapperBuilderCustomizer() {
			@Override
			public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
				jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance)
						.serializerByType(Long.TYPE, ToStringSerializer.instance);
			}
		};
		return customizer;
	}
}
