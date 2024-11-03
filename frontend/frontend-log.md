# Front-end 개발일지

## 2024-11-01

- **작업 내용**: 간단한 연산이 가능한 계산기
- **사용 기술**: HTML5
- **진행 상황**: 수식을 입력하는 텍스트 박스를 만들고, 확인 버튼 클릭시 연산.
- **문제 분석**: 연산자를 입력하지않고 숫자만 입력할 경우 그 숫자가 그대로 나옴, eval() 함수와 innerHTML 속성은 보안상의 위험이 있음. 
- **해결 방법**: innerHTML : 사용자 입력을 출력하기 전에 반드시 적절한 예외처리를 해줘야 함.
			  : eval() : 수식 라이브러리를 사용하거나, 정규식 표현식으로 검증을 거쳐야 함.

## 코드
```HTML5
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>

	<h3>간단한 계산기</h3>
	<div>
		<input type="text" id="inputs" placeholder="수식을 입력하세요"> <!-- 수식을 입력할 텍스트 박스 -->
		<button type="button" onclick="result();">확인</button> <!-- 확인 버튼 클릭 시 result() 함수 호출 -->
	</div>
	<hr>
	<div id="resultLayout"></div> <!-- 계산 결과를 출력할 영역 -->
	
	<script type="text/javascript">
	// 결과를 계산하고 출력하는 함수
	function result() {
		const inputs = document.querySelector('#inputs'); // css 선택자 id inputs에 입력된 수식을 가져오는 요소
		const layout = document.querySelector('#resultLayout'); // 결과를 표시할 요소
		
		let s = inputs.value.trim(); // 입력값에서 공백 제거
		if( ! s ) { // 입력값이 비어있으면
			alert('수식을 입력하세요.'); // 경고 메시지 표시
			inputs.focus(); // 입력 필드에 포커스
			return; // 함수 종료
		}
		
		// 입력된 수식과 계산 결과를 출력
		let out = `${s} = ${eval(s)}`; // eval()을 사용해 수식을 계산
		layout.innerHTML = out; // 결과를 layout에 표시
	}
	</script>

</body>
</html>

### 개념 정리
eval() : 주어진 문자열을 javascript 코드로 해석하여 실행.
innerHTML : HTML 요소의 내용을 가져오거나 설정하는 데 사용, 내부 HTML 구조를 동적으로 변경 할 수 있음.
document.querySelector() : CSS 선택자(id,class,tag 등) 구문을 사용하여 해당하는 첫번째 요소만 반환 없으면 null을 반환함.