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
    public void testSaveUser(){
        User user = new User();
        user.setUserName("mybatis_saveUser");
        user.setUserAddress("河南郑州");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

        userDao.saveUser(user);
        System.out.println(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUserId(54);
        user.setUserName("mybatis_updateUser");
        user.setUserAddress("河南郑州");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        userDao.deleteUser(53);
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
    public void testFindTotal() {
        int total = userDao.findTotal();
        System.out.println(total);
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
}
