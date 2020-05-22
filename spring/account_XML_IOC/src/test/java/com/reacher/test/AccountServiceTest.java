package com.reacher.test;

import com.reacher.domain.Account;
import com.reacher.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/*
* 使用junit单元测试
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
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
        Account account = aService.findById(4);
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
