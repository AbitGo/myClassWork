package ch9Source;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ch9Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        JuiceMaker2 juiceMaker2 = (JuiceMaker2)applicationContext.getBean("juicemaker2");
        System.out.println(juiceMaker2.makeJuice());
    }
}
