package ex01.aop.before;

import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		GenericXmlApplicationContext context =
				new GenericXmlApplicationContext(
						"classpath:ex01/aop/before/applicationContext.xml");
		
		System.out.println("MethodBeforeAdvice...\n");
		
		try {
			TestService service = (TestService)context.getBean("testService");
			
			service.save("자바");
			System.out.println();
			
			service.write();
			System.out.println();
		} finally {
			context.close();
		}

	}

}
