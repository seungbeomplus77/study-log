package ex02.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/*
 	- MethodInterceptor
 		: 메소드 실행 전, 후 또는 예외 발생 시점에 공통 기능을 실행 할 수 있다.
 		: MethodBeforeAdvice, AfterReturningAdvice, ThrowAdvice 세가지
 		: Advice를 하나로 묶은 Advice
 */

public class MyLogAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// 대상 객체 메소드 호출 전
		System.out.println(invocation.getMethod().getName() + " 메소드 호출 전 ...");
		
		// 대상 객체 메소드 호출
		Object returnValue = invocation.proceed();
		
		// 대상 객체 메소드 호출 후
		System.out.println(invocation.getMethod().getName() + " 메소드 호출 후 ...");
		System.out.println("리턴 값 : " + returnValue);
		
		return returnValue;
	}

}
