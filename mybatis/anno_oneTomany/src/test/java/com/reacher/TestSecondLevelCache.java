package com.reacher;

import com.reacher.dao.UserDao;
import com.reacher.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestSecondLevelCache {
    private InputStream is;
    private SqlSessionFactory factory;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    @After
    public void destroy() throws IOException {;
        is.close();
    }

    @Test
    public void findById() {
        SqlSession sqlSession1 = factory.openSession(true);
        UserDao userDao1 = sqlSession1.getMapper(UserDao.class);
        User user1 = userDao1.findById(41);
        System.out.println(user1);

        sqlSession1.close();

        SqlSession sqlSession2 = factory.openSession(true);
        UserDao userDao2 = sqlSession2.getMapper(UserDao.class);
        User user2 = userDao2.findById(41);
        System.out.println(user2);

        sqlSession2.close();


    }

}
