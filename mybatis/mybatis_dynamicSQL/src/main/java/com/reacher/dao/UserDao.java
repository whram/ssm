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
    * 根据id查询一个用户
    * */
    User findById(Integer userId);

    /*
    * 根据姓名查询用户
    * */
    List<User> findByName(String userName);

    /*
    *根据queryVo中的条件查询
    * */
    List<User> findByVo(QueryVo vo);

    /*
    * 根据参数条件查询
    * 参数可能为任意数量的字段
    * */
    List<User> findByCondition(User user);

    /*
    * 根据queryVo中的idList查询
    * */
    List<User> findUserInIdList(QueryVo vo);
}
