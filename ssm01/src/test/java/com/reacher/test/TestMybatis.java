package com.reacher.test;

import com.reacher.dao.AccountDao;
import com.reacher.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {

    @Test
    public void testFindAll() throws IOException {
        //加载mybatis文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建sqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //生产sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取到代理对象
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
        sqlSession.close();
        is.close();
    }

    @Test
    public void testSaveAccount() throws IOException {
        //加载mybatis文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建sqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //生产sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取到代理对象
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        Account account = new Account();
        account.setName("大大");
        account.setMoney(4000.0);
        accountDao.saveAccount(account);
        sqlSession.commit();
        sqlSession.close();
        is.close();
    }

}
