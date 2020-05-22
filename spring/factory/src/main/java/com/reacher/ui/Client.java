package com.reacher.ui;

import com.reacher.factory.BeanFactory;
import com.reacher.service.AccountService;
import com.reacher.service.impl.AccountServiceImpl;

/*
* 模拟表现层，用于调用业务
* */
public class Client {
    public static void main(String[] args) {
        //AccountService as = new AccountServiceImpl();
        for (int i = 0; i < 5; i++) {
            AccountService as = (AccountService)BeanFactory.getBean("accountService");
            System.out.println(as);
            as.saveAccount();
        }
    }
}
