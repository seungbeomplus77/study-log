package com.anno.user5;

import org.springframework.beans.factory.annotation.Value;

public class UserServiceImpl implements UserService {
	private String name;
	private String tel;
	private int age;
	
	@Value("자바")
	private String subject;
	
	public UserServiceImpl(@Value("하하하") String name, @Value("010-1111-1111") String tel, @Value("21") int age) {
		this.name = name;
		this.tel = tel;
		this.age = age;
	}
	
	@Override
	public String message() {
		String result = age >= 20 ? "성인" : "미성년자";
		String s = name + ", " + tel + ", " + age + ", " + result + ", " + subject;
		
		return s;
	}

}
