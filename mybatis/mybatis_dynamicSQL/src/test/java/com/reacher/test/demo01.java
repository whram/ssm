package com.reacher.test;

import com.reacher.dao.UserDao;
import com.reacher.domain.QueryVo;
import com.reacher.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class demo01 {

    private InputStream is;
    private SqlSession session;
    private UserDao userDao;

    @Before //用于测试方法执行前执行
    public void init() throws IOException {
        //读取配置文件，生成字节流
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //生产sqlSession对象
        session = new SqlSessionFactoryBuilder().build(is).openSession(true);//autoCommit:设置事务是否自动提交
        //使用SqlSession对象代理实现UserDao
        userDao = session.getMapper(UserDao.class);
    }

    @After//用于执行方法执行完后后执行
    public void destroy() throws IOException {
        //提交事务
        session.commit();
        session.close();
        is.close();
    }

    @Test
    public void testFindAll(){
        //调用findAll方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById(){
        User user = userDao.findById(42);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        List<User> users = userDao.findByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindByCondition() {
        User user = new User();
        user.setUserName("老王");
        user.setUserSex("");
        List<User> users = userDao.findByCondition(user);
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindUserInIdList(){
        QueryVo vo = new QueryVo();
        List<Integer> IdList = new ArrayList<Integer>();
        IdList.add(42);
        IdList.add(43);
        IdList.add(46);
        IdList.add(45);
        vo.setIdList(IdList);
        List<User> users = userDao.findUserInIdList(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
