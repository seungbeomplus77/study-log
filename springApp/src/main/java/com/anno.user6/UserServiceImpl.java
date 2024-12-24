package com.anno.user6;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // 빈이름 : userServiceImpl
public class UserServiceImpl implements UserService {
	private String name;
	private String tel;
	private int age;

	public UserServiceImpl(@Value("하하하") String name, @Value("010-1111-1111") String tel, @Value("21") int age) {
		this.name = name;
		this.tel = tel;
		this.age = age;
	}
	
	@Override
	public String message() {
		String result = age >= 20 ? "성인" : "미성년자";
		String s = name + ", " + tel + ", " + age + ", " + result;
		
		return s;
	}

}
