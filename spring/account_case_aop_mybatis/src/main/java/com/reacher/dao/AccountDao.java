package com.reacher.dao;

import com.reacher.domain.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*
* 账户的持久层接口
* */
public interface AccountDao {

    /*
     * 查询所有
     * */
    @Select("select * from account")
    List<Account> findAllAccount();

    /*
     * 查询一个
     * */
    @Select("select * from account where id =#{id}")
    Account findById(Integer accountId);

    /*
     * 保存操作
     * */
    @Insert("insert into account(name,money) value(#{name},#{money})")
    void saveAccount(Account account);

    /*
     * 更新
     * */
    @Update("update account set name = #{name}, money = #{money} where id = #{id}")
    void updateAccount(Account account);

    /*
     * 删除账户
     * */
    @Delete("delete from account where id = #{id}")
    void deleteAccount(Integer accountId);

    /*
    *根据名称查询账户
    * 如果有唯一结果，返回结果
    * 如果结果不唯一，异常
    * */
    @Select("select * from account where name = #{name}")
    Account findAccountByName(String name);
}
