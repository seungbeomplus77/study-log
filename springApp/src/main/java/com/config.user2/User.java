package com.config.user2;

import org.springframework.beans.factory.annotation.Autowired;

public class User {
	@Autowired
	private UserService userService;

	public void init() {
		System.out.println("init...");
	}

	public void write() {
		System.out.println("-- 자바로 환경설정 : @Bean --");
		System.out.println(userService.message());
	}
	
	public void destroy() {
		System.out.println("destroy...");
	}
}
