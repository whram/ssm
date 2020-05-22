package com.reacher.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/*
* 用于记录日志的工具类，提供了公共代码
* */
public class Logger {

    /*
     * 前置通知
     * */
    public void beforePrintLog(){
        System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志了...");
    }

    /*
     * 后置通知
     * */
    public void afterReturningPrintLog(){
        System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志了...");
    }

    /*
     * 异常通知
     * */
    public void afterThrowingPrintLog(){
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志了...");
    }

    /*
     * 最终通知
     * */
    public void afterPrintLog(){
        System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志了...");
    }

    /*
    * 环绕通知
    *   配置环绕通知后，切入点方法没有执行，通知方法执行了
    *   通过对比动态代理中的环绕通知代码，动态代理中有明确的切入点方法调用
    * 解决：
    *   Spring框架中提供了一个接口：ProceedingJoinPoint，该接口有一个方法proceed（），此方法就相当于明确调用切入点方法。
    *   该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用
    *
    * spring中的环绕通知：
    *   是spring框架提供的一种可以在代码中手动控制增强方法的合适执行的方法
    * */
    public Object aroundPrintLog(ProceedingJoinPoint proceedingJoinPoint) {
        Object returnValue;
        try {
            Object[] args = proceedingJoinPoint.getArgs();//得到方法所需参数
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了...前置");
            returnValue = proceedingJoinPoint.proceed(args);//明确调用业务层方法（切入点方法）
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了...后置");
            return returnValue;
        } catch (Throwable throwable) {
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了...异常");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了...最终");
        }
    }
}
