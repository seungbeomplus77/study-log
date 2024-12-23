package com.user1;

public class User {
	private UserService userService;
	
	public User(UserService userService) {
		this.userService = userService;
	}
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 생성자를 이용한 의존성 주입 --");
		System.out.println(s);
	}
}
