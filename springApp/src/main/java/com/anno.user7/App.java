package com.anno.user7;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// 스프링 컨테이너
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext(
						"classpath:com/anno/user7/applicationContext.xml");
		
		try {
			User obj = context.getBean(User.class);
			
			obj.write();
			System.out.println("------------------------");
			
			// 런타임 오류 발생
			User2 obj2 = context.getBean(User2.class);
			obj2.write();
		} finally {
			context.close();
		}
	}
}
