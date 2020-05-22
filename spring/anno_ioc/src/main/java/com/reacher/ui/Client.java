package com.reacher.ui;

import com.reacher.dao.AccountDao;
import com.reacher.service.AccountService;
import com.reacher.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/*
* 模拟表现层，用于调用业务
* */
public class Client {

    public static void main(String[] args) {
        //获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //根据id获取bean对象
        AccountService aService = (AccountService)ac.getBean("accountService");
        //System.out.println(aService);

        /*AccountDao aDao = (AccountDao) ac.getBean("accountDao");
        System.out.println(aDao);*/

        aService.saveAccount();
    }
}
