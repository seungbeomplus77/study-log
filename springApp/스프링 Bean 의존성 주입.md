# 스프링 Bean 의존성 주입 방법 정리

스프링에서는 의존성 주입(DI, Dependency Injection)을 통해 객체 간의 의존성을 설정한다. 아래는 **생성자**를 이용한 방식, **c 네임스페이스**를 이용한 방식, **p 네임스페이스**, 그리고 **컬렉션 타입 주입** 그리고 **setter**를 이용한 방식이다.

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
---

## 4. p 네임스페이스를 이용한 의존성 주입
### 특징
- p 네임스페이스를 사용하여 setter 주입 방식을 간소화합니다.
- XML 설정이 간결해지고 가독성이 좋아집니다.

### 예제
```xml
<bean id="userService" class="com.user4.UserServiceImpl"
    p:name="이순신" p:tel="010-6666-5555" p:age="22"/>

<bean id="user" class="com.user4.User"
    p:userService-ref="userService"/>
```
---

## 5. 컬렉션 타입 주입    
### 특징
- map, list, props 태그를 사용하여 컬렉션 데이터를 주입합니다.
- 다양한 데이터 구조를 간편하게 설정 가능.

### 예제
```xml
<bean id="userService" class="com.user5.UserServiceImpl">
    <property name="name" value="컬렉션"/>
    <property name="address">
        <map>
            <entry key="서블릿" value="서울"/>
            <entry>
                <key><value>스프링</value></key>
                <value>경기</value>
            </entry>
        </map>
    </property>
    <property name="hobby">
        <list>
            <value>게임</value>
            <value>영화</value>
            <value>운동</value>
        </list>
    </property>
    <property name="tel">
        <props>
            <prop key="하하하">010-1111-2222</prop>
            <prop key="가가가">010-1331-2442</prop>
            <prop key="나나나">010-1441-2552</prop>
        </props>
    </property>
</bean>

<bean id="user" class="com.user5.User">
    <property name="userService" ref="userService"/>
</bean>
```
---

![image](https://github.com/user-attachments/assets/492915f5-30e2-4e86-9856-fe40c413af28)
