# Spring MVC 관련 에노테이션 정리

## @Controller
- 스테레오 타입(객체를 생성하는) 에노테이션.
- Spring MVC 컨트롤러로 인식하며, 클라이언트의 요청을 받는다.
- 사용 예시에서 빈의 이름은 클래스명의 첫 글자를 소문자로 한 이름이다.(userController)
- 다른 클래스와 빈의 이름이 동일하면 오류가 발생

---

## @RequestMapping
- URL을 class 또는 메소드와 mapping
- value 속성으로 주소를 매핑
- value 속성만 사용하는 경우 value 속성 생략 가능
- 주의 : name 속성은 이름을 설정하는 것이기 때문에 주소 매핑이 아니다.

---

## @PostMapping, @GetMapping
- 각각 POST 방식, GET 방식의 요청만을 처리한다는 뜻의 에노테이션

---

## ModelAndView
- 컨트롤러의 처리 결과를 전달할 뷰의 이름과 뷰에 전달할 모델을 가지고 있다.

---

### 사용 예시

```java
@Controller
public class UserController {
	// @RequestMapping("/ex01/main") // GET, POST, PUT 모두 처리. 부트(스프링 6 이상 버전)에서는 경고
	@RequestMapping(value = "/ex01/main", method = {RequestMethod.GET, RequestMethod.POST})
	public String home() {
		return "ex01/main"; // forward 할 뷰의 이름
			// String 을 반환하면 내부적으로 ModelAndView 으로 변환
	}
	
	// GET 방식의 요청만 받음
	// @GetMapping("/ex01/request") 와 동일, 스프링 4.3이상
	@RequestMapping(value = "/ex01/request", method = {RequestMethod.GET})
	public ModelAndView requestGet() throws Exception {
		// ModelAndView mav = new ModelAndView("ex01/main"); // 포워딩 할 뷰의 이름
		
		ModelAndView mav = new ModelAndView(); // 포워딩 할 뷰의 이름
		mav.setViewName("ex01/main");
		mav.addObject("message", "GET 방식으로 접근");
		
		return mav;
	}
	
	// POST 방식의 요청을 처리
	// @PostMapping("/ex01/request") 와 동일
	@RequestMapping(value = "/ex01/request", method = RequestMethod.POST)
	public String requestPost(Model model) throws Exception {
		
		model.addAttribute("message", "POST 방식으로 접근");
		
		return "ex01/main";
	}
	
}
```

