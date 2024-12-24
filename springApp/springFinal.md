# Java `final` 변수

`final` 변수는 Java 프로그래밍에서 중요한 역할을 하며, 코드의 안정성과 예측 가능성을 높이는 데 기여합니다. 이 문서에서는 `final` 변수의 개념, 초기화 방법, 사용 사례, 주의사항 등을 자세히 다룹니다.

---

## `final` 변수란?

`final` 변수는 한 번 초기화되면 그 값을 변경할 수 없는 변수입니다. 주로 상수를 정의하거나 불변 객체를 생성하는 데 사용됩니다. `final` 키워드는 변수뿐만 아니라 메서드와 클래스에도 적용될 수 있지만, 이 문서에서는 주로 변수에 대해 다룹니다.

## `final` 변수 초기화 방법

Java에서 `final` 변수를 초기화할 수 있는 방법은 다음 세 가지입니다:

1. **선언과 동시에 초기화**
2. **생성자를 통한 초기화**
3. **인스턴스 초기화 블록을 통한 초기화**

### 1. 선언과 동시에 초기화

변수를 선언하는 동시에 값을 할당하는 방법입니다. 이 방식은 가장 간단하며, 변수가 항상 동일한 값으로 초기화되도록 보장합니다.

```java
public class Example {
    // 선언과 동시에 초기화
    private final int CONSTANT_VALUE = 100;

    public void display() {
        System.out.println(CONSTANT_VALUE);
    }
}
```

**장점**
- 코드가 간결하고 명확합니다.
- 변수가 항상 동일한 값으로 초기화되므로 예측 가능성이 높습니다.

---

### 2. 생성자를 통한 초기화
객체가 생성될 때 생성자에서 final 변수를 초기화할 수 있습니다. 이 방법은 초기화 시점에 동적인 값을 할당해야 할 때 유용합니다.

```java
public class Example {
    private final int dynamicValue;

    public Example(int value) {
        this.dynamicValue = value;
    }

    public void display() {
        System.out.println(dynamicValue);
    }
}
```

***특징***
- 객체 생성 시점에 값을 지정할 수 있어 유연성이 있습니다.
- 모든 생성자에서 final 변수를 초기화해야 합니다. 그렇지 않으면 컴파일 오류가 발생합니다.

---

### 3. 인스턴스 초기화 블록을 통한 초기화
인스턴스 초기화 블록을 사용하여 final 변수를 초기화할 수 있습니다. 이는 선언과 동시에 초기화하거나 생성자에서 초기화할 수 없는 복잡한 초기화 로직이 필요할 때 유용합니다.

```java
public class Example {
    private final int initializedValue;

    // 인스턴스 초기화 블록
    {
        initializedValue = 50;
        // 복잡한 초기화 로직 가능
    }

    public Example() {
        // 생성자에서도 초기화 가능 (단, 이미 초기화된 경우 중복 초기화 불가)
    }

    public void display() {
        System.out.println(initializedValue);
    }
}
```

***주의사항***
- 인스턴스 초기화 블록은 클래스 내에 여러 개 있을 수 있으며, 선언 순서에 따라 실행됩니다.
- 생성자와 인스턴스 초기화 블록을 함께 사용할 경우, final 변수를 두 번 초기화하려고 하면 컴파일 오류가 발생합니다.

---

### 스프링에서 final 변수 사용
스프링 프레임워크에서 final 변수를 사용하는 것은 의존성 주입(Dependency Injection) 시 매우 유용합니다. 주로 생성자 기반 의존성 주입과 함께 사용되어, 의존성이 불변임을 보장합니다.

```java
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(Order order) {
        orderRepository.save(order);
    }
}
```

***장점***
- 불변성 보장: 의존성이 한 번 할당되면 변경될 수 없습니다.
- 명확한 의존성: 생성자를 통해 필요한 의존성이 명확하게 드러납니다.
- 테스트 용이성: 단위 테스트 시 모킹(Mock) 객체를 주입하기 쉽습니다.

---
