package com.reacher.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/*
* 事务管理相关的工具类
*   包含了：开启事务，提交事务，回滚事务，释放连接
* */
@Component("transactionManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.reacher.service.impl.*.*(..))")
    private void pt1(){}

    /*
    * 开启事务
    * */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 提交事务
    * */
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 回滚事务
    * */
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 释放资源
    * */
    public void release() {
        try {
            connectionUtils.getThreadConnection().close();//将连接还回连接池中，但仍和线程绑定所以需要解绑
            connectionUtils.removeConnection();//解绑
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 环绕通知
    * */
    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        Object returnValue = null;
        try {
            //获取参数
            Object[] args = proceedingJoinPoint.getArgs();
            //开启事务
            this.beginTransaction();
            //执行方法
            returnValue = proceedingJoinPoint.proceed(args);
            //提交事务
            this.commit();
            //返回结果
            return returnValue;
        } catch (Throwable e){
            //回滚事务
            this.rollback();
            throw new RuntimeException(e);
        }finally {
            //释放资源
            this.release();
        }
    }
}
