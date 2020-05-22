package com.reacher.dao;

import com.reacher.domain.User;

import java.util.List;

public interface UserDao {

    /*
    * 查询所有用户,同时获取到用户下所有账户信息
    * */
    List<User> findAll();

    /*
    * 根据id查询一个用户
    * */
    User findById(Integer userId);

    /*
    * 更新用户信息
    * */
    void updateUser(User user);

}
