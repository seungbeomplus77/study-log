# 간단한 게시판 관련 메서드 및 함수(CRUD)

## 💡 Lessons Learned

### HttpServlet 상속(extends) 받았을 때
- `dog`, `dop` 누르고 컨트롤 스페이스 누르면 `doGet`, `doPost` 메소드 자동 생성

### doGet() / doPost()
- **doGet()**: 브라우저에서 주소창으로 접근하거나 링크를 클릭할 때 호출, 데이터 조회에 주로 사용 (목록보기, 상세보기 등)
- **doPost()**: 폼에서 데이터를 제출할 때 호출, 데이터 생성/수정/삭제에 주로 사용 (글쓰기, 수정하기 등)

### URI
- `cp`부터 끝까지 주소(QueryString은 제외)

### handleRequest()
- 사용자가 어떤 주소로 접근했는지 확인
- **GET 방식일 때**: 요청 방식(GET/POST) 확인
- URI 분석하여 적절한 메서드 호출

### req.setCharacterEncoding("utf-8")
- 한글처리(인코딩)

### viewPage()
- 페이지 처리 방식
- **포워딩 (Forwarding)**: 현재 작업을 가지고 다른 페이지(JSP)로 이동
  - 포워딩은 서버 내부에서 JSP로 처리를 넘기는 방식
  - 예: `viewPage(req, resp, "bbs/list")` → `/WEB-INF/views/bbs/list.jsp`로 이동


- **리다이렉트 (Redirect)**: 새로운 페이지로 이동
  - 리다이렉트는 브라우저가 새로운 주소로 다시 요청하는 방식
  - 예: `viewPage(req, resp, "redirect/bbs/list.do")`

### 서블릿 설정

```java
@WebServlet("/bbs/*")  
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	// 서블릿 코드 작성 시작
}
```
- @WebServlet("/bbs/*"): URL 매핑 어노테이션 /bbs/로 시작하는 모든 웹 요청을 이 서블릿이 처리
- 예: /bbs/list.do, /bbs/write.do 등


# 서블릿 관련 메서드 및 함수

## `extends HttpServlet`: 서블릿의 기본 기능을 상속받음

- `serialVersionUID`: 서블릿의 버전 관리를 위한 ID

## `getRequestDispatcher()`

- **getRequestDispatcher()**: 서블릿과 JSP 간에 요청을 포워딩하거나 포함하는 데 사용
  - 예: `request.getRequestDispatcher("/newPage.jsp");`

## INSERT 문 작성

```sql
INSERT INTO bbs (num, name, pwd, subject, content, hitCount, reg_date, ipAddr)
VALUES (bbs_seq.NEXTVAL, ?, ?, ?, ?, 0, ?, SYSDATE)
```
- 물음표가 다섯 번째 있어도 reg_date 순서가 맞지 않으면 INSERT가 되지 않음.
- insert, update, delete할 때만 throws (예외 catch)
- 테이블이 2개 이상일 때만 예외 처리 필요, 1개일 땐 필요 없음

## 클라이언트 정보
- **getRemoteAddr()** : 클라이언트의 IP 주소를 반환
`request.getRemoteAddr()`

## 문자열 처리
- **equalsIgnoreCase()** : 입력된 영문자 대소문자 구분 없이 비교
`"apple".equals("apple") // → true`

- **indexOf()** : 문자열 내에서 특정 문자가 처음 등장하는 인덱스 반환
`"apple".indexOf("p") // → 1`

- **length()** : 문자열의 길이를 반환
`"Hello".length() // → 5`

- **배열.length** : 배열의 크기(배열에 포함된 요소의 개수)를 반환
`int[] arr = {1, 2, 3}; // arr.length → 3`

## request 객체 메서드

- **setAttribute()** : 객체에 속성을 설정 (요청, 세션, 애플리케이션 범위에서 사용)
`request.setAttribute("key", "value");`

- **getAttribute()** : 설정된 속성의 값을 가져옴
`request.getAttribute("key");`

- **removeAttribute()** : 설정된 속성을 제거
`request.removeAttribute("key");`

- **getParameter(String name)** : 요청 파라미터 값을 가져옴
`request.getParameter("username")`

- **getParameterMap()** : 요청에 포함된 모든 파라미터를 Map 형식으로 반환
`Map<String, String[]> parameters = request.getParameterMap();`

- **getHeader(String name)** : 요청 헤더의 값을 가져옴
`request.getHeader("User-Agent")`

- **getMethod()** : HTTP 요청의 메서드(GET, POST 등)를 가져옴
`request.getMethod() // → "GET"`

- **getRequestURI()** : 요청한 URI를 반환 (쿼리 문자열 제외)
`request.getRequestURI()`

- **getQueryString()** : 요청 URI의 쿼리 문자열을 반환
`request.getQueryString()`

- **getSession()** : 현재 HTTP 세션을 반환 (세션이 없으면 새로 생성)
`HttpSession session = request.getSession();`

- **getCookies()** : 요청에 포함된 모든 쿠키를 반환
`Cookie[] cookies = request.getCookies();`

- **getContextPath()** : 메서드는 서블릿이나 JSP에서 동적으로 컨텍스트 경로를 가져와서, 상대적인 URL을 생성하거나, 환경에 맞게 URL을 만들 때 유용합니다.
`response.getWriter().write("컨텍스트 경로는: " + contextPath); // 결과 나의 경로가 app이면 /app`

## HTML5
`onclick="location.href='주소';": HTML 요소에서 클릭 시 다른 URL로 이동하는 JavaScript 코드`

`<a href="${pageContext.request.contextPath}/bbs/article.do?${query}&num=${nextDto.num}">${nextDto.subject}</a>`

- 버튼이나 a 태그에서 사용 가능
- 주소?이름=값&이름=값&이름
- subject도 이름이 없는데, 단순하게 텍스트로 표시할거라면 이름=값 형식 불필요하다.
- 여기서 query는 이름이 없는데 jsp에서 이름이 없다면 서블릿에서 이름=값 형식을 지정해준거임

```JAVA
// 예시
String query = "page=" + page;  // page=1
if(kwd.length() != 0) {
    query += "&schType=" + schType  // &schType=title
           + "&kwd=" + URLEncoder.encode(kwd, "utf-8");  // &kwd=검색어
}
```
