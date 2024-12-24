package com.anno.user7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/*
 	- @Component의 구체적인 애노테이션
 	- 비지니스 로직(파일매니저, 페이징처리 등)을 담당하는 클래스의 빈 등록
 */

@Service
public class User {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 애노테이션 : @Service를 이용한 빈 등록 --");
		System.out.println(s);
	}
}
