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

	<h3>@RequsetMapping : url 을 class 또는 메소드와 mapping</h3>
	<p> ${message} </p>
	<hr>
	
	<p>
		<a href="${pageContext.request.contextPath}/ex01/request">GET 요청</a>
	</p>
	<hr>
	
	<form action="${pageContext.request.contextPath}/ex01/request" method="post">
		<button type="submit">POST 요청</button>
	</form>
</body>
</html>