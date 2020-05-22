package com.reacher.test;

import com.reacher.dao.UserDao;
import com.reacher.dao.impl.UserDaoImpl;
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
import java.util.Date;
import java.util.List;

public class demo01 {

    private InputStream is;
    private UserDao userDao;

    @Before //用于测试方法执行前执行
    public void init() throws IOException {
        //读取配置文件，生成字节流
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //使用工厂对象创建对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        userDao = new UserDaoImpl(factory);
    }

    @After//用于执行方法执行完后后执行
    public void destroy() throws IOException {
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
        user.setUsername("dao_impl_user");
        user.setAddress("河南郑州");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存前：" + user);
        userDao.saveUser(user);
        System.out.println("保存后：" + user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(54);
        user.setUsername("dao_impl_updateUser");
        user.setAddress("河南郑州");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        userDao.deleteUser(55);
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

}
