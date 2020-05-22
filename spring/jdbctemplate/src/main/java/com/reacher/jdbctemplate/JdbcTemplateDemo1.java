package com.reacher.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/*
* JdbcTemplate的最基本用法
* */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {

        //准备数据源：spring内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.2.110:3306/spring");
        ds.setUsername("root");
        ds.setPassword("68845292");

        //创建jdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();
        //给jt设置数据源
        jt.setDataSource(ds);
        //执行操作
        jt.execute("insert into account(name ,money) values('ddd' ,1000)");

    }
}
