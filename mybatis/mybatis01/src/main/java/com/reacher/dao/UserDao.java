package com.reacher.dao;


import com.reacher.domain.User;

import java.util.List;

/*
* 用户持久层接口
* */
public interface UserDao {
    /*
    * 查询所有
    * */
    List<User> findAll();
}
