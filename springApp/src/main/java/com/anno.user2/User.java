package com.anno.user2;

import org.springframework.beans.factory.annotation.Autowired;

public class User {
	/*
	 	- 동일한 타입이 2개 이상이면 필드명과 동일한 이름을 갖는 빈을 주입하며,
	 	- 동일한 이름이 존재하지 않는 경우에는 예외가 발생한다.
	 */
	@Autowired
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- @Autowired : 동일한 타입이 둘 이상인 경우 --");
		System.out.println(s);
	}
}
