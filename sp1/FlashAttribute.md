# Flash Attribute (RedirectAttributes, FlashMap) 어노테이션

Spring MVC에서 **Flash Attribute**는 다음과 같은 특징을 갖는다:

1. **RedirectAttributes**를 사용하여 데이터 등을 저장하면 **Redirect** 된 후 즉시 사라지게 된다.  
   - 사용자가 F5 등으로 **새로고침**(리로드)하더라도 서버로 다시 **submit**되어 데이터가 중복으로 저장되지 않는다.

2. **RedirectAttributes**을 이용하면 리다이렉트 시 값 전달을 쿼리 스트링 등 파라미터로 하지 않고, 임시 **플래시 메모리**를 사용하여 **보이지 않게** 저장하고, 사용 후 삭제한다.  
   - 즉, 리다이렉트 시 데이터를 **숨겨서 넘기는 방법**을 제공한다.

---

## 예시 코드

```java
package com.sp.app.ex23;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("ex23.userController")
@RequestMapping("/ex23/*")
public class UserController {
    
    @GetMapping("request")
    public String form() {
        return "ex23/write";
    }
    
    @PostMapping("request")
    public String submit(User dto,
            final RedirectAttributes rAttr) {
        // DB 작업 수행 (가정)

        // 리다이렉트 된 페이지에 값 넘기기
        String s = dto.getName() + "님 안녕하세요~~~";
        
        rAttr.addFlashAttribute("dto", dto);
        rAttr.addFlashAttribute("message", s);
        
        // 리다이렉트 시 "dto", "message" 값들이 임시로 저장되어 이동
        return "redirect:/ex23/complete";
    }
    
    @GetMapping("complete")
    public String result(@ModelAttribute("dto") User dto) {
        
        /*
         - result() 메소드 괄호 안에 매개변수가 없어도,
           view에서는 addFlashAttribute()에서 설정한 속성(값)을 바로 출력할 수 있다.
           
         - @ModelAttribute("dto")를 붙이지 않으면 콘솔에서 null이 출력된다.
           
         - JSP에서 출력되는 내용은 위의 rAttr.addFlashAttribute("dto", dto)에 의해 전달된 데이터다.
        */
        
        System.out.println("확인 : " + dto.getName());
        // 새로고침 후 다시 리로드하면 dto가 초기화되어 null이 출력된다.
        
        return "ex23/result";
    }
}
```

---


## 정리
- Flash Attribute는 일회성 데이터를 전달하기에 적합하다.
예: 성공 메시지, 오류 메시지, 임시 폼 데이터 등.
- RedirectAttributes와 결합해 쓰면, Redirect 시 임시 메모리를 사용하여 데이터를 간단하고 안전하게 전달할 수 있다.
- ModelAttribute로 뷰에서 바로 해당 Flash Attribute 데이터를 받아 사용 가능하다.
- Flash Attribute는 리다이렉트 이후에 사라지므로, 지속적으로 유지해야 하는 데이터에는 적합하지 않다.  
- 더 오래 유지해야 할 데이터에는 세션 등에 저장하는 방법을 사용해야 한다.
