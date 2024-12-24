package com.anno.user2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class User2 {
	
	@Autowired
	@Qualifier("userService2") // @Autowired와 함께 사용해야하며, 이름을 설정하여 의존성 주입
	private UserService service; // 변수명은 클래스 이름하고 같게하고, 첫문자는 소문자로 준다.
	
	public void write() {
		String s = service.message();
		
		System.out.println("-- @Autowired, @Qualifier : 동일한 타입이 둘 이상인 경우 --");
		System.out.println(s);
	}
}
