package ch10Source;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//ComponentScan是对Component的扫描，默认是扫描当前包的路径，pojo的包名和他保持一致才会扫描
@ComponentScan
public class PoJoConfig {

}
