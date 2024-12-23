# 스프링 스코프 설정 요약 및 Property Placeholder

## 1. Setter vs Constructor

### **Setter 방식**
**특징**
  - 객체를 생성한 후, `setter` 메서드를 통해 값을 설정.
  - 유연성이 높아 선택적 의존성 주입이 가능.
  - 단, 객체가 완전히 초기화되지 않은 상태에서 사용될 가능성이 있어 **안정성이 떨어질 수 있음**.
  - 변경 가능한 의존성을 설정하는 데 적합.

**장점**
  - 의존성을 선택적으로 주입할 수 있어 유연성이 높음.
  - 기본적인 설정과 추가 설정을 분리하여 관리 가능.
  
**단점**
  - 불완전한 상태의 객체가 생성될 위험.
  - 코드 가독성이 떨어질 수 있음 (많은 `setter` 메서드 필요).

**예제**
  ```xml
  <bean id="userService" class="com.example.UserServiceImpl">
      <property name="name" value="홍길동"/>
      <property name="age" value="30"/>
  </bean>
  ```

### **Constructor 방식**
**특징**
- 객체를 생성하는 시점에 필수 의존성을 강제적으로 설정.
- 불변성을 보장하며, 의존성을 빠뜨리는 실수를 방지.
- 객체 생성 후 상태가 변하지 않는 경우 적합.

**장점**
- 의존성 누락 방지: 생성자 호출 시 필수 의존성을 전달하도록 강제 가능.
- 객체가 초기화 상태로 완전한 상태에서 사용 가능.
- 테스트 코드 작성 시 의존성을 명시적으로 주입 가능.
  
**단점**
- 선택적 의존성을 설정하는 데는 다소 부적합.
- 많은 의존성을 가진 객체에서는 생성자가 복잡해질 수 있음.

**예제**
  ```xml
 <bean id="userService" class="com.example.UserServiceImpl">
    <constructor-arg name="name" value="홍길동"/>
    <constructor-arg name="age" value="30"/>
</bean>
```
---

## 2. Singleton vs Prototype
### **Singleton 방식**

**특징**:
- 스프링의 기본 스코프.
- 컨테이너당 한 개의 객체만 생성되어 애플리케이션 전역에서 재사용.
- 객체가 항상 동일하므로 메모리 효율성과 성능 면에서 유리.

**사용 사례**
- 상태를 가지지 않는 서비스 객체 (Stateless Service).
- 캐싱 또는 공통 로직을 처리하는 컴포넌트.

**예제**
```xml
<bean id="movie" class="com.scope1.Movie"/>
```

### **Prototype 스코프 방식**
**특징**
- 컨테이너에서 요청 시마다 새로운 객체를 생성하여 반환.
- 객체가 독립적인 상태를 가질 수 있음.
- 컨테이너가 객체의 생명주기를 관리하지 않음(생성과 반환까지만 관여).

**사용 사례**
- 상태가 달라야 하는 객체 (Stateful Bean).
- 특정 사용자의 요청마다 새로운 객체를 제공해야 하는 경우.

**예제**
```xml
<bean id="music" class="com.scope1.Music" scope="prototype"/>
```
---
