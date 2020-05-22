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
import java.util.Date;
import java.util.List;

public class TestCRUD {
    private InputStream is;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        is.close();
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("save_user");
        user.setAddress("河南郑州");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.saveUser(user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(41);
        user.setUsername("update_user");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    @Test
    public void deleteUser() {
        userDao.deleteUser(49);
    }

    @Test
    public void findById() {
        User user = userDao.findById(41);
        System.out.println(user);
    }

    @Test
    public  void  findByName() {
        //List<User> users = userDao.findUserByName("%王%");
        List<User> users = userDao.findUserByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void findTotal() {
        int total = userDao.findTotal();
        System.out.println(total);
    }

}
