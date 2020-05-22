package com.reacher.jdbc;

import java.sql.*;

/*
* 程序的耦合
*       耦合：程序间的依赖关系
*           包括：
*               类之间的依赖
*               方法间的依赖
*       解耦：降低程序间的依赖关系
*       实际开发中：
*               应做到编译期不依赖，运行时才依赖
*       解决：
*           1.使用反射来创建对象，而避免new对象
*           2.通过读取配置文件来获取要创建的对象的全限定类名
* */

public class JdbcDemo01 {
    public static void main(String[] args) throws SQLException {
        //注册驱动
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.2.110:3306/spring", "root", "68845292");
        //获取操作数据库的预处理对象
        PreparedStatement pstm = conn.prepareStatement("select * from account");
        //执行sql语句得到结果集
        ResultSet rs = pstm.executeQuery();
        //遍历/封装结果集
        while (rs.next()){
            System.out.println(rs.getString("name"));
        }
        //释放资源
        rs.close();
        pstm.close();
        conn.close();
    }
}
