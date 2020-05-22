package com.reacher.jdbctemplate;

import com.reacher.dao.AccountDao;
import com.reacher.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/*
* JdbcTemplate的最基本用法
* */
public class JdbcTemplateDemo4 {
    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountDao accountDao = ac.getBean("accountDao",AccountDao.class);
        Account account = accountDao.findById(1);
        System.out.println(account);

    }
}
