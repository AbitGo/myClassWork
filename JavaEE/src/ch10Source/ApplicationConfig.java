package ch10Source;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//优先使用basePackages
@ComponentScan(basePackageClasses = {RoleAnnotation.class,RoleServiceImpl.class})
//@ComponentScan(basePackages = {"com.ssm.chapter10.pojo","com.ssm.chapter10.annotation.service"})
public class ApplicationConfig {
}
