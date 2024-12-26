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

	<h3> 계산기 </h3>
	
	<form action="<c:url value='/calc/main' />" method="post">
		<input type="text" name="num1" required pattern="\d+">
		<select name="operator">
			<option value="+">더하기</option>
			<option value="-">빼기</option>
			<option value="*">곱하기</option>
			<option value="/">나누기</option>
		</select>
		<input type="text" name="num2" required pattern="\d+">
		<button type="submit">결과</button>
	</form>

</body>
</html>
