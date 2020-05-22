package com.reacher.dao;

import com.reacher.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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
    @Results(id = "userMap",value = {
            @Result(id = true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "address", property = "userAddress"),
            @Result(column = "sex", property = "userSex"),
            @Result(column = "birthday", property = "userBirthday"),
            @Result(property = "accounts", column = "id",
                    many = @Many(select = "com.reacher.dao.AccountDao.findByUid", fetchType = FetchType.LAZY))//fetchType配置加载方式，延迟加载
    })
    List<User> findAll();

    /*
    * 根据id查询用户
    * */
    @Select("select * from user where id = #{id}")
    //@ResultMap(value = {"userMap"})
    @ResultMap("userMap")
    User findById(Integer id);

    /*
    * 根据用户名模糊查询
    * */
    //@Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%'")//value为固定关键字
    @ResultMap("userMap")
    List<User> findUserByName(String username);

}
