package com.reacher.dao;

import com.reacher.domain.QueryVo;
import com.reacher.domain.User;

import java.util.List;

public interface UserDao {

    /*
    * 查询所有用户
    * */
    List<User> findAll();

    /*
    * 保存用户
    * */
    void saveUser(User user);

    /*
    * 更新用户
    * */
    void updateUser(User user);

    /*
    * 删除用户
    * */
    void deleteUser(Integer userId);

    /*
    * 根据id查询一个用户
    * */
    User findById(Integer userId);

    /*
    * 根据姓名查询用户
    * */
    List<User> findByName(String userName);

    /*
    * 查询总记录数
    * */
    int findTotal();

    /*
    *根据queryVo中的条件查询
    * */
    List<User> findByVo(QueryVo vo);
}
