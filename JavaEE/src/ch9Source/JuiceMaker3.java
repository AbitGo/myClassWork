package ch9Source;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class JuiceMaker3 implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean,DisposableBean {
    private String beverageShop = null;
    private Source source = null;

    public String getBeverageShop() {
        return beverageShop;
    }

    public void setBeverageShop(String beverageShop) {
        this.beverageShop = beverageShop;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void init(){
        System.out.println("["+this.getClass().getSimpleName()+"]执行自定义初始化方法");
    }

    public void myDestory(){
        System.out.println("["+this.getClass().getSimpleName()+"]执行自定义销毁方法");
    }

    public String makeJuice(){
        String juice = "这是一杯"+beverageShop+"饮品店提供的"+source.getSize()+source.getSugar()+source.getFruit();
        return juice;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("["+this.getClass().getSimpleName()+"]调用BeanNameAware的setBeanName方法");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("["+this.getClass().getSimpleName()+"]调用BeanFactoryAware的setBeanFactory方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("["+this.getClass().getSimpleName()+"]调用InitializingBean的afterPropertiesSet方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("["+this.getClass().getSimpleName()+"]调用ApplicationContextAware的setApplicationContext方法");
    }


    @Override
    public void destroy() throws Exception {
        //销毁提示
        System.out.println("["+this.getClass().getSimpleName()+"]调用DisposableBean的destroy方法");
    }
}
