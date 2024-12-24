package com.anno.user4;

import javax.inject.Inject;
import javax.inject.Named;

public class User2 {
	

	@Inject
	@Named("userService2")
	private UserService service;
	
	public void write() {
		String s = service.message();
		
		System.out.println("-- @Inject, @Named --");
		System.out.println(s);
	}
}
