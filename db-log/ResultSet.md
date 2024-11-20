# Java `ResultSet`과 반복 처리
## 📅 작성일: 2024-11-20
## 💡 Lessons Learned
### `ResultSet`이란?
- **`ResultSet`**: 데이터베이스 쿼리 실행 결과를 테이블 형식으로 저장하는 **Java 객체**입니다.
- SQL 쿼리의 실행 결과를 행(row) 단위로 접근할 수 있도록 제공됩니다.
- 내부적으로 **커서(cursor)**를 사용해 결과를 탐색합니다.

---

## `while (rs.next())`가 필요한 이유

### 1. `ResultSet`의 기본 동작
- `ResultSet`은 내부적으로 **커서(cursor)**를 사용하여 행을 탐색합니다.
- **`rs.next()`**:
  - 커서를 다음 행으로 이동합니다.
  - 이동한 행이 있으면 `true`를 반환하고, 더 이상 행이 없으면 `false`를 반환합니다.

### 2. `while` 반복문 사용
- `while` 루프는 결과 테이블의 모든 행을 탐색하면서 데이터를 처리하기 위해 사용됩니다.
- 각 행의 데이터를 추출하여 DTO(Data Transfer Object) 객체로 변환하고, 리스트에 추가합니다.

---

## 작동 과정
`while (rs.next())`는 다음과 같은 단계를 거칩니다

1. **초기 상태**
   - `ResultSet` 커서는 첫 번째 행 이전에 위치합니다.
   - 데이터를 읽으려면 커서를 이동해야 합니다.

2. **루프 시작**
   - `rs.next()`를 호출하면 커서를 다음 행으로 이동합니다.
   - 각 행의 데이터를 DTO 객체로 저장합니다.

3. **마지막 행 이후**
   - 커서가 마지막 행까지 이동한 후 더 이상 데이터가 없으면 `rs.next()`는 `false`를 반환하고 루프가 종료됩니다.

---

## 코드 예제

```java
while (rs.next()) { // 각 행에 대해 반복
    GuestDTO dto = new GuestDTO(); // 새로운 DTO 객체 생성
    dto.setNum(rs.getInt("num")); // 현재 행에서 "num" 컬럼 값 가져오기
    dto.setName(rs.getString("name")); // "name" 컬럼 값 가져오기
    dto.setContent(rs.getString("content")); // "content" 컬럼 값 가져오기
    dto.setReg_date(rs.getString("reg_date")); // "reg_date" 컬럼 값 가져오기
    list.add(dto); // 리스트에 DTO 추가
}
```

## 왜 반복문을 써야하는지?
1. 결과가 여러 행일 수 있음
- 데이터베이스 쿼리 결과는 하나 이상의 행(row)을 반환할 수 있습니다.
- 모든 행을 처리하려면 반복문이 필요합니다.

2. ResultSet은 순차 접근만 가능
- ResultSet은 한 번에 하나의 행만 참조할 수 있습니다.
- 커서를 이동하지 않으면 데이터를 읽을 수 없습니다.

3. 행의 데이터 추출
- 각 행에서 데이터를 추출하여 객체로 변환한 뒤, 리스트 등에 저장해야 합니다.
