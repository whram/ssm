package com.reacher.test;

import com.reacher.dao.AccountDao;
import com.reacher.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/*
* 在对应的四种表关系中：一对多，多对一，一对一，多对多
		一对多，多对多：通常情况下我们都是采用延迟加载。
		多对一，一对一：通常情况下我们都是采用立即加载。
* */
public class TestAccount {

    private InputStream is;
    private SqlSession session;
    private AccountDao accountDao;

    @Before //用于测试方法执行前执行
    public void init() throws IOException {
        //读取配置文件，生成字节流
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //生产sqlSession对象
        session = new SqlSessionFactoryBuilder().build(is).openSession(true);//autoCommit:设置事务是否自动提交
        //使用SqlSession对象代理实现UserDao
        accountDao = session.getMapper(AccountDao.class);
    }

    @After//用于执行方法执行完后后执行
    public void destroy() throws IOException {
        //提交事务
        session.commit();
        session.close();
        is.close();
    }

    /*
    * 查询所有账户
    * 延迟加载user
    * 方法执行时查询出所有account
    * 当调用account.getUser()时再执行  select * from user where id = #{uid}
    * */
    @Test
    public void findAll() {
        List<Account> accountList = accountDao.findAll();
        for (Account account : accountList) {
            System.out.println("----每个用户----");
            System.out.println("账户信息： "+account);
            System.out.println("用户信息： "+account.getUser());
        }
    }


}
