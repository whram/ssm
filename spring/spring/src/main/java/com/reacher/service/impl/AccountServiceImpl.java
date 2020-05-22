package com.reacher.service.impl;

import com.reacher.dao.AccountDao;
import com.reacher.dao.impl.AccountDaoImpl;
import com.reacher.service.AccountService;

/*
* 业务层实现类
* */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = new AccountDaoImpl();

    //构造函数，测试类被实例的时机
    public AccountServiceImpl() {
        System.out.println("对象被创建了");
    }

    public void saveAccount() {
        int i = 1;//类成员要定义在方法中，避免单例对象的线程安全问题
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
