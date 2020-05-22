package com.reacher.test;

import com.reacher.domain.Account;
import com.reacher.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/*
* 使用junit单元测试
* */
public class AccountServiceTest {

    @Test
    public void findAll() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取业务层对象
        AccountService aService = (AccountService)ac.getBean("accountService");
        List<Account> accounts = aService.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void findById() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取业务层对象
        AccountService aService = (AccountService)ac.getBean("accountService");
        Account account = aService.findById(1);
        System.out.println(account);
    }

    @Test
    public void save() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取业务层对象
        AccountService aService = (AccountService)ac.getBean("accountService");
        Account account = new Account();
        account.setName("test");
        account.setMoney(3000f);
        aService.saveAccount(account);
    }

    @Test
    public void update() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取业务层对象
        AccountService aService = (AccountService)ac.getBean("accountService");
        Account account = aService.findById(4);
        account.setMoney(12345f);
        account.setName("update");
        aService.updateAccount(account);
        System.out.println(account);
    }

    @Test
    public void delete() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取业务层对象
        AccountService aService = (AccountService)ac.getBean("accountService");
        aService.deleteAccount(4);
    }
}
