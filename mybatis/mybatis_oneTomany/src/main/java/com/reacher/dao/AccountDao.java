package com.reacher.dao;

import com.reacher.domain.Account;
import com.reacher.domain.AccountUser;

import java.util.List;

public interface AccountDao {

    /*
    * 查询所有账户
    * */
    List<Account> findAll();

    /*
    * 查询所有账户，并带有用户名称和地址信息
    * */
    List<AccountUser> findAllAccount();
}
