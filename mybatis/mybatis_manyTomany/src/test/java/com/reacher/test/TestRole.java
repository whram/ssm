package com.reacher.test;

import com.reacher.dao.RoleDao;
import com.reacher.domain.Role;
import com.reacher.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestRole {

    private InputStream is;
    private SqlSession session;
    private RoleDao roleDao;

    @Before //用于测试方法执行前执行
    public void init() throws IOException {
        //读取配置文件，生成字节流
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //生产sqlSession对象
        session = new SqlSessionFactoryBuilder().build(is).openSession(true);//autoCommit:设置事务是否自动提交
        //使用SqlSession对象代理实现UserDao
        roleDao = session.getMapper(RoleDao.class);
    }

    @After//用于执行方法执行完后后执行
    public void destroy() throws IOException {
        //提交事务
        session.commit();
        session.close();
        is.close();
    }

    @Test
    public void testFindAll(){
        //调用findAll方法
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println("----每个----");
            System.out.println("角色： " + role);
            System.out.println("用户： "+role.getUsers());
        }
    }

}
