package com.user4;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// 스프링 컨테이너
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext(
						"classpath:com/user4/applicationContext.xml");
		
		try {
			// 스프링 컨테이너에서 빈을 가져옴
			// User obj = (User)context.getBean("user");
			User obj = context.getBean(User.class); // 다운 캐스팅 필요없음, 이렇게 작성하면
			
			obj.write();
		} finally {
			context.close(); // 모든 리소스는 닫아주는게 원칙이다.
		}
	}
}
