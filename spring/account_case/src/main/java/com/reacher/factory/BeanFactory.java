package com.reacher.factory;

import com.reacher.domain.Account;
import com.reacher.service.AccountService;
import com.reacher.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/*
* 用于创建Service的代理对象的工厂
* */
public class BeanFactory {

    private AccountService accountService;
    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /*
    * 获取Service的代理对象
    * */
    public AccountService getAccountService() {
        return (AccountService) Proxy.newProxyInstance(AccountService.class.getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    Object returnValue = null;
                    /*
                    * 添加事务的支持
                    * */
                    //整个invoke方法在执行就是环绕通知
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try{
                            //1.开启事务
                            transactionManager.beginTransaction();//前置通知
                            //2.执行操作
                            returnValue = method.invoke(accountService,args);//在环绕通知中有明确的切入点方法调用
                            //3.提交事务
                            transactionManager.commit();//后置通知
                            //4.返回结果
                            return returnValue;
                        }catch (Exception e) {
                            //5.回滚操作
                            transactionManager.rollback();//异常通知
                            throw new RuntimeException(e);
                        }finally {
                            //6.释放资源
                            transactionManager.release();//最终通知
                        }
                    }
                });
    }
}
