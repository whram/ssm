package com.reacher.dao;

import com.reacher.domain.Account;

import java.util.List;

/*
* 账户的持久层接口
* */
public interface AccountDao {

    /*
     * 查询所有
     * */
    List<Account> findAllAccount();

    /*
     * 查询一个
     * */
    Account findById(Integer accountId);

    /*
     * 保存操作
     * */
    void saveAccount(Account account);

    /*
     * 更新
     * */
    void updateAccount(Account account);

    /*
     * 删除账户
     * */
    void deleteAccount(Integer accountId);
}
