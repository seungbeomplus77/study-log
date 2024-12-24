package com.anno.user5;

import org.springframework.beans.factory.annotation.Autowired;

public class User {
	
	@Autowired
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 애노테이션 : @Value를 이용하여 생성자, 필드에 문자열 또는 기본 자료형 값 설정 --");
		System.out.println(s);
	}
}
