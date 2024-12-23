package com.user2;

public class User {
	private UserService userService;
	
	public User(UserService userService) {
		this.userService = userService;
	}
	
	public void init() {
		System.out.println("초기화는 여기서...");
	}
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 생성자를 이용한 의존성 주입 : c 네임스페이스 --");
		System.out.println(s);
	}
	
	public void destroy() {
		System.out.println("객체 소멸 전 작업은 여기서...");
	}
}
