package com.anno.user1;

import org.springframework.beans.factory.annotation.Autowired;

public class User2 {

	private final UserService userService;  // 생성자를 이용해서 의존성 주입하는 이유는 값을 바꾸지않기위해
											// Final 을 이용해서 더 굳건하게 하는 느낌.
											// Final 변수 초기화 할수있는 방법 3가지
											// 선언과 동시에 초기화, 생성자, 인스턴스 초기화 블록
	
	// 생성자를 이용한 의존성 주입
	@Autowired  // 스프링 5.1 이상부터는 <context:anntation-config/> 가 설정되어 있는 경우
				// 생성자를 이용한 의존성 주입에서 @Autowired 생략 가능
	public User2(UserService userService) {
		this.userService = userService;
	}
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 애노테이션 : 생성자를 이용한 의존관계 설정 --");
		System.out.println(s);
	}
}
