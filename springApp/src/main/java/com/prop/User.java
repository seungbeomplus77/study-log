package com.prop;

public class User {
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 프로퍼티 파일 내용 읽어오기 --");
		System.out.println(s);
	}

	
}
