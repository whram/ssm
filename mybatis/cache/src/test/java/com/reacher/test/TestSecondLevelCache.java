package com.reacher.test;

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

/*
*
	Mybatis中的一级缓存和二级缓存
		二级缓存:
			它指的是Mybatis中SqlSessionFactory对象的缓存。由同一个SqlSessionFactory对象创建的SqlSession共享其缓存。
			二级缓存的使用步骤：
				第一步：让Mybatis框架支持二级缓存（在SqlMapConfig.xml中配置）
				第二步：让当前的映射文件支持二级缓存（在IUserDao.xml中配置）
				第三步：让当前的操作支持二级缓存（在select标签中配置）

			* 二级缓存中存储的是数据（未打包）而非对象，因此对象不会保存在二级缓存中
* */

public class TestSecondLevelCache {

    private InputStream is;
    private SqlSessionFactory factory;

    @Before //用于测试方法执行前执行
    public void init() throws IOException {
        //读取配置文件，生成字节流
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //生产sqlSession对象
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    @After//用于执行方法执行完后后执行
    public void destroy() throws IOException {
        is.close();
    }

    @Test
    public void secondLevelCache(){
        SqlSession sqlSession01 = factory.openSession(true);
        UserDao userDao01 = sqlSession01.getMapper(UserDao.class);
        User user1=userDao01.findById(41);
        System.out.println(user1);
        sqlSession01.close();

        SqlSession sqlSession02 = factory.openSession(true);
        UserDao userDao02 = sqlSession02.getMapper(UserDao.class);
        User user2=userDao02.findById(41);
        System.out.println(user2);
        sqlSession02.close();

        System.out.println(sqlSession01==sqlSession02);
    }

}
