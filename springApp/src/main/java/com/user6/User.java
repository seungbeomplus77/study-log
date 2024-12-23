package com.user6;

public class User {
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 의존관계 자동 설정 --");
		System.out.println(s);
	}

	
}
