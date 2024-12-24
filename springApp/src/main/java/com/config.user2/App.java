package com.config.user2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App {

	public static void main(String[] args) {
		AbstractApplicationContext context =
				new AnnotationConfigApplicationContext(SpringConfig.class);
		
		try {
			User user = context.getBean(User.class);
			user.write();
			
			User user2 = context.getBean(User.class, "user2.user");
			
			System.out.println(user == user2); // true
		
		} finally {
			context.close();
		}

	}

}
