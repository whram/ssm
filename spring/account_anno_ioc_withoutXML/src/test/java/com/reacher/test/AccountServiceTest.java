package com.reacher.test;

import com.reacher.config.JDBCConfig;
import com.reacher.config.SpringConfiguration;
import com.reacher.domain.Account;
import com.reacher.service.AccountService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/*
* 使用junit单元测试
*
* 应用程序的入口：
*       main方法
* junit单元测试中，没有main方法也能执行
*       junit集成了main方法
* junit不会管我们是否使用了spring框架，所以不会为我们创建ioc容器
*
* Spring整合junit的配置
*   1.导入Spring整合junit的jar包（maven坐标）
*   2.使用junit提供的注解把原有的main方法替换成spring提供的
*       @RunWith
*   3.告知Spring的运行器，Spring的ioc创建是基于xml还是注解，并说明位置
*       @ContextConfiguration
*           location：指定xml文件位置，加上classpath关键字，表示在类路径下
*           classes：指定注解类所在位置
*
* 当使用Spring 5.x版本的时候，要求junit的jar必须是4.12及以上
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {

    @Autowired
    private AccountService aService;

    @Test
    public void findAll() {
        List<Account> accounts = aService.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void findById() {
        Account account = aService.findById(1);
        System.out.println(account);
    }

    @Test
    public void save() {
        Account account = new Account();
        account.setName("test");
        account.setMoney(3000f);
        aService.saveAccount(account);
    }

    @Test
    public void update() {
        Account account = aService.findById(5);
        account.setMoney(12345f);
        account.setName("update");
        aService.updateAccount(account);
        System.out.println(account);
    }

    @Test
    public void delete() {
        aService.deleteAccount(4);
    }
}
