package ch10Source;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotionMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(PoJoConfig.class);
        RoleAnnotation role = context.getBean(RoleAnnotation.class);
        System.out.println(role.getId());
    }
}
