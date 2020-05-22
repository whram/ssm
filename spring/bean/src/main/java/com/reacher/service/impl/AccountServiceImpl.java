package com.reacher.service.impl;

import com.reacher.service.AccountService;

/*
* 业务层实现类
* */
public class AccountServiceImpl implements AccountService {

    //构造函数，测试类被实例的时机
    public AccountServiceImpl() {
        System.out.println("对象被创建了");
    }

    public void saveAccount() {
        System.out.println("service中的saveAccount方法执行了...");
    }

    public void init() {
        System.out.println("account对象被初始化...");
    }

    public void destroy() {
        System.out.println("account对象被销毁...");
    }

}
