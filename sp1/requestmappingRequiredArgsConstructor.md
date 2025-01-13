# `@RequiredArgsConstructor`
- Lombok에서 제공하는 어노테이션으로, final이나 `@NonNull`이 붙은 필드에 대해 생성자를 자동으로 생성 해줌.

**예시**
```java
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final EmailSender emailSender;
}
```

이 코드는 다음과 같은 생성자를 자동으로 만들어 줌.
---

```java
public MemberService(MemberRepository memberRepository, EmailSender emailSender) {
    this.memberRepository = memberRepository;
    this.emailSender = emailSender;
}
```
- 생성자 주입할때, 생성자를 직접 작성하지 않아도 되서 코드가 간결해짐.

---

# `@RequestMapping`
- 스프링 MVC에서 요청 URL과 컨트롤러 메서드를 매핑하는 데 사용됩니다.
- 하위 메서드에서 세부적인 URL과 HTTP 메서드를 설정할 수 있습니다.
**예시**
```java
@RestController
@RequestMapping(value = "/member/*")
public class MemberController {

    @GetMapping("/info")
    public ResponseEntity<String> getMemberInfo() {
        return ResponseEntity.ok("Member info");
    }
}
```
---
- value 속성 : 요청 URL 패턴을 지정. 위 코드의 `/member/*` 는 `/member/` 로 시작하는 모든 요청을 이 컨트롤러가 처리.
- info는 클래스 레벨 경로에서 상대 경로이고, 최종 경로는 `/member/info` 가 됨.

