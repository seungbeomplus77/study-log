package com.anno.user1;

import org.springframework.beans.factory.annotation.Autowired;

public class User {
	// 필드를 이용한 의존관계 설정. setter를 작성하지 않아도 된다.
	@Autowired	// 타입을 이용한 의존관계 설정, setter를 만들지 않아도 된다.
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 애노테이션 : 필드를 이용한 의존관계 설정 --");
		System.out.println(s);
	}
}
