package ex05.aop.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/*
  - @Aspect
    : 설정 파일에 Advice 및 Pointcut 등의 설정을 하지 않고 자동으로 Advice 적용
*/
@Aspect
public class MyLogAspect {
	
	@Pointcut(value = "execution(public * ex05.aop.anno.core..*Service.*(..))")
	private void publicMethod() {
		// Pointcut 설정을 위한 가명 메소드.
		// private void 이어야 하며, 메소드의 몸체가 없어야 한다.
	}
	
	// 메소드 호출 전
	// @Before("execution(public * ex05.aop.anno.core..*Service.*(..))")
	@Before("publicMethod()")
	public String beforeLogging(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("before : " + methodName);
		
		return methodName;
	}
	
	// 메소드가 예외 없이 실행 된 후
	@AfterReturning(value = "publicMethod()", returning = "ret")
	public void returningLogging(JoinPoint joinPoint, Object ret) {
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("returning : " + methodName + ", 리턴값 : " + ret);
	}
	
	// 메소드에서 예외가 발생한 경우
	@AfterThrowing(value = "publicMethod()", throwing = "ex")
	public void throwingLogging(JoinPoint joinPoint, Throwable ex) {
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("throwing : " + methodName + 
				", throws : " + ex.getClass().getName());
		
	}
	
	// 예외 발생 유무와 상관 없이 메소드 실행 후
	@After("publicMethod()")
	public void afterLogging(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("after : " + methodName);
	}
}
 