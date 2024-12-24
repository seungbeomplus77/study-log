package com.anno.user8;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User {
	
	@Autowired
	private UserService userService;
	
	public User() {
		System.out.println("생성자....");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("생성자 호출 후 바로 다음에 호출. 초기화 작업");
	}
	
	public void write() {
		String s = userService.message();
		
		System.out.println("-- 애노테이션 : @PostConstruct, @PreDestroy를 이용 --");
		System.out.println(s);
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("객체 소멸 전 호출");
	}
}
