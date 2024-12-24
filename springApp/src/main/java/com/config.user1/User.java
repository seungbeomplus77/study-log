package com.config.user1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User {
	private final UserService userService;
	
	@Autowired
	public User(UserService userService) {
		this.userService = userService;
	}
	
	public void write() {
		System.out.println("-- 자바로 환경설정 --");
		System.out.println(userService.message());
	}
}
