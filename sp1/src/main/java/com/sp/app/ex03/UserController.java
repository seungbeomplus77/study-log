package com.sp.app.ex03;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 	- RequestMapping 리턴 타입
 		: String 리턴 타입
 		  - 리턴값이 뷰의 이름이 된다.
 		  - 내부적으로는 ModelAndView 객체를 생성하여 처리한다.
 		
 		: ModelAndView 리턴 타입
 		  - ModelAndView 객체에 뷰의 이름을 설정한다.
 		  - 처리 결과를 담은 모델 객체와 뷰의 이름을 설정한다.
 		
 		: void 리턴 타입
 		  - 메소드의 파라미터에 HttpServletResponse 가 없는 경우 뷰의 이름은 url을 가지고 결정한다.
 		  - 메소드 내부에서 HTTP Response를 직접 처리 할 경우 사용한다.
 		
 		: Map, Model, ModelMap 리턴 타입
 		  - 반환값은 모델이 된다.
 		  - 뷰의 이름은 url을 가지고 결정한다.
 		  - url 이 "/ex03/hello" 인 경우 뷰의 이름은 "ex03/hello" 가 된다.
 */


@Controller("ex03.userController")
@RequestMapping("/ex03/*")
public class UserController {
	
	@GetMapping("hello")
	public void execute(HttpServletResponse resp) throws Exception {
		
		try {
			resp.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = resp.getWriter();
			
			out.print("<html><body>");
			out.print("<p>안녕하세요 !!!</p>");
			out.print("</body></html>");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Map 을 반환하면 JSP는 url을 가지고 결정한다.
	@GetMapping("hello2")
	public Map<String, ?> request() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("message", "Map 인터페이스를 리턴 타입으로 포워딩");
		return model;
	}
	
	@GetMapping("hello3")
	public Model result() throws Exception {
		Model model = new ExtendedModelMap();
		
		model.addAttribute("message", "Model 인터페이스를 리턴 타입으로 포워딩");
		return model;
	}
}
