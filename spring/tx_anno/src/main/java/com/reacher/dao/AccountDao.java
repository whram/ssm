package com.reacher.dao;

import com.reacher.domain.Account;

/*
* 账户的持久层接口
* */
public interface AccountDao {

    Account findById(Integer accountId);

    Account findByName(String accountName);

    void updateAccount(Account account);

}
