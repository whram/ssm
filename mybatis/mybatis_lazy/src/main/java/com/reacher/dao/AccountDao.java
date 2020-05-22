package com.reacher.dao;

import com.reacher.domain.Account;

import java.util.List;

public interface AccountDao {

    /*
    * 查询所有账户
    * */
    List<Account> findAll();

    /*
    * 根据用户id查询账户信息
    * */
    List<Account> findAccountByUid(Integer uid);
}
