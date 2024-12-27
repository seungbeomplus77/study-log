package ex01.aop.after;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/*
 	- AfterReturningAdvice
 		: 대상 객체의 메소드를 실행 이후에 공통 기능을 실행할 때 사용하는 Advice
 		: 예외없이 실행 된 경우에만 실행
 */


public class AfterLogAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		String s = target + " 클래스의 " + method.getName() 
			+ " 메소드 호출 후 ...\n " 
			+ " 리턴 값 : " + returnValue;
		
		System.out.println(s);
	}

}
