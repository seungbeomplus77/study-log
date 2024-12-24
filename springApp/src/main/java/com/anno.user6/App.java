package com.anno.user6;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// 스프링 컨테이너
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext(
						"classpath:com/anno/user6/applicationContext.xml");
		
		try {
			User obj = context.getBean(User.class);
			
			obj.write();
		} finally {
			context.close();
		}
	}
}
