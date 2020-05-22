package com.reacher;


import com.reacher.dao.UserDao;
import com.reacher.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestAnnotation {
    /*
    * 测试基于注解的mybatis使用
    * */
    public static void main(String[] args) throws IOException {
        //获取字节输入流
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //根据字节输入流构建sqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //生产sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取dao代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

        sqlSession.close();
        is.close();
    }
}
