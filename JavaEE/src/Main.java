import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        HelloWorld helloWorld = (HelloWorld)applicationContext.getBean("helloworld");
        helloWorld.sayHello();
    }
}
