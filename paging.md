## 페이징 처리 과정

- 한 페이지에 출력할 데이터 개수 : 10개
- 전체 데이터 개수 : 965개

- 전체 페이지 수 : 97
- 자바
```JAVA
전체 페이지 수 = 젠체 데이터 개수 / 10 + (전체데이터개수%10==0 ? 0 : 1);
```
- 자바스크립트
```JavaScript
Math.ceil(전체 데이터 개수 / 10);
Math.floor(전체 데이터 개수 / 10) + (전체 데이터개수%10 == 0 ? 0 : 1);
```
- 1페이지 : 1~10
- 2페이지 : 11~20
- 3페이지 : 21~30
	.
	.
	.
- ORACLE 11g
	시작 : (페이지-1) * 10 + 1; 
	끝 : 페이지 * 10;

- ORACLE 12C 이상
	시작 : offset(건너뛰는 개수) = (페이지 - 1) * 10
	size = 10
## 페이징
-GET 방식
: 주소?이름=값&이름=값

	[이전] : 앞으로 10 페이지 이동
	[다음] : 뒤로 10 페이지 이동
	
- 1~10 페이지 중 한 페이지가 보이는 경우
	1 2 3 4 5 6 7 8 9 10 [다음] 97

- 51-60 페이지 중 한 페이지가 보이는 경우
	1 [이전] 51 52 53 54 55 56 57 58 59 60 [다음] 97 

- 91-97 페이지 중 한 페이지가 보이는 경우
	1 [이전] 91 92 93 94 95 96 97

- a 태그 : 52 페이지가 보이는 경우
		<a href="list.jsp?page=1">1</a>
		<a href="list.jsp?page=42">[이전]</a>
		<a href="list.jsp?page=51">51</a>
		<span>52</<span>
		<a href="list.jsp?page=53">53</a>
		<a href="list.jsp?page=54">54</a>
		<a href="list.jsp?page=55">55</a>
		<a href="list.jsp?page=56">56</a>
		<a href="list.jsp?page=57">57</a>
		<a href="list.jsp?page=58">58</a>
		<a href="list.jsp?page=59">59</a>
		<a href="list.jsp?page=60">60</a>
		<a href="list.jsp?page=62">[다음]</a>
		<a href="list.jsp?page=97">97</a>

- a 태그가 페이지 이외의 다른 파타미터가 있는경우
		<a href="list.jsp?condition=subject&keyword=java$page=1"<1></a>

- currentPageSetUp : 표시 할 첫 페이지 - 1
- current_page : 화면에 표시 할 페이지

	current_page : 3 => 1 ~ 10
		currentPageSetUp : 0
		
	current_page : 47 => 41 ~ 50
		currentPageSetUp : 40
		
			current_page : 60 => 51 ~ 60
		currentPageSetUp : 50
		
- ORACLE 11g
	-- 형식
	SELECT * FROM (
		SELECT ROWNUM rnum, tb, *FORM (
			SELECT 컬럼,컬럼
			WHERE 조건
			ORDER BY 컬럼 DESC
		) tb WHERE ROWNUM <= 끝
	) WHERE num >= 시작

- ORACLE 12c
	-- 형식
	SELECT 컬럼, 컬럼
	FROM 테이블
	WHERE 조건
	ORDER BY 컬럼 DESC
	OFFSET 건너 띌 개수 ROWS FETICH FIRST 가져 올 수 있게 ROWS ONLY;

- Maria DB(MY SQL)
	SELECT 컬럼, 컬럼
	FROM 테이블
	WHERE 조건
	ORDER BY 컬럼 DESC
	LIMIT 건너 띌 개수, 가져 올 개수;