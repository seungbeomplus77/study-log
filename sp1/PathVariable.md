# ***@PathVariable 애노테이션***

## ***개요***
- **@PathVariable**은 메서드 매개변수에 **URI 템플릿 변수**(`{}` 구문)를 바인딩해야 함을 나타내는 애노테이션이다.  
- Spring MVC에서 요청 URI 매핑의 템플릿 변수를 처리하고, 이를 메서드 매개변수로 사용할 수 있다.  
- 주로 **상세 조회, 수정, 삭제**와 같은 작업에서 리소스 식별자로 사용된다.

## 특징
1. **메서드 매핑**  
   - @RequestMapping, @GetMapping, @PostMapping 등과 함께 사용할 수 있다.  
   - 경로 변수는 **중괄호** `{variableName}`로 감싸진 값을 나타낸다.

2. **정규식**  
   - 정규식을 사용하여 경로 변수를 제한할 수 있다.  
   - 예: 
     - `@GetMapping("/user/{age:\\d+}")` : **숫자**만 허용  
     - `@GetMapping("/user/{name:\\D+}")` : **숫자 제외** 허용

3. **다중 사용**  
   - `{}` 구문을 여러 개 두어, 여러 개의 변수를 한 메서드에서 받을 수 있다.  
   - 예: 
     ```java
     @GetMapping("/users/{userId}/orders/{orderId}")
     public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
         // ...
     }
     ```

4. **Map으로 받기**  
   - 메서드의 매개변수가 `Map<String, String>`이면, **모든 경로 변수의 이름과 값**이 맵에 저장된다.

5. **이름 생략**  
   - @PathVariable의 이름이 **메서드 파라미터 이름**과 같다면, `@PathVariable("paramName")`를 **생략**할 수 있다.

---

## ***예시 코드***

```java
package com.sp.app.blog;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    - @PathVariable 애노테이션
      : 메소드 매개 변수가 URI 템플릿 변수({} 안에 변수)에 바인딩되어야 함을 나타내는 애노테이션 이다.
        요청 URI 매핑의 템플릿 변수를 처리하고 이를 메소드 매개 변수로 사용할 수 있다.
      
      : @RequestMapping 애노테이션이 있는 핸들러 메소드에 지원된다.
      : 경로 변수는 중괄호 {id}로 둘러싸인 값을 나타낸다
      : 정규식 사용이 가능하다.
        @GetMapping("/user/{age:\\d+}")  - 숫자만 가능
        @GetMapping("/user/{name:\\D+}")  - 숫자 제외
      
      : @PathVariable을 다중으로도 사용 가능하다.
         @GetMapping("/users/{userId}/orders/{orderId}")
         public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
      : 메소드 매개 변수가 Map <String, String>이면 Map이 모든 경로 변수 이름 및 값으로 채워 진다.
      : 주로 상세 조회, 수정, 삭제와 같은 작업에서 리소스 식별자로 사용된다.
 */

@Controller
@RequestMapping("/blog/*")
public class BlogController {
    private final BlogService service;
    
    @Autowired
    public BlogController(BlogService service) {
        this.service = service;
    }
    
    @GetMapping("main")
    public String main(Model model) throws Exception {
        model.addAttribute("list", service.listBlog());
        return "blog/list";
    }
    
    // @PathVariable : 이름과 파라미터의 이름이 같은 경우 이름 생략 가능
    @GetMapping("{blogIdx}/home")
    public String blogHome(
            @PathVariable("blogIdx") long blogIdx,
            Model model) throws Exception {
        
        try {
            Blog dto = Objects.requireNonNull(service.findById(blogIdx));
            // Objects.requireNonNull : 인자가 null이면 NullPointerException을 던진다.
            // if 문 대신 null 검사를 수행하며, 코드 가독성을 높인다.
            
            model.addAttribute("dto", dto);
        
        } catch (NullPointerException e) {
            return "redirect:/blog/notFound";
        } catch (Exception e) {
            return "redirect:/blog/main";
        }
        return "blog/home";
    }
    
    @GetMapping("notFound")
    public String notFound() throws Exception {
        return "blog/error";
    }
}
```

---

## ***정리***
- @PathVariable은 URL 경로의 일부를 메서드 파라미터로 매핑해 주는 매우 편리한 기능이다.
- 자주 사용되는 케이스는 /{id}, /{userId}, /{userId}/orders/{orderId} 등과 같은 자원 식별자를 포함한 URL을 다룰 때이다.
- 정규식을 활용하여 유효성 체크도 가능하다.
- 이름이 동일하면 @PathVariable("변수명")에서 괄호 안 문자열을 생략할 수 있어 코드를 더 간결하게 만들 수 있다.
