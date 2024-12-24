package com.anno.user7;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("user7.userService")
public class UserServiceImpl2 implements UserService {
	@Value("홍자바")
	private String name;
	@Value("010-9999-9999")
	private String tel;
	@Value("15")
	private int age;
	
	@Override
	public String message() {
		String result = age >= 20 ? "성인" : "미성년자";
		String s = name + ", " + tel + ", " + age + ", " + result;
		
		return s;
	}

}
