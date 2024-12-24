package com.config.prop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User {
	@Autowired
	private UserService service;
	
	public void write() {
		System.out.println("-- 자바 환경 설정 : properties --");
		System.out.println(service.message());
	}
}
