package ch9Source;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ch9Main {
    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
//        JuiceMaker2 juiceMaker2 = (JuiceMaker2)applicationContext.getBean("juicemaker2");
//        System.out.println(juiceMaker2.makeJuice());
//
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
//        JuiceMaker3 juiceMaker3 = (JuiceMaker3)applicationContext.getBean("juicemaker3");
//        System.out.println(juiceMaker3.makeJuice());
//        applicationContext.close();
//
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
//        Role role1 = (Role)applicationContext.getBean("role1");
//        System.out.println(role1.printInfo());
//
//        Role role2 = (Role)applicationContext.getBean("role2");
//        System.out.println(role2.printInfo());

        //通过xml获取配置文件
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        ComplexAssemly complexAssembly = (ComplexAssemly)applicationContext.getBean("complexAssembly");
        System.out.println(complexAssembly.printInfo());
    }
}
