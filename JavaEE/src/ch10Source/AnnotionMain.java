package ch10Source;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotionMain {
    public static void main(String[] args) {
        //通过注释去注册java实体类
        ApplicationContext context = new AnnotationConfigApplicationContext(PoJoConfig.class);
        //需要将RoleAnnotation类注入进去
        RoleAnnotation role = context.getBean(RoleAnnotation.class);
        System.out.println(role.getId());
    }
}
