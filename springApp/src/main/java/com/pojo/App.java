package ex04.aop.pojo;

import org.springframework.context.support.GenericXmlApplicationContext;

import ex04.aop.pojo.core.TestService;

public class App {
	public static void main(String[] args) {
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("classpath:ex04/aop/pojo/applicationContext.xml");
		
		System.out.println(">> AOP 예제 <<");
		
		try {
			TestService service = (TestService) context.getBean("testService");
			
			service.save("스프링");
			System.out.println();
			
			service.write();
			System.out.println();
		} finally {
			context.close();
		}
		
	}
}
