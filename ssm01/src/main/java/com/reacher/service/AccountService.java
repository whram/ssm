package com.reacher.service;

import com.reacher.domain.Account;

import java.util.List;

/*
* 账户服务
* */
public interface AccountService {

    /*
     * 查询所有账户
     * */
    List<Account> findAll();

    /*
     * 保存账户信息
     * */
    void saveAccount(Account account);

}
