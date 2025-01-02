# Spring MVC 애노테이션 @RestController, @RequestBody, @ResponseBody
---

## @RestController

- **설명**  
  `@Controller`와 `@ResponseBody`를 합쳐 놓은 애노테이션으로, **API 개발 시**에 주로 사용한다.
  
- **특징**  
  - **REST 방식을 위한 컨트롤러**.
  - 뷰(JSP 등)가 필요 없는 **API**성 서비스를 지원할 때 편리하다.
  - 클래스에 이 애노테이션을 붙이면, 내부 메서드에서 반환한 객체가 **자동으로 HTTP Response Body**로 직렬화되어 전송된다.
  - 스프링 4부터 사용 가능하며, JSON, XML 등 **데이터 포맷** 전송에 최적화된 구조이다.
  - `@RequestMapping`(혹은 `@GetMapping`, `@PostMapping`)의 `produces` 속성으로 **response의 Content-Type**을 제어할 수 있다.

**예시**  
```java
@GetMapping(value = "list", produces = "application/json; charset=utf-8")
public List<String> list() {
	...
}
```

---

## @RequestBody

**설명**
- HTTP 요청의 Body로 들어오는 데이터를 자바 객체로 변환해 받을 때 사용한다.

**특징** 
- 주로 POST 요청에서 폼데이터나 JSON 데이터를 직접 읽어오는 데에 사용한다.
- 예를 들어, JSON 형태로 데이터를 전송하면, @RequestBody가 붙은 DTO(혹은 Map) 객체로 받아올 수 있다.
- @RequestBody를 붙이지 않으면, JSON으로 보낸 정보는 받을 수 없다(기본 파싱이 되지 않음).
- @Controller와 함께 사용할 수도 있고, @RestController에서도 사용할 수 있다.

**예시(JSON -> DTO로 변환)**
```java
@PostMapping("request")
public User submit(@RequestBody User dto) {
    ...
    return dto;
}
```

---

## @ResponseBody

**설명**
- 자바 객체를 HTTP 응답 몸체로 전송, 단순하게 JSON만 쏠 때 유용
- AJAX - JSON 으로 응답
- 컨트롤러 메서드에서 반환한 값을 뷰로 포워딩하지 않고, HTTP Response의 Body로 직접 전송할 때 사용한다.

**특징**
- 자바 객체(예: DTO, Map 등)를 JSON 형태로 변환하여 반환할 때 주로 사용한다.
- @Controller에 주로 붙여 사용하며, 메서드나 클래스에 적용 가능하다.
- @RestController는 내부적으로 @ResponseBody를 포함하므로, 별도로 붙일 필요가 없다.

**예시**  
```java
@ResponseBody
@PostMapping("request")
public User submit(User dto) {
    // ...
    return dto; // JSON 형태로 변환되어 응답
}
```
