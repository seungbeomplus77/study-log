# @RequestParam 애노테이션 관련

## @RequestParam
- **요청 파라미터와 메소드의 매개변수를 매핑**하는 애노테이션
- 기본적으로 **필수(required=true)**이므로, 해당 파라미터가 없으면 400(Bad Request) 에러 발생
- `required = false` 설정 시, 해당 파라미터가 없어도 에러 발생 없이 null 허용
- `defaultValue` 속성을 사용하면, 파라미터가 없을 경우(또는 비어 있을 경우) 기본값을 지정 가능
  - 예) `defaultValue = "기본값"`
- `name(value)` 속성을 통해 실제 요청 파라미터 이름을 지정
  - **Spring 6.1** 이후에는 `@RequestParam` 사용 시, `name`(또는 `value`) 속성을 명시하지 않으면 에러 발생 가능성 있음

---

## 1. 파라미터 이름과 동일한 매개변수

- 요청 파라미터 이름과 **메서드 매개변수의 이름**이 동일하면, 별도의 설정 없이 매핑
- 하지만 **스프링 6.1** 이상에서는 `@RequestParam`으로 명시해주는 방식이 권장됨

***예시(간단 매핑, 스프링 6.1 이전 버전에서 주로 사용)***
```java
  @GetMapping("/example1")
  public String example1(String name, int age, Model model) {
      // "name"과 "age"라는 파라미터가 반드시 존재해야 함
      // 없으면 400 / 500 에러 발생 가능

      model.addAttribute("message", "이름: " + name + ", 나이: " + age);
      return "exampleView";
  }
```

---

## 2. 필수 여부 (required)

- `@RequestParam(name="paramName", required=false)`
- 필수 파라미터가 아니므로, 전송되지 않아도 에러가 발생하지 않음
- 매개변수 자료형이 `String`일 경우, 파라미터가 없으면 `null`이 들어올 수 있음
- 기본형 `int`를 사용할 경우, 파라미터가 없으면 변환 불가 → 에러
- 이때는 `Integer`와 같이 **래퍼 타입** 사용을 고려하거나 `defaultValue` 지정

***예시***
```java
@GetMapping("/example2")
public String example2(
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "age", required = false) Integer age,
        Model model) {

    // name이나 age가 없을 경우 null이 들어올 수 있음
    // 기본형(int)을 사용했다면 파라미터가 없는 경우 에러 발생
    String msg = "이름: " + (name != null ? name : "미입력")
               + ", 나이: " + (age != null ? age : "미입력");
    model.addAttribute("message", msg);
    return "exampleView";
}
```

---

## 3. 기본값 설정 (defaultValue)

- `@RequestParam(name="paramName", defaultValue="기본값")`
- 파라미터가 없거나 비어 있는 경우, `"기본값"`으로 설정
- **예)** `defaultValue="0"`을 설정하면 숫자 파라미터가 넘어오지 않을 때 자동으로 0 할당
- `defaultValue`와 `required=false`를 함께 사용하면, **파라미터가 없어도** 안전하게 기본값을 사용 가능

***예시***
```java
@GetMapping("/example3")
public String example3(
        @RequestParam(name = "name", defaultValue = "손님") String name,
        @RequestParam(name = "age", defaultValue = "0") int age,
        Model model) {

    // name과 age가 전혀 전송되지 않아도, 각각 "손님", 0으로 설정됨
    String msg = "이름: " + name + ", 나이: " + age;
    model.addAttribute("message", msg);
    return "exampleView";
}
```

---

## 4. 파라미터 자료형

**기본형(int, float, double 등)**
- 파라미터가 존재해야만 정상 변환 가능
- 파라미터 누락 시 400 에러 또는 변환 에러 발생
- `defaultValue`로 기본값을 지정하거나, `required=false` + **래퍼 클래스(Integer 등)** 조합을 고려

**래퍼 클래스(Integer, Float, Double 등)**
   - 파라미터가 없으면 `null` 할당 가능
   - 에러 없이 처리할 수 있으나, 실제 로직에서 null 처리를 별도로 해야 할 수도 있음

**String**
- 파라미터가 없으면 `null`이 들어오거나 `defaultValue`가 있으면 그 값이 들어옴
- `required=false` 시, 파라미터가 누락돼도 에러 없이 진행

---

## 5. 파라미터 이름 직접 매핑하기 (name, value 속성)

- `@RequestParam(name = "actualParamName")`
- 서버 코드에서 `actualParamName`과 일치하는 쿼리 파라미터를 매핑
- 외부에서 `"actualParamName"`으로 전송되어야 매개변수에 할당됨
- **스프링 6.1 이상**에서는 파라미터 이름을 지정하지 않으면 경고 또는 에러가 발생할 수 있어, 명시적으로 `name`(또는 `value`) 설정을 권장

***예시***
```java
@GetMapping("/example5")
public String example5(
        // 요청 파라미터 키가 "userName"일 때만 매핑
        @RequestParam(name = "userName") String name,
        // 요청 파라미터 키가 "userAge"일 때만 매핑
        @RequestParam(name = "userAge") int age,
        Model model) {

    String msg = "사용자명: " + name + ", 나이: " + age;
    model.addAttribute("message", msg);
    return "exampleView";
}
```

---

## 6. 특별 케이스 및 참고 사항

1. **파라미터가 매우 많을 경우**
   - 하나하나 `@RequestParam`으로 받기보다, **커맨드 객체(@ModelAttribute)**를 사용해 한 번에 바인딩하는 편이 편리

2. **Map으로 받기**
   - `@RequestParam Map<String,String> map`을 사용하면, 모든 쿼리 파라미터를 `Map` 형태로 받아서 처리 가능
   - **MultiValueMap**을 사용하면, 동일한 파라미터 이름에 여러 값이 들어오는 경우도 처리 가능
   
   ```java
	@GetMapping("/example6")
	  public String example6(@RequestParam Map<String, String> allParams, Model model) {
		// 모든 쿼리 파라미터가 키-값 쌍으로 allParams에 저장
		model.addAttribute("params", allParams);
		return "exampleView";
	}
   ```

3. **파라미터 필드명이 동적으로 결정되는 경우**
   - 동적으로 변화하는 필드명을 처리해야 한다면, 역시 Map 계열이 더 유연

---

## 7. 요약

- **`@RequestParam`**을 통해 파라미터를 간편하게 매핑할 수 있으며,  
- **필수 여부**(`required`)  
- **기본값 설정**(`defaultValue`)  
- **파라미터 이름 매핑**(`name` 또는 `value`) 등을 세밀하게 제어 가능
- **스프링 6.1 이후**에는 파라미터 이름을 명시(`@RequestParam(name="...")`)하는 것이 보안적/호환성 측면에서 권장되며, 실제 사용 시 에러를 예방할 수 있음
- 파라미터 자료형이 **기본형**인 경우와 **래퍼 클래스**인 경우, 처리 방식이 달라질 수 있으므로 주의
- 보다 복잡한 폼 데이터는 **@ModelAttribute**를 사용하거나 **Map**(또는 **MultiValueMap**)으로 처리하는 방식을 고려할 수 있음
- 적은 수의 파라미터는 @RequestParam으로, 많은 수의 파라미터는 커맨드 객체나 맵으로 받는 것을 고려
- 기본형에 defaultValue를 설정하면 누락 시에도 안전하게 값을 대입할 수 있으며, 래퍼 클래스를 사용하면 null을 구분 처리할 수 있습니다.
