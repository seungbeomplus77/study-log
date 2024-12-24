package com.anno.user3;

public class UserServiceImpl2 implements UserService {
	private String name;
	private String tel;
	private int age;
	
	public UserServiceImpl2() {
		this.name = "스프링";
		this.tel = "010-3333-4444";
		this.age = 21;
	}
	
	@Override
	public String message() {
		String result = age >= 20 ? "성인" : "미성년자";
		String s = name + " : " + tel + " : " + age + " : " + result;
		
		return s;
	}

}
