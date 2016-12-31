package net.markguerra.springappdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import net.markguerra.springappdemo.constants.AppConstants;
import net.markguerra.springappdemo.services.ExampleBean;

/**
 * A spring-aware Console Java app
 */
@Component
public class ConsoleApp {
	@Autowired
	private ExampleBean exampleBean;

	public static void main(String[] args) {
		// Get the spring context
		ApplicationContext context = new ClassPathXmlApplicationContext(AppConstants.SPRING_CONTEXT);

		// Get the bean for this class, with the member components injected by Spring
		ConsoleApp consoleApp = context.getBean(ConsoleApp.class);

		// Run the app, now with Spring powers!
		consoleApp.runApp(args);

		// Close the spring context when the app is done executing
		((ConfigurableApplicationContext)context).close();
	}

	public void runApp(String[] args) {
		// Use the ExampleBean as autowired by Spring
		System.out.println(exampleBean.testMessage());
	}
}
