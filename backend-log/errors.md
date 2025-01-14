mybatis 네임스페이스가 잘못 된 경우 / xml 의 id 와 매칭되는 자바 매퍼의 메소드가 없는 경우
- BindingException

mybatis parameterType 또는 resultType 에 존재하지 않는 클래스를 작성한 경우
- 서버가 실행되지 않는다.
- ClassNotFoundException

mybatis parameterType or resultType 을 map으로 사용한 경우
- 모든 메퍼가 실행되지 않는다(로그인 불가)

mybatis xml 에서 쿼리 뒤에 세미콜론을 붙인 경우
- ORA-00933 에러 발생

mybatis xml 에서 두개 이상의 테이블을 조인할 때 두개 이상의 테이블에 중복 된 이름의 컬럼이 존재하는 경우
- ORA-00918 에러 발생
- 조인할때 각 컬럼에 별명을 부여하는것이 에러를 방지하기 더 쉽다.

mybatis xml 에서 존재하지않는 컬럼이 존재할 때
- ORA-00904 에러 발생

mybatis xml 에서 INSERT, UPDATE 등에서 #(컬럼명) 이 dto에 존재하지 않는 경우
- Error updating database. Cause: org.apache.ibatis.relflection.ReflectionException: There is no getter for property named

파라미터가 문제일때는, 주소줄을 확인한다.

<c:forEach var="dto" items="${list}" varStatus="status">
- items 에 EL표현식을 생략하거나 dto에 없는 이름 썼을 때, PropertyNotFoundException 에러가 발생
