package com.reacher.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

/*
* 和Spring连接数据库相关的配置类
* */
//@Configuration
public class JDBCConfig {

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    /*
     * 用于创建一个QueryRunner对象
     * */
    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(@Qualifier("dataSource1") DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    /*
     * 创建数据源对象
     * */
    @Bean(name = "dataSource1")
    public DataSource createDataSource1(){
        try {
            ComboPooledDataSource dataSource =new ComboPooledDataSource();
            dataSource.setDriverClass(driver);
            dataSource.setJdbcUrl(url);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Bean(name = "dataSource2")
    public DataSource createDataSource2(){
        try {
            ComboPooledDataSource dataSource =new ComboPooledDataSource();
            dataSource.setDriverClass(driver);
            dataSource.setJdbcUrl("jdbc:mysql://192.168.2.110/mybatis");
            dataSource.setUser(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
