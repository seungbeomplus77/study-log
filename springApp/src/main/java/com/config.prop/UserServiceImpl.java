package com.config.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
 	@Value("${프로퍼티이름}") : 프로퍼티 값 반환
 	user.name을 프로퍼티 이름으로 사용하면 시스템 사용자명이 나옴. 
 	프로퍼티 이름을 user로 만들지 않기를 권장함
 */

@Service
public class UserServiceImpl implements UserService {
	private @Value("${join.name}") String name; 
	private @Value("${join.tel}") String tel;
	private @Value("${join.age}") int age;
	
	@Override
	public String message() {
		
		return name + " : " + tel + " : " + age;
	}

}
