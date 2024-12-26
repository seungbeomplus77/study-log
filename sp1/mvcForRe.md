# Spring MVC 포워딩(Forwarding) vs. 리다이렉트(Redirect)

## 포워딩(Forwarding)

### ***정의***
- 서버 내부에서 다른 페이지 혹은 다른 자원으로 제어 흐름을 넘겨, 사용자가 최초 요청한 URL이 바뀌지 않는 방식.
- Spring MVC에서 뷰 이름을 단순 문자열로 반환하거나, ModelAndView를 사용해 뷰 이름만 지정했을 때 기본적으로 포워딩을 수행합니다.

### ***특징***
- Request/Response 객체를 그대로 유지
- 포워딩 시, 동일한 요청/응답 객체를 재사용하므로, 모델에 담았던 정보를 그대로 활용 가능.
- 클라이언트(브라우저) 측에서 URL 변화 없음
- 화면 전환 후에도 URL이 동일합니다.

### ***사용 예시***
```java
// 포워딩 예시 - 문자열로 뷰 이름 반환
@PostMapping("request")
public String submitForm(String num, Model model) {
    model.addAttribute("message", "포워딩 예시");
    return "ex02/result";  // Forwarding
}
```

---

## 리다이렉트(Redirect)

### ***정의***
- 서버가 응답 시, 302 Redirect 상태 코드를 보내 브라우저로 하여금 새로운 요청을 수행하도록 지시하는 방식.
- Spring MVC에서 "redirect:경로" 형태로 반환하면 해당 경로로 새롭게 요청합니다.

### ***특징***
- 새로운 Request/Response, 브라우저가 새 요청을 보내므로, 이전 요청의 Request/Response와 분리됩니다.
- URL 변화, 브라우저 주소창에서 요청 경로가 "redirect:경로"로 변경됩니다.
- CRUD 후, 새로고침 후, 로그인/로그아웃 후, 보안, 새로고침 방지, 폼 전송 처리 후, 새로고침 시 등 중복 요청이 발생하지 않도록 리다이렉트 사용.

### ***사용 예시***
```java
// 리다이렉트 예시
@PostMapping("request")
public String submitForm(String num, Model model) {
    try {
        int n = Integer.parseInt(num);
        // ... 처리 로직
    } catch (NumberFormatException e) {
        return "redirect:/ex02/error"; // Redirect
    }
    return "ex02/result";  // Forward
}
```
