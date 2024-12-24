package com.config.prop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App {

	public static void main(String[] args) {
		AbstractApplicationContext context =
				new AnnotationConfigApplicationContext(SpringConfig.class);
		
		try {
			User user = context.getBean(User.class);
			
			user.write();
		} finally {
			context.close();
		}
	}
}
