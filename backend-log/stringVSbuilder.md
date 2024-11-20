# String 과 StringBuilder 의 차이

## 💡 Lessons Learned

## 📅 작성일: 2024-11-20

String은 문자열을 수정할 때마다 새로운 공간을 만듬.
```JAVA
String name = "홍";  // 메모리 공간 1
name += "길동";     // 새로운 메모리 공간 2
name += "님";      // 새로운 메모리 공간 3
// "홍길동님" 만드는데 메모리 공간 3개 사용
```

StringBuilder는 하나의 공간에서 계속 수정.
```JAVA
javaCopyStringBuilder name = new StringBuilder("홍"); // 메모리 공간 1
name.append("길동");  // 같은 공간 사용
name.append("님");   // 같은 공간 사용
// "홍길동님" 만드는데 메모리 공간 1개만 사용
```

```JAVA
// 사용 예시
StringBuilder query = new StringBuilder();
query.append("page=").append(page);        // page=1
query.append("&name=").append(name);       // page=1&name=홍길동
query.append("&age=").append(age);         // page=1&name=홍길동&age=20

String result = query.toString();  // 최종 문자열로 변환
```

- 문자열을 많이 수정하거나 붙여야 할 때는 StringBuilder를 쓰면 더 효율적!

**StringBuilder 에서 주로 사용되는 메서드**
- append(): 문자열 끝에 추가
- insert(): 특정 위치에 삽입
- delete(): 특정 부분 삭제
- reverse(): 문자열 뒤집기
- toString(): String으로 변환
