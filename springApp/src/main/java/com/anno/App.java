package ex05.aop.anno;

import org.springframework.context.support.GenericXmlApplicationContext;

import ex05.aop.anno.core.TestService;

public class App {
	public static void main(String[] args) {
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("classpath:ex05/aop/anno/applicationContext.xml");
		
		System.out.println("@Aspect 애노테이션을 이용한 AOP...\n");
		
		try {
			TestService service = (TestService)context.getBean("testService");
			
			service.save("스프링");
			System.out.println();
			
			service.write();
			System.out.println();
		} finally {
			context.close();
		}
	}
}
