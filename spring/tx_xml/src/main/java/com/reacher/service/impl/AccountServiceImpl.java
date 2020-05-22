package com.reacher.service.impl;

import com.reacher.dao.AccountDao;
import com.reacher.domain.Account;
import com.reacher.service.AccountService;

import java.util.List;

/*
* 账户的业务层实现类
* */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account findById(Integer accountId) {
       return accountDao.findById(accountId);
    }

    public Account findByName(String accountName) {
        return accountDao.findByName(accountName);
    }

    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer...");
        //根据名称查询转出账户
        Account source = accountDao.findByName(sourceName);
        //根据名称查询转入账户
        Account target = accountDao.findByName(targetName);
        //转出账户减钱
        source.setMoney(source.getMoney()-money);
        //转入账户加钱
        target.setMoney(target.getMoney()+money);
        //更新转出账户
        accountDao.updateAccount(source);

        int i = 1/0;
        //更新转入账户
        accountDao.updateAccount(target);
    }
}
