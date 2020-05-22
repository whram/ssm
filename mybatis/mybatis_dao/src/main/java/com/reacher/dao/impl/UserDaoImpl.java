package com.reacher.dao.impl;

import com.reacher.dao.UserDao;
import com.reacher.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        //根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //调用SqlSession中的方法，实现查询列表
        List<User> users = sqlSession.selectList("com.reacher.dao.UserDao.findAll");
        sqlSession.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.insert("com.reacher.dao.UserDao.saveUser",user);//方法需要传入插入数据
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateUser(User user) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.update("com.reacher.dao.UserDao.updateUser",user);//方法需要传入插入数据
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteUser(Integer userId) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.delete("com.reacher.dao.UserDao.deleteUser",userId);//方法需要传入插入数据
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public User findById(Integer userId) {
        //根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //调用SqlSession中的方法，实现查询列表
        User user = sqlSession.selectOne("com.reacher.dao.UserDao.findById",userId);
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> findByName(String userName) {
        //根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //调用SqlSession中的方法，实现查询列表
       List<User> users = sqlSession.selectList("com.reacher.dao.UserDao.findByName",userName);
        sqlSession.close();
        return users;
    }

    @Override
    public int findTotal() {
        //根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //调用SqlSession中的方法，实现查询列表
        Integer count = sqlSession.selectOne("com.reacher.dao.UserDao.findTotal");
        sqlSession.close();
        return count;
    }
}
