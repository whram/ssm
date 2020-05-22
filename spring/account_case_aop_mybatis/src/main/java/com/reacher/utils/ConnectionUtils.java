package com.reacher.utils;

import com.reacher.dao.AccountDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

/*
* 连接的工具类，用于从数据源中获取一个连接，并实现和线程的绑定
* */
public class ConnectionUtils {

    private InputStream is= Resources.getResourceAsStream("SqlMapConfig.xml");;
    private SqlSession sqlSession;

    public ConnectionUtils() throws IOException {
    }

    public SqlSession getSqlSession() {
        return this.sqlSession;
    }

    public AccountDao getAccountDao(){
        try {
            is = Resources.getResourceAsStream("SqlMapConfig.xml");
            sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(false);
            return sqlSession.getMapper(AccountDao.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy(){
        try {
            is.close();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
