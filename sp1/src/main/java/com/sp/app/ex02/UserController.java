package com.sp.app.ex02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 	- ModelAndView
 		: 컨트롤러의 처리 결과를 전달할 뷰의 이름과 뷰에 전달할 모델을 가지고 있다.
 */

@Controller("ex02.userController") // 빈의 이름이 동일하면 에러 발생
@RequestMapping("/ex02/*")
public class UserController {
	
	@GetMapping("request")
	public ModelAndView requestForm() throws Exception {
		return new ModelAndView("ex02/write");
	}
	
	/*
	@PostMapping("request")
	public ModelAndView requestSubmit(String num) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		try {
			int n = Integer.parseInt(num);
			int s = 0;

			for(int i = 1; i <= n; i++) {
				s += i;
			}
			
			mav.addObject("message", "1~" + num + " 까지 합 : " + s);
		} catch (NumberFormatException e) {
			// redirect
			return new ModelAndView("redirect:/ex02/error"); // redirect : crud 후, 새로고침 후, 로그인/로그아웃 후
		}
		
		mav.setViewName("ex02/result"); // 뷰 이름
		return mav;
	}
	*/
	
	@PostMapping("request")
	public String requestSubmit(String num, Model model) throws Exception {
		
		try {
			int n = Integer.parseInt(num);
			int s = 0;

			for(int i = 1; i <= n; i++) {
				s += i;
			}
			
			model.addAttribute("message", "1~" + num + " 까지 합 : " + s);
		} catch (NumberFormatException e) {
			// redirect
			return "redirect:/ex02/error"; // redirect : crud 후, 새로고침 후, 로그인/로그아웃 후
		}

		return "ex02/result";
	}
	
	@GetMapping("error")
	public ModelAndView error() throws Exception {
		return new ModelAndView("ex02/error");
	}
}
