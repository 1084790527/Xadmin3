package com.yao;

import com.yao.common.util.IdWorker;
import com.yao.common.util.JwtUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
}
