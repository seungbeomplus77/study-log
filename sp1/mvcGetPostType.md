# 1. Spring MVC GET/POST 메서드 파라미터로 받을 수 있는 대표 타입

## Model / ModelMap / ModelAndView
- Model 혹은 ModelMap 파라미터를 통해 뷰로 전달할 데이터를 담을 수 있습니다
## 예시
```java
public String someGetMethod(Model model) {
    model.addAttribute("key", "value");
    return "viewName";
}
```
- ModelAndView는 메서드의 반환 타입으로 자주 쓰이지만, 파라미터로도 받을 수 있습니다. 다만, 일반적으로 파라미터보다 반환 객체로 사용하는 경우가 더 많습니다.

---

## 요청/응답 객체 (HttpServletRequest / HttpServletResponse)
- 서블릿 API가 필요할 때 직접 사용할 수 있습니다.
----
## 예시
```java
public String somePostMethod(HttpServletRequest request, HttpServletResponse response) {
    String param = request.getParameter("paramName");
    // ...
    return "viewName";
}
```
- 스프링이 자동으로 주입해 주므로, 필요한 경우 언제든 파라미터에 선언할 수 있습니다.
- 다만, Spring MVC의 @RequestParam, @ModelAttribute 등을 통해 더 편리하게 요청 데이터를 받을 수 있으므로, 직접 서블릿 API를 사용할 일은 줄어드는 추세입니다.
---
## HttpServletRequest 인터페이스에 정의 된 메서드
- request.getContextPath() : 배포된 웹 애플리케이션의 최상위 경로 (예: "/myapp")
- request.getRequestURI() : 컨텍스트 패스를 포함한 전체 요청 경로 (예: "/myapp/member/login")
- request.getQueryString() : URL에 붙은 쿼리 파라미터 (예: "userId=abc&param2=value2")
---

## @RequestParam
- 요청 파라미터를 특정 변수에 바로 매핑할 때 사용.
## 예시
```java
public String someMethod(@RequestParam("num") int numParam) {
    // numParam에 "num" 파라미터 값이 바로 매핑됨
    // ...
    return "viewName";
}
```
- 파라미터 이름이 같을 경우 @RequestParam 생략 가능 (스프링 부트에선 기본 동작).

---

## 2. Spring MVC GET/POST 메서드 return 타입의 다양성
# String
- 반환된 문자열이 뷰 이름이 됨.
## 예시
```java
public String requestForm(Model model) {
    model.addAttribute("data", "someValue");
    return "viewName"; // -> 포워딩
}
```

---

# ModelAndView
- 뷰 이름과 모델 데이터를 한 객체 안에 담아서 반환.
## 예시
```java
public ModelAndView requestForm() {
    ModelAndView mav = new ModelAndView("viewName");
    mav.addObject("data", "someValue");
    return mav;
}
```

---

# void
- 직접 HttpServletResponse로 출력하거나, RequestToViewNameTranslator를 통해 뷰 이름을 추론할 수도 있습니다.
## 예시
```java
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
```

---

# 객체(도메인 객체) + @ResponseBody
- REST API 응답 시 주로 사용.
- JSON 형식 등으로 바로 직렬화되어 클라이언트로 전송.
## 사용예시
```java
@PostMapping("/api/data")
@ResponseBody
public SomeDto processData(@RequestBody SomeDto dto) {
    // ...
    return dto; // -> JSON 형태로 응답
}
```

---

# Map, Model, ModelMap 리턴 타입
- 반환값은 모델이 된다.
- 뷰의 이름은 url을 가지고 결정한다.
- url 이 "/ex03/hello" 인 경우 뷰의 이름은 "ex03/hello" 가 된다.

## 사용예시
```java
	// Map 을 반환하면 JSP는 url을 가지고 결정한다.
	@GetMapping("hello2")
	public Map<String, ?> request() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("message", "Map 인터페이스를 리턴 타입으로 포워딩");
		return model;
	}
```

---

```java
	@GetMapping("hello3")
	public Model result() throws Exception {
		Model model = new ExtendedModelMap();
		
		model.addAttribute("message", "Model 인터페이스를 리턴 타입으로 포워딩");
		return model;
	}
}
```
