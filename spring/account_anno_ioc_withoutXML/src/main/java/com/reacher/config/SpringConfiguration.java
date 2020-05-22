package com.reacher.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/*
* 配置类，作用和bean.xml相同
* Spring中的新注解
* @Configuration
*       作用：指定当前类是一个注解类
*       细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写
* @ComponentScan
*       作用：用于通过注解指定Spring在创建容器时要扫描的包
*       属性：
*           value：它和basePackage的作用是一样的，都是用于指定创建容器时要扫描的包
*                   使用此注解就等同于在xml中配置了：
*                       <context:component-scan base-package="com.reacher"></context:component-scan>
* @Bean
*       作用：用于把当前方法的返回值作为bean对象存入Spring的IOC容器中
*       属性：
*           name：用于指定bean的id，默认值是当前方法的名称
*       细节：
*           当我们使用注解的方式时，如果有方法参数，Spring框架会去容器中查找有没有可用的bean对象。
*           查找方式和Autowired注解作用一样
* @Import
*       作用：用于导入其他的配置类
*       属性：
*           value：用于指定其他配置类的字节码
* @PropertySource
*       作用：用于指定properties文件的位置
*       属性：
*           value：指定文件的名称和路径    classpath：表示类路径
* */
//@Configuration
@ComponentScan(basePackages = {"com.reacher"})
@Import(JDBCConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {

}
