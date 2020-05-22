package com.reacher.dao;

import com.reacher.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {

    /*
    * 查询所有账户，并且获取每个账户所属的用户信息
    * */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(property = "user",column = "uid",
                    one = @One(select = "com.reacher.dao.UserDao.findById", fetchType = FetchType.EAGER))//fetchType配置加载方式，立即加载
    })
    List<Account> findAll();

    /*
    * 根据用户id查询账户信息
    * */
    @Select("select * from account where uid = #{uid}")
    @ResultMap("accountMap")
    List<Account> findByUid(Integer uid);
}
