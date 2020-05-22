package com.reacher.test;

import com.reacher.dao.UserDao;
import com.reacher.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");

        /*创建SqlSessionFactory工厂
        * 创建工厂mybatis使用了构建者模式 把对象的创建细节隐藏，是使用者调用方法即可拿到对象
        * SqlSessionFactoryBuilder为构建者
        * */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);

        /*使用工厂产生sqlSession对象
        * 生产SqlSession使用了工厂模式
        *   优势：解耦，降低类之间的依赖
        * */
        SqlSession sqlSession = factory.openSession();

        //使用sqlSession创建Dao接口的代理对象(Mybatis使用代理的方式将dao接口实现）不修改源码的基础上对已有方法增强
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //使用代理对象的方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //释放资源
        sqlSession.close();
        is.close();
    }
}
