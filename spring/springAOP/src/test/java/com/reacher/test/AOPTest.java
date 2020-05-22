package com.reacher.test;

import com.reacher.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
* 测试Aop的配置
* */
public class AOPTest {
    public static void main(String[] args) {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取对象
        AccountService aService = (AccountService) ac.getBean("accountService");
        //执行方法
        aService.saveAccount();
        aService.updateAccount(1);
        int i = aService.deleteAccount();
    }
}
