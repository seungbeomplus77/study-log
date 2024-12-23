package com.user2;

public class UserServiceImpl implements UserService {
	private String name;
	private String tel;
	private int age;
	
	public UserServiceImpl() {
		this.name = "이자바";
		this.tel = "010-1111-2222";
		this.age = 20;
	}
	
	@Override
	public String message() {
		String result = age >= 20 ? "성인" : "미성년자";
		String s = name + ", " + tel + ", " + age + ", " + result;
		
		return s;
	}

}
