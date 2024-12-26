<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>

	<h3>파라미터 받기</h3>
	
	<form action="<c:url value='/ex12/request'/>" method="post">
		<p> 이름 : <input type="text" name="name"> </p>
		<p> 나이 : <input type="text" name="age"> </p>
		<p>
			좋아하는 과목...<br>
			<input type="checkbox" name="subjects" value="java"> 자바<br>
			<input type="checkbox" name="subjects" value="oracle"> 오라클<br>
			<input type="checkbox" name="subjects" value="servelt"> 서블릿<br>
			<input type="checkbox" name="subjects" value="spring"> 스프링<br>
			<input type="checkbox" name="subjects" value="springboot"> 스프링부트
		</p>
		<p>
			<button type="submit"> 전송하기 </button>
		</p>
	</form>
</body>
</html>