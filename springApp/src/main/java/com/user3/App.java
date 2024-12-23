package com.user3;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// 스프링 컨테이너
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext(
						"classpath:com/user3/applicationContext.xml");
		
		try {
			// 스프링 컨테이너에서 빈을 가져옴
			// User obj = (User)context.getBean("user");
			User obj = context.getBean(User.class); // 다운 캐스팅 필요없음, 이렇게 작성하면
			
			// setter는 실행 중 값을 변경할 수 있으므로 안전하지 못함.
			/*
			UserService service = context.getBean(UserServiceImpl.class);
			obj.setUserService(service);
			*/
			
			obj.write();
		} finally {
			context.close(); // 모든 리소스는 닫아주는게 원칙이다.
		}
	}
}
