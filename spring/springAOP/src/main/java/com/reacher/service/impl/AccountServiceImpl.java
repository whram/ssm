package com.reacher.service.impl;

import com.reacher.service.AccountService;

public class AccountServiceImpl implements AccountService {
    public void saveAccount() {
        System.out.println("执行账户保存...");
    }

    public void updateAccount(int i) {
        System.out.println("执行了更新"+ i);
    }

    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
