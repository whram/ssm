package com.reacher.utils;

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

}
