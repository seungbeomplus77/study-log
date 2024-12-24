package com.anno.user4;

import javax.inject.Inject;

/*
 	- 다음이 필요
	<dependency>
		<groupId>javax.inject</groupId>
		<artifactId>javax.inject</artifactId>
		<version>1</version>
	</dependency>

 */


public class User {
	/*
	 	- 자바가 제공하는 어노테이션
	 	- 타입으로 의존성 주입
	 	- 동일한 타입이 둘 이상인 경우 필드와 동일한 이름을 갖는 빈 주입
	 */
	@Inject
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- @Inject : 타입으로 의존성 주입 --");
		System.out.println(s);
	}
}
