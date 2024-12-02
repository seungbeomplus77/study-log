![서블릿 MVC 구현](https://github.com/user-attachments/assets/480dc025-9468-4f83-997f-8b3c33a28c29)

## 1. 클라이언트 (Web browser)
- 웹 브라우저에서 서버로 요청을 보내고 응답을 받음
- 예: 사용자가 웹페이지에서 버튼을 클릭하거나 URL을 입력

## 2. DispatcherServlet
- 모든 요청을 최초로 받는 프론트 컨트롤러
- 요청을 분석하여 적절한 컨트롤러에게 전달하는 역할
- 컨트롤러의 처리 결과를 알맞은 JSP로 전달하는 역할

## 3. HandlerMapping
- 요청 URL을 어떤 컨트롤러가 처리할지 찾아주는 역할
- `@RequestMapping`과 같은 어노테이션 정보를 통해 알맞은 컨트롤러 메서드 찾음

## 4. Controller
- 실제 요청을 처리하는 비즈니스 로직 수행
- 처리 결과 데이터와 보여줄 JSP 페이지 정보를 `ModelAndView` 객체에 담아 반환

## 5. View (JSP)
- 실제 사용자에게 보여질 페이지를 만드는 역할
- 컨트롤러가 전달한 데이터를 사용해 HTML 생성

## 처리 순서:
1. 클라이언트가 요청 전송
2. DispatcherServlet이 HandlerMapping에게 요청을 처리할 컨트롤러 검색
3. 해당 컨트롤러로 요청 전달
4. 컨트롤러가 로직을 처리하고 결과 데이터와 JSP 페이지 정보를 `ModelAndView`로 반환
5. DispatcherServlet이 받은 JSP 페이지에 데이터를 전달하여 최종 HTML 생성
6. 생성된 HTML을 클라이언트에게 응답
