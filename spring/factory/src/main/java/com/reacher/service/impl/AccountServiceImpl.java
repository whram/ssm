package com.reacher.service.impl;

import com.reacher.dao.AccountDao;
import com.reacher.dao.impl.AccountDaoImpl;
import com.reacher.factory.BeanFactory;
import com.reacher.service.AccountService;

/*
* 业务层实现类
* */
public class AccountServiceImpl implements AccountService {
    //private AccountDao accountDao = new AccountDaoImpl();
    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    public void saveAccount() {
        int i = 1;//类成员要定义在方法中，避免单例对象的线程安全问题
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
