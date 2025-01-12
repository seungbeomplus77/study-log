# 인터셉터란?
- HandlerInterceptor 인터페이스를 구현하여 만든 클래스입니다.
---
## **HandlerInterceptor**
- 컨트롤러가 요청하기 전과 후에 반복적인 기능을 수행합니다.
- 인터셉터를 이용해서 로그인 기능을 구현 할수있음.
---
## **HandlerInterceptor** 인터페이스에 정의 된 3가지 메서드는 다음과 같다

# `preHandle()`
- 클라이언트 요청이 컨트롤러에 도착하기 전에 호출하며 false를 리턴하면 HandlerInterceptor 또는 컨트롤러를 실행하지 않고 요청을 종료.
