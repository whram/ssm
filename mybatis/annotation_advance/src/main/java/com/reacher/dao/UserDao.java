package com.reacher.dao;

import com.reacher.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*
* 在mybatis中针对CRUD一共有四个注解
*       @Select() @Insert() @Update() @Delete()
*
* 配置文件和注解同时出现mybatis必然会报错
*       注解和xml不能同时出现
* */
public interface UserDao {

    /*
    * 查询所有用户
    * */
    @Select("select * from user")
    List<User> findAll();

    /*
    * 保存用户
    * */
    @Insert("insert into user(username, address, sex, birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /*
    * 更新用户信息
    * */
    @Update("update user set username = #{username}, birthday= #{birthday} where id = #{id}")
    void updateUser(User user);

    /*
    * 删除用户
    * */
    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

    /*
    * 根据id查询用户
    * */
    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    /*
    * 根据用户名模糊查询
    * */
    //@Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%'")//value为固定关键字
    List<User> findUserByName(String username);

    /*
    * 查询总用户数
    * */
    @Select("select count(*) from user ")
    int findTotal();
}
