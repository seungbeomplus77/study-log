## <c:forEach> 태그

```JSP
<c:forEach var="date" begin="1" end="${week-1}">
```
- var: 반복문 안에서 사용할 변수 이름을 지정합니다 (여기서는 'date'라는 이름으로 지정)
- begin: 반복 시작 숫자 (1부터 시작)
- end: 반복 끝 숫자 (여기서는 week-1까지)
- varStatus: 반복의 상태를 담는 변수 (현재 몇 번째 반복인지 등의 정보를 담음)

```JSP
<c:forEach var="date" begin="1" end="5" varStatus="vs">
   ${vs.count} // 1,2,3,4,5 로 증가
   ${vs.index} // 0,1,2,3,4 로 증가
</c:forEach>
```

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

## EL(Expression Language) 표현식
```JSP
${preDate + date - 1}
```
- ${}로 감싸서 사용
- 변수 값을 출력하거나 간단한 계산을 수행, JSTL 값을 출력하려고 사용
- pageContext, request, session 등에 저장 된 내부 객체 속성에 접근 가능
- ${year} 는 pageContext.getAttribute("year")와 같음
