package com.anno.user7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class User2 {
	
	@Autowired
	@Qualifier("user7.userService")
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 애노테이션 : @Component를 이용한 빈 등록 --");
		System.out.println(s);
	}
}
