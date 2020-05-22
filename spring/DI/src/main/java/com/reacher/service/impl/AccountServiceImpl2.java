package com.reacher.service.impl;

import com.reacher.service.AccountService;

import java.util.Date;

/*
* 业务层实现类
* */
public class AccountServiceImpl2 implements AccountService {

    //如果是经常变化的数据并不适用注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println("service中的saveAccount方法执行了..."+name+", "+age+", "+birthday);
    }
}
