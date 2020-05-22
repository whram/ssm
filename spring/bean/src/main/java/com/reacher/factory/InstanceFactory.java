package com.reacher.factory;

import com.reacher.service.AccountService;
import com.reacher.service.impl.AccountServiceImpl;

public class InstanceFactory {
    public AccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
