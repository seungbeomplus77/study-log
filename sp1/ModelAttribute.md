# @ModelAttribute

## 개요
- **@ModelAttribute**는 Controller 메서드의 **파라미터**나 **리턴 값**을 `Model` 객체와 바인딩하기 위한 애노테이션이다.
- 내부적으로 `ModelMap.addAttribute(이름, 값)`과 유사하게 작동한다.

## 사용 방법

### 1. 요청 메서드의 매개변수에 사용
- 클라이언트 요청에 담긴 **파라미터**는 `@ModelAttribute`가 적용된 매개변수(모델 객체)에 **매핑**되어 넘어온다.
- `@ModelAttribute`가 적용된 매개변수는 **포워딩**되는 View(JSP 등)에서 **바로 사용**할 수 있다.

### 2. 메서드 레벨(Method Level)로 사용
- `@ModelAttribute`를 메서드에 붙이면, **View에서 사용할 데이터를 설정**하는 용도로 활용할 수 있다.
- `@ModelAttribute`가 설정된 메서드는 `@RequestMapping`이 적용된 **핸들러 메서드보다 먼저 호출**된다.
- 메서드 실행 결과로 리턴되는 객체는 **자동으로 Model에 저장**된다.
- Model에 저장된 객체는 **뷰**(JSP 등) 페이지에서 **바로 사용** 가능하다.

---

## 예시 코드

```java
package com.sp.app.ex21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    1) @ModelAttribute 개요
     : Controller 메소드의 파라미터나 리턴 값을 Model 객체와 바인딩하기 위한 애노테이션
       @ModelAttribute는 ModelMap.addAttribute(이름, 값)와 같은 기능을 한다.

    2) 요청 메소드의 매개변수에 적용
     : 요청 파라미터는 @ModelAttribute가 적용된 메소드의 파라미터(모델 객체)에 매핑되어 넘어온다.
     : @ModelAttribute 적용된 메소드 파라미터는 View 페이지에서 사용 가능하다.

    3) 메소드 레벨(method level)
     : View에서 사용할 데이터를 설정하는 용도로 사용
     : @ModelAttribute가 설정된 메소드는 @RequestMapping이 적용된 메소드보다 먼저 호출
     : 메소드 실행 결과로 리턴되는 객체는 자동으로 Model에 저장
     : View 페이지에서 사용 가능
*/

@Controller("ex21.userController")
@RequestMapping("/ex21/*")
public class UserController {
    private final UserService service;
    
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    // 회원가입 등의 폼을 보여주는 메서드
    @GetMapping("request")
    public String form(Model model) throws Exception {
        // 화면(Form) 구성에 필요한 데이터
        model.addAttribute("memberTypes", service.getMemberTypes());
        model.addAttribute("hakList", service.getHakList());
        
        return "ex21/write";
    }

    // 폼 전송(Submit) 처리 메서드
    @PostMapping("request")
    public String submit(@ModelAttribute("dto") User vo) throws Exception {
        // "dto"라는 이름으로 JSP에 객체가 전달됨
        // (즉, request.setAttribute("dto", vo); 역할)
        
        return "ex21/result"; 
    }
}
```

---

## ***정리***
- @ModelAttribute를 통해, 요청 파라미터가 들어오는 DTO 객체를 쉽고 안전하게 바인딩할 수 있다.
- 메서드 수준에서 사용하면, 공통 데이터(예: select 박스 데이터, 메뉴 항목 등)를 뷰로 넘기기 전에 자동으로 Model에 담을 수 있다.
- 리턴 값에도 사용할 수 있어, 메서드가 반환하는 객체를 뷰에서 직접 사용할 수 있도록 한다.


