package com.sp.app.calc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 객체 생성, 클라이언트의 요청을 받음.
@RequestMapping("/calc/*")
public class CalculatorController {
	private final CalculatorService service;
	
	@Autowired
	public CalculatorController(CalculatorService service) {
		this.service = service;
	}
	
	// @RequestMapping(value = "main", method = RequestMethod.GET)
	@GetMapping("main") // @GetMapping : 위 코드의 축약형
	public String requestForm() throws Exception {
		return "calc/write";
	}	
	
	@PostMapping("main")
	public ModelAndView requestSubmit(Calculator dto) throws Exception {
		ModelAndView mav = new ModelAndView("calc/result");
		
		try {
			int s = service.result(dto);
			
			mav.addObject("dto", dto);
			mav.addObject("result", s);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
}
