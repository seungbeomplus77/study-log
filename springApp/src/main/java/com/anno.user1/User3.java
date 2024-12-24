package com.anno.user1;

import org.springframework.beans.factory.annotation.Autowired;

public class User3 {

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 애노테이션 : setter를 이용한 의존관계 설정 --");
		System.out.println(s);
	}



}
