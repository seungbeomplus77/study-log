package com.anno.user6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 	- 자동으로 빈 등록(객체 생성)
 	- 빈의 이름은 클래스명의 첫글자를 소문자로 하는 클래스명
 	- User 클래스의 빈 이름 : user
 	- 빈의 이름이 동일한 것이 두개 이상 있으면 예외 발생
 */

@Component
public class User {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 애노테이션 : @Component를 이용한 빈 등록 --");
		System.out.println(s);
	}
}
