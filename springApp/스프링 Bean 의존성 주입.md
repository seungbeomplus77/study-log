# 스프링 Bean 의존성 주입 방법 정리

스프링에서는 의존성 주입(DI, Dependency Injection)을 통해 객체 간의 의존성을 설정한다. 아래는 **생성자**를 이용한 방식, **c 네임스페이스**를 이용한 방식, 그리고 **setter**를 이용한 방식이다.

---

## 1. 생성자를 이용한 의존성 주입

### 특징
- 객체 생성 시 **생성자**를 통해 의존성을 주입합니다.
- 주입된 값은 변경할 수 없으므로 안정적입니다.
- XML에서는 `<constructor-arg>` 태그를 사용합니다.
- 인덱스를 통해서 순서를 변경할 수 있다.

### 예제
```xml
<bean id="userService" class="com.user1.UserServiceImpl"/>

<bean id="userService2" class="com.user1.UserServiceImpl2">
    <constructor-arg index="0" value="스프링"/>
    <constructor-arg index="2" value="17"/>
    <constructor-arg index="1" value="010-2222-3333"/>
</bean>

<bean id="user" class="com.user1.User">
    <constructor-arg ref="userService2"/>
</bean>
```

---

## 2. c 네임스페이스를 이용한 의존성 주입
### 특징
- XML의 c 네임스페이스를 사용하여 생성자 값을 간결하게 설정합니다.
- 생성자 의존성을 주입하는 코드의 가독성이 높아집니다.
- init-method와 destroy-method를 통해 초기화/소멸 메소드를 지정할 수 있습니다.

### 예제
```xml
<bean id="userService" class="com.user2.UserServiceImpl2"
    c:name="네임스" c:tel="010-3333-4444" c:age="19"/>

<bean id="user" class="com.user2.User"
    c:userService-ref="userService"
    init-method="init"
    destroy-method="destroy"/>
```

---

## 3. setter를 이용한 의존성 주입
### 특징
- <property> 태그를 사용하여 setter 메소드를 통해 의존성을 주입합니다.
- 주입된 값을 프로그램 실행 중에 변경할 수 있어 유연성이 높습니다.
- 의존성 주입을 위해 반드시 setter 메소드가 구현되어 있어야 합니다.
- 값 변경이 가능하므로 안정성은 상대적으로 낮습니다.

### 예제
```xml
<bean id="userService" class="com.user3.UserServiceImpl">
    <property name="name" value="차차차"/>
    <property name="tel" value="010-4444-5555"/>
    <property name="age" value="30"/>
</bean>

<bean id="userService2" class="com.user3.UserServiceImpl2">
    <property name="name" value="흐흐흐"/>
    <property name="tel" value="010-5555-6666"/>
    <property name="age" value="17"/>
</bean>

<bean id="user" class="com.user3.User">
    <property name="userService" ref="userService2"/>
</bean>
```

![image](https://github.com/user-attachments/assets/b37e51e9-922b-444d-8bd6-86108d01075a)
