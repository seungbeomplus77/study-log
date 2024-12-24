package com.anno.user1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// 스프링 컨테이너
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext(
						"classpath:com/anno/user1/applicationContext.xml");
		
		try {
			User3 obj = context.getBean(User3.class);
			
			obj.write();
		} finally {
			context.close();
		}
	}
}
