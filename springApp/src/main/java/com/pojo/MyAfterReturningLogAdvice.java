package ex04.aop.pojo;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class MyAfterReturningLogAdvice implements AfterReturningAdvice {
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("MyAfterReturningLogAdvice 클래스 : " +
				method.getName() +", " + target +", " + returnValue);
	}
}
