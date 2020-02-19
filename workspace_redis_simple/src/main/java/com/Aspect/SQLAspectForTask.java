package com.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SQLAspectForTask {
//    @Pointcut(value = "execution(* com.DeviceData.DeviceController.test(..))")
//    private void SQLDelOptAspectForTask() {
//        //System.out.println("SQLDelOptAspectForTask");
//    }
//    //@After 在方法后执行
//    @After("SQLDelOptAspectForTask()")
//    private void doAfter()
//    {
//        System.out.println("i run after the SQLAspectForTask");
//    }
//
//    @Pointcut(value = "execution(* com.DeviceData.*.*(..))")
//    private void allAspect() {
//        //System.out.println("SQLDelOptAspectForTask");
//    }
//    @After("allAspect()")
//    private void allAspectDoAfter()
//    {
//        System.out.println("i run after the allAspect");
//    }

//
//    //@Before 在方法前执行
//    @Transactional
//    @Before("RedisAspectForTask()")
//    public void doBefore(JoinPoint joinPoint)
//    {
//
//
//    }
//

//
//    //@AfterReturning 在方法执行后返回一个结果后执行
//    @AfterReturning("RedisAspectForTask()")
//    public void doAfterRunning(JoinPoint joinPoint)
//    {
//        System.out.println("doAfterRunning");
//    }
//
//    //@AfterThrowing 在方法执行过程中抛出异常的时候执行
//    @AfterThrowing("RedisAspectForTask()")
//    public void doAfterThrowing(JoinPoint joinPoint)
//    {
//        System.out.println("AfterThrowing");
//    }
//
//    //@Around 环绕通知，就是可以在执行前后都使用，
//    //这个方法参数必须为ProceedingJoinPoint，proceed()方法就是被切面的方法，
//    // 上面四个方法可以使用JoinPoint，JoinPoint包含了类名，被切面的方法名，参数等信息。
//    @Around("RedisAspectForTask()")
//    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable{
//        System.out.println("deAround");
//        return joinPoint.proceed();
//    }
}