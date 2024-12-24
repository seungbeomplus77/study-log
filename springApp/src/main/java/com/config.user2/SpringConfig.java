package com.config.user2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링 환경 설정 파일
@ComponentScan(basePackages = {"com.config.user2"})
public class SpringConfig {
	
	/*
	 	- @Bean
	 		: 외부 라이브러리의 객체 생성
	 		: 하나 이상의 @Bean 이 있으면 @Configuration 필요
	 		: 만들어진 클래스는 new 를 이용해서 객체 생성 해야함
	 */
	
	@Bean // 빈 이름은 기본적으로 메소드명 - userServiceDevice
	public UserService userServiceDevice() {
		return new UserServiceImpl();
	}
	
	@Bean(name = "user2.user", initMethod = "init", destroyMethod = "destroy")
	public User userDevice() {
		return new User();
	}
}
