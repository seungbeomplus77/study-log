package com.user5;

public class User {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void disp() {
		String s = userService.message();
		
		System.out.println("...컬렉션 타입의 의존성 주입 --");
		System.out.println(s);
	}
}
