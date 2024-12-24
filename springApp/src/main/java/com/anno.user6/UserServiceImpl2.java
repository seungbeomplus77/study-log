package com.anno.user6;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("user6.userService") // 빈의 이름을 같게하면 여러명 협업할때 서버가 안 켜지는 등 예외 발생
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
