package com.reacher.utils;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.SQLException;

/*
* 事务管理相关的工具类
*   包含了：开启事务，提交事务，回滚事务，释放连接
* */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /*
    * 开启事务
    * */
    public void beginTransaction(){
        try {
            connectionUtils.getSqlSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 提交事务
    * */
    public void commit() {
        try {
            connectionUtils.getSqlSession().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 回滚事务
    * */
    public void rollback() {
        try {
            connectionUtils.getSqlSession().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 释放资源
    * */
    public void release() {
        try {
            connectionUtils.getSqlSession().close();
            connectionUtils.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
