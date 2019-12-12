package ch9Source;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorImpl implements BeanPostProcessor {
    @Override
    //开始实例化
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("["+bean.getClass().getSimpleName()+"]对象"+beanName+"开始实例化");
        return bean;
    }

    @Override
    //实例化完成
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("["+bean.getClass().getSimpleName()+"]对象"+beanName+"实例化完成");
        return bean;
    }
}
