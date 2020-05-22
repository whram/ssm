package com.reacher.ui;

import com.reacher.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
* 模拟表现层，用于调用业务
* */
public class Client {


    public static void main(String[] args) {
        //获取核心容器对象
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //根据id获取bean对象
        AccountService aService = (AccountService)ac.getBean("accountService");
        aService.saveAccount();
        //手动关闭容器
        ac.close();
    }
}
