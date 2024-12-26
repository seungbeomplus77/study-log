# Spring MVC ModelAndView vs. Model

## ModelAndView
- 뷰 이름과 모델(데이터)을 하나의 객체(ModelAndView)에 담아서 반환.

## 사용예시
```java
ModelAndView mav = new ModelAndView("viewName");
mav.addObject("key", value);
return mav;
```

---

## Model
- 컨트롤러 메서드의 파라미터로 Model을 받으면, 해당 Model 객체에 데이터를 담은 뒤, 단순히 뷰의 이름만 반환하는 방식.

## 사용예시
```java
public String someMethod(Model model) {
    model.addAttribute("key", value);
    return "viewName";
}
```