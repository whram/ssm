package com.reacher.service.impl;

import com.reacher.dao.AccountDao;
import com.reacher.domain.Account;
import com.reacher.service.AccountService;
import com.reacher.utils.TransactionManager;

import java.util.List;

/*
* 账户的业务层实现类
* */
public class AccountServiceImpl_old implements AccountService {

    private AccountDao accountDao;
    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        try{
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //3.提交事务
            transactionManager.commit();
            //4.返回结果
            return accounts;
        }catch (Exception e) {
            //5.回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放资源
            transactionManager.release();
        }
    }

    public Account findById(Integer accountId) {
        try{
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            Account account = accountDao.findById(accountId);
            //3.提交事务
            transactionManager.commit();
            //4.返回结果
            return account;
        }catch (Exception e) {
            //5.回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放资源
            transactionManager.release();
        }
    }

    public void saveAccount(Account account) {
        try{
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            accountDao.saveAccount(account);
            //3.提交事务
            transactionManager.commit();
        }catch (Exception e) {
            //5.回滚操作
            transactionManager.rollback();
        }finally {
            //6.释放资源
            transactionManager.release();
        }
    }

    public void updateAccount(Account account) {
        try{
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            accountDao.updateAccount(account);
            //3.提交事务
            transactionManager.commit();
        }catch (Exception e) {
            //5.回滚操作
            transactionManager.rollback();
        }finally {
            //6.释放资源
            transactionManager.release();
        }
    }

    public void deleteAccount(Integer accountId) {
        try{
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            accountDao.deleteAccount(accountId);
            //3.提交事务
            transactionManager.commit();
        }catch (Exception e) {
            //5.回滚操作
            transactionManager.rollback();
        }finally {
            //6.释放资源
            transactionManager.release();
        }
    }

    public void transfer(String sourceName, String targetName, Float money) {
        try{
            //1.开启事务
            transactionManager.beginTransaction();

            //2.执行操作
            //根据名称查询转出账户
            Account source = accountDao.findAccountByName(sourceName);
            //根据名称查询转入账户
            Account target = accountDao.findAccountByName(targetName);
            //转出账户减钱
            source.setMoney(source.getMoney()-money);
            //转入账户加钱
            target.setMoney(target.getMoney()+money);
            //更新转出账户
            accountDao.updateAccount(source);

            int i = 1/0;
            //更新转入账户
            accountDao.updateAccount(target);

            //3.提交事务
            transactionManager.commit();
        }catch (Exception e) {
            //5.回滚操作
            transactionManager.rollback();
            e.printStackTrace();
        }finally {
            //6.释放资源
            transactionManager.release();
        }
    }
}
