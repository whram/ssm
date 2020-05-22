package com.reacher.service;

import com.reacher.domain.Account;

public interface AccountService {

    Account findById(Integer accountId);

    Account findByName(String accountName);

    void transfer(String sourceName,String targetName,Float money);
}
