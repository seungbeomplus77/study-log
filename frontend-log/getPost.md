# Front-end 개발일지

## 📅 작성일: 2024-11-11

## 📝 Task
- JSP 파라미터 전송 방식 GET

## 🛠️ Tech Stack
- HTML5
- CSS
- JavaScript
- JSP

## 💡 Lessons Learned

### 1. GET
- 무조건 get으로 전송 post로 가는경우는 극히 드물다, get 전송 방식은 주로 a태그를 사용하며, form 태그를 사용하기도 한다.
- 형식 : 주소?이름=값&이름=값 이며 값 제외 모든 주소 란에는 공백이 들어가면 안된다.
- ok.jsp?name=홍길동&age=20 이런 식의 형식이다.
- get 방식으로 전송할 때 특수문자 등은 반드시 인코딩을 해야한다.
- ok.jsp?name=홍%동&age=20 이러한 형식은 인코딩이 반드시 필요한 경우이다.

### 2. Query String
- GET 방식으로 전송되는 파라미터이다.
- 주소줄에 정보를 실어 보낸다.
- 형식 : 주소?이름=값&이름=값

### 3. 구체적인 GET 전송 방법 및 예시
- 우선 코드에서 보시다시피 보내기를 3개를 만들어 놓았다
- 한개는 a태그를 활용한 `<p> <a href="ok.jsp?name=홍길동&age=20">보내기</a> </p>`

- 그리고 두번째는 이름에 특수문자가 들어갔을 경우 JSP를 활용해 인코딩을 한 아래 코드이다

`<p> <a href="ok.jsp?name=<%= URLEncoder.encode("홍%동", "utf-8") %>&age=20">보내기</a> </p>`

- 그리고 마지막은 아래의 코드 에서처럼 자바 스크립트를 이용, 이름에 특수문자가 들어갔을 경우 인코딩하여 전송한 방법이다.
```
	<p> <button type="button" onclick="sendOk();"> 보내기 </button> </p>
	
	<script type="text/javascript">

	function sendOk() {
		let url = 'ok.jsp';

		let name = '홍%바';
		name = encodeURIComponent(name);
		let age = 20;
		let query = 'name=' + name + '&age=' + age;
		// let query = `name=${name}&age=${age}`;
		location.href = url + '?' + query;
	}
	</script>
```

### 4. ok.jsp 파일과 get.jsp 파일을 구분하는 의미
- 이클립스 기준으로 ok.jsp 파일이 get.jsp와 같은 폴더 안에 있어야한다. 
- get.jsp가 클라이언트고, ok.jsp가 클라이언트가 보낸 파라미터 값들을 받는다고 생각하면 쉽다.
## ⚠️ Issues

### 1. 아직 AWS 배포 방식을 배우지 못해 jsp파일 실행 결과를 캡쳐로 보여준게 아쉽다.
- GitHub page는 정적인 파일들만 올릴수 있다고 알고 있기에.. 아쉬웠던 개발이였다


## 📄 Result

- 사진 순서는 
- 3가지 get 전송 링크 및 버튼
- a태그의 url, 결과
- jsp를 이용해 인코딩 한 코드의 url, 결과
- 마지막으로 JavaScript를 이용해 인코딩 한 코드의 url, 결과이다.

![3가지 get 방식](https://github.com/user-attachments/assets/cf0e1b9e-733a-4e93-9f91-53f1880ea97e)

![a태그 URL](https://github.com/user-attachments/assets/43ada6c7-404e-4f9c-a77a-6ad9f731d583)

![a태그 결과](https://github.com/user-attachments/assets/e7dea513-3d63-4ed5-a51e-73e82a4eca34)

![jsp URL](https://github.com/user-attachments/assets/a65abbbd-af65-489a-a807-5307f8aaa9fb)

![jsp 결과](https://github.com/user-attachments/assets/03d152d5-b4fa-4d72-8fb3-74d71c25e1eb)

![js URL](https://github.com/user-attachments/assets/a7a78759-445e-47d5-9392-9ba6e0d13fb2)

![js 결과](https://github.com/user-attachments/assets/f768dd22-5336-49fc-91e6-797b7dcde929)



## 📄 Code

<details>
<summary>get.jsp와 ok.jsp 코드</summary>

```
	// get.jsp 파일의 코드 시작
	<%@page import="java.net.URLEncoder"%>
	<%@ page contentType="text/html; charset=UTF-8"%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="icon" href="data:;base64,iVBORw0KGgo=">
	
	<script type="text/javascript">
	
	function sendOk() {
		let url = 'ok.jsp';
		// let query = 'name=이%바&age=20'; // 인코딩 안해서 이름이 null로 전송
		
		// 스크립트에서도 반드시 한글등은 인코딩해서 보내야 한다.
		let name = '홍%바';
		name = encodeURIComponent(name);
		let age = 20;
		let query = 'name=' + name + '&age=' + age;
		// let query = `name=${name}&age=${age}`;
		location.href = url + '?' + query;
	}
	</script>
	</head>
	<body>
	
		<p> <a href="ok.jsp?name=홍길동&age=20">보내기</a> </p>
		
		<p> <a href="ok.jsp?name=<%= URLEncoder.encode("홍%동", "utf-8") %>&age=20">보내기</a> </p>
		
		<p> <button type="button" onclick="sendOk();"> 보내기 </button> </p>
	</body>
	</html>
	// get.jsp 파일의 코드 끝
	
	// ok.jsp 파일의 코드 시작 부분
	
	<%@ page contentType="text/html; charset=UTF-8"%>
<%
	// 클라이언트가 보낸 파라미터 받기
	String name = request.getParameter("name");
	int age = Integer.parseInt(request.getParameter("age"));
	
	String state = age >= 19 ? "성인" : "미성년자";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>

	<p> 이름 : <%= name %> </p>
	<p> 나이 : <%= age %>, <%= state %> </p>

</body>
</html>
```






