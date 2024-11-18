## <c:forEach> 태그

```JSP
<c:forEach var="date" begin="1" end="${week-1}">
```
```JSP
<c:forEach var="date" begin="1" end="5" varStatus="vs">
   ${vs.count} // 1,2,3,4,5 로 증가
   ${vs.index} // 0,1,2,3,4 로 증가
</c:forEach>
```
- var: 반복문 안에서 사용할 변수 이름을 지정합니다 (여기서는 'date'라는 이름으로 지정)
- begin: 반복 시작 숫자 (1부터 시작)
- end: 반복 끝 숫자 (여기서는 week-1까지)
- varStatus: 반복의 상태를 담는 변수 (현재 몇 번째 반복인지 등의 정보를 담음)
- 증가분은 step 속성을 이용하며 1이면 생략 가능

## <c:choose>, <c:when>, <c:otherwise> 태그
```JSP
<c:choose>
    <c:when test="${year == ty && month == tm && date == td}">
        <td class="today">${date}</td>
    </c:when>
    <c:otherwise>
        <td>${date}</td>
    </c:otherwise>
</c:choose>
```
- Java의 if-else와 같은 역할
- <c:choose>: switch문과 비슷
- <c:when test="">: if 또는 case문과 비슷. test에 조건을 넣음
- <c:otherwise>: else 또는 default와 비슷. 모든 when이 실패했을 때 실행

## <c:if> 태그
```JSP
<c:if test="${date != lastDate && (week + vs.count - 1) % 7 == 0}">
    </tr><tr>
</c:if>
```
- 단순 if문과 같음
- test 속성에 조건식을 넣음
- EL(${})을 사용해서 조건을 체크

## <c:set> 태그
- set - 표현식의 결과를 특정 범위(세션,리퀘스트)에 저장

## <c:out> 태그
```JSP
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<%
	String s = "<p style='color:red;'>자바</p>";
	pageContext.setAttribute("msg", s);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>

	<h3>c:out - 표현식의 결과를 출력</h3>
	
	<p> EL을 이용한 출력 : 글자색은 red로 출력 </p>
	<p> ${msg} </p>
	<hr>
	
	<p> c:out 출력 : 기본은 escapeXml 속성이 true로 태그 문자로 출력 </p>
	<p> <c:out value="${msg}"/> </p>
	<hr>
	
	<p> c:out 출력 : escapeXml 속성이 false </p>
	<p> <c:out value="${msg}" escapeXml="false"/> </p>
	<hr>

</body>
</html>
```
- 표현식의 결과를 출력

## <c:url>
- url을 만들며, URL 인코딩 또는 URL 형식 지정에 사용
- 주소 앞에 / 가 붙으면 자동으로 Context path가 추가

## <c:param>
- c:import, c:url 태그에 파라미터를 전달하기 위해 사용

## <c:import>
```JSP
<h3>내부 자원 포함</h3>
	 <c:set var="url" value="jsp파일명"/>
	 <c:import var="u" url="${url}"/>
	 
	 <h4><c:out value="${url}"/> 소스보기 </h4>
	 <c:out value="${u}"/>
	 <hr>
	 
	 <h4><c:out value="${url}"/> 실행결과 </h4>
	 <c:out value="${u}" escapeXml="false"/>
	 <hr>
	 
	 <h3>c:import 태그에서 var이 없으면 바로 실행</h3>
	 <c:import url="jsp 파일명"/>

<h3>외부 자원 가져오기</h3>
	 <c:set var="url" value="https://www.google.com"/>
	 <c:import var="u" url="${url}"/>
```
- 서버 내부 및 서버 외부의 자원을 포함 할 수 있다.
## EL(Expression Language) 표현식
```JSP
   <p> ${ 3 + 5 } </p> <!--  8 : Long -->
	<p> ${ 3 + "5" } </p> <!--  8 : Long -->
	<p> ${ "3" + "5" } </p> <!--  8 : Long -->
	<p> ${ 3 + null } </p> <!--  3+0 = 3 : Long -->
	<p> ${ 10 / 5 }</p> <!-- 2.0, div -->
	<p> ${ 13 % 5 }</p> <!-- 3, mod -->
	<hr>
	
	<p> 3 == 4 : ${ 3 eq 4 }, ${ 3 == 4 } </p>
	<p> empty name : ${ empty name } </p> <!-- name 속성이 없으므로 true -->
	<p> ${ 10 mod 2 == 0 ? "짝수" : "홀수" } </p> 
	<hr>
	
	<p> 문자열 결합 : +=, ${ "서울" += "경기" } </p>
	
	<p> 세미콜론 연산자 - a;b 에서 a는 출력되지 않고 b만 출력 </p>
	<p> ${1+2; 2+5} </p> <!-- 7 --> 
	<p> ${a=10} ${a} </p> <!-- 10 10 --> 
	<p> ${a=10; a} </p> <!-- 10 --> 
```
```JSP
   <h3> EL 내장 객체 </h3>
	<p> pageContext : 현재 페이지 정보 </p>
	<p> Context path : <%= request.getContextPath() %> </p>
	<p> Context path : ${pageContext.request.contextPath} </p>
	<hr>
	
	<p> 헤더 정보 </p>
	<p> User-Agent : ${header["user-agent"] } </p>
```
```JSP
<h3>EL을 사용하지 않는 경우</h3>
	<%
       request.setCharacterEncoding("utf-8");
		
	    User vo = (User)request.getAttribute("dto");
		String city = request.getParameter("city");
	%>
	<p> <%=vo.getName() %>, <%=vo.getAge() %>, <%=vo.getTel() %>, <%=vo.getSubject() %>,
	<%= city %> </p>
	
<h3>EL을 사용한 경우</h3>
	<!-- 값이 null이면 아무것도 출력되지 않음 -->
	<p>
		${dto.name}, ${dto.age}, ${dto.tel}, ${dto.subject}
	</p>
	<!-- param 내장 객체 : request.getParameter("이름") 과 유사 -->
	<p>
		${param.city}
	</p>
```
- ${}로 감싸서 사용.
- 변수 값을 출력하거나 간단한 계산을 수행.
- request, pageContext, session 등에서 설정한 속성(attribute) 을 출력하기 위해 사용.
