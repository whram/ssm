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
* 什么是缓存
		存在于内存中的临时数据。
	为什么使用缓存
		减少和数据库的交互次数，提高执行效率。
	什么样的数据能使用缓存，什么样的数据不能使用
		适用于缓存：
			经常查询并且不经常改变的。
			数据的正确与否对最终结果影响不大的。
		不适用于缓存：
			经常改变的数据
			数据的正确与否对最终结果影响很大的。
			例如：商品的库存，银行的汇率，股市的牌价。
	Mybatis中的一级缓存和二级缓存
		一级缓存：
			它指的是Mybatis中SqlSession对象的缓存。
			当我们执行查询之后，查询的结果会同时存入到SqlSession为我们提供一块区域中。
			该区域的结构是一个Map。当我们再次查询同样的数据，mybatis会先去sqlsession中
			查询是否有，有的话直接拿出来用。
			当SqlSession对象消失时，mybatis的一级缓存也就消失了。
			* 当调用 SqlSession 的修改，添加，删除， commit()， close()等方法时，就会清空一级缓存。
* */

public class TestFirstLevelCache {

    private InputStream is;
    private SqlSession sqlSession;
    private UserDao userDao;
    private SqlSessionFactory factory;

    @Before //用于测试方法执行前执行
    public void init() throws IOException {
        //读取配置文件，生成字节流
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //生产sqlSession对象
        factory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = factory.openSession(true);//autoCommit:设置事务是否自动提交
        //使用SqlSession对象代理实现UserDao
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After//用于执行方法执行完后后执行
    public void destroy() throws IOException {
        //提交事务
        sqlSession.commit();
        sqlSession.close();
        is.close();
    }

    @Test
    public void firstLevelCache(){
        User user1=userDao.findById(41);
        System.out.println(user1);

        /*sqlSession.close();//当SqlSession对象关闭或者消失时，mybatis的一级缓存也就消失了。
        sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(UserDao.class);*/

        sqlSession.clearCache();//此方法可清空缓存

        User user2=userDao.findById(41);
        System.out.println(user2);

        System.out.println(user1==user2);
    }

    /*
    * 测试缓存的同步
    * */
    @Test
    public void clearCache(){
        User user1=userDao.findById(41);
        System.out.println(user1);

        user1.setUsername("update_clear_cache");
        user1.setAddress("123");
        userDao.updateUser(user1);//当调用 SqlSession 的修改，添加，删除， commit()， close()等方法时，就会清空一级缓存。

        User user2=userDao.findById(41);
        System.out.println(user2);

        System.out.println(user1==user2);
    }

}
