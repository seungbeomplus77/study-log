package com.sp.app.ex11;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 	- 요청 파라미터를 메소드의 매개변수로 받기
 		: 요청 파라미터와 메소드 인수의 이름이 동일한 경우 요청 파라미터를 받는다.
 		: 매개변수와 동일한 이름의 요청 파라미터가 없으면 null
 	
 	- @RequestParam
 		: 요청 파라미터와 메소드의 매개변수를 매핑
 		: 기본이 필수이므로 해당하는 요청 파라미터가 없으면 400 에러가 발생
 		: 속성에서 required = false 로 설정하면 필수가 아님
 		: defaultValue 속성을 이용하여 기본값을 설정할 수 있다.
 		: name(value) 속성은 파라미터의 이름을 설정하며 스프링 6.1 에서는 name 속성을 설정하지 않으면 에러가 발생
 */


@Controller("ex11.testController")
@RequestMapping("/ex11/*")
public class TestController {
	@GetMapping("main")
	public String main() throws Exception {
		return "ex11/main";
	}
	
	/*
	@GetMapping("request")
	public String handleRequest(String name, // 이 코드는 스프링 6에서는 에러가 발생함.
			int age, // age 파라미터가 넘어오지 않으면 null 이므로 500 에러
			Model model) throws Exception {
		
		String s = name + ", " + age;
		
		model.addAttribute("message", s);
		
		return "ex11/result";
	}
	*/
	
	/*
	@GetMapping("request")
	public String handleRequest(String name,
			Integer age,
			Model model) throws Exception {
		
		String s = name + ", " + age;
		
		model.addAttribute("message", s);
		
		return "ex11/result";
	}
	*/
	
	/*
	@GetMapping("request")
	public String handleRequest(
			@RequestParam(name = "name", required = false) String name, // name은 null 을 허용할때
			@RequestParam(name = "age") int age,
			Model model) throws Exception {
		
		String s = name + ", " + age;
		
		model.addAttribute("message", s);
		
		return "ex11/result";
	}
	*/
	
	@GetMapping("request")
	public String handleRequest(
			@RequestParam(name = "name", defaultValue = "기본") String name,
			@RequestParam(name = "age", defaultValue = "0") int a,
			Model model) throws Exception {
		
		String s = name + ", " + a;
		
		model.addAttribute("message", s);
		
		return "ex11/result";
	}
}
