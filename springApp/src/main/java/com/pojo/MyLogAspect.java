package ex04.aop.pojo;

import org.aspectj.lang.JoinPoint;

public class MyLogAspect {
	
	/*
	- JoinPoint : 클라이언트가 호출한 비즈니스 메소드의 정보를 가지고 있는 인터페이스
		Signature getSignature()	: 클라이언트가 호출한 메소드의 시그니처(리턴타입, 이름, 매개변수) 정보가 저장된 Signature 객체 리턴
		Object getTarget()	클라이언트가 호출한 비즈니스 메소드를 포함하는 비즈니스 객체 리턴
		Object[] getArgs()	클라이언트가 메소드를 호출할 때 넘겨준 인자 목록을 Object 배열 로 리턴
	*/
	public String beforeLogging(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("before : " + methodName);
		
		return methodName;
	}
	
	public void returningLogging(JoinPoint joinPoint, Object ret) {
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("returning : " + methodName + ", 리턴값 : " + ret);
	}
	
	public void throwingLogging(JoinPoint joinPoint, Throwable ex) {
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("throwing : " + methodName + 
				", throws : " + ex.getClass().getName());
		
	}
	
	public void afterLogging(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("after : " + methodName);
	}
}
 