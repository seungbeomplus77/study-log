package com.scope1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// 스프링 컨테이너
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext(
						"classpath:com/scope1/applicationContext.xml");
		
		try {
			Movie movie1 = context.getBean(Movie.class); // 컨테이너에서 객체 끄집어내는 getBean() 메소드
			Movie movie2 = context.getBean(Movie.class);
			
			if(movie1 == movie2) {
				System.out.println("movie : 동일 객체...");
			} else {
				System.out.println("다른 객체...");
			}
			movie1.play();
			movie2.play();
			
			System.out.println("------------------------------------");
			
			Music music1 = context.getBean(Music.class);
			Music music2 = context.getBean(Music.class);
			
			if(music1 == music2) {
				System.out.println("music : 동일 객체...");
			} else {
				System.out.println("다른 객체...");
			}
			music1.play();
			music2.play();
		
		} finally {
			context.close(); // 모든 리소스는 닫아주는게 원칙이다.
		}
	}
}
