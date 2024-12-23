package com.scope2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// 스프링 컨테이너
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext(
						"classpath:com/scope2/applicationContext.xml");
		
		try {
			User user = context.getBean(User.class);
			
			user.execute();
			user.execute();
			user.execute();
			System.out.println("----------------");
			
			Movie movie = context.getBean(Movie.class);
			movie.play();
			movie.play();
			System.out.println("----------------");
			
			Music music = context.getBean(Music.class);
			music.play();
			music.play();
		
		} finally {
			context.close(); // 모든 리소스는 닫아주는게 원칙이다.
		}
	}
}
