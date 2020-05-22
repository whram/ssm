package com.reacher.ui;

import com.reacher.dao.AccountDao;
import com.reacher.service.AccountService;
import com.reacher.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/*
* 模拟表现层，用于调用业务
* */
public class Client {

    /*
    * 获取spring的ioc核心容器，并根据id获取对象
    *
    * ApplicationContext三个常用实现类:
    *       ClassPathXmlApplicationContext：加载类路径下的配置文件，配置文件必须在类路径下
    *       FileSystemXmlApplicationContext：加载任意路径下的配置文件
    *       AnnotationConfigApplicationContext：用于读取注解创建容器的
    *
    * 核心容器的两个接口引发的问题
    *   ApplicationContext： 单例对象适用  更多采用此接口
    *       采用立即加载的方式，一读取完配置文件就立即创建配置文件中配置的对象
    *   BeanFactory：  多例对象适用
    *       采用延迟加载的方式，什么时候根据id获取对象了，什么时候才创建对象
    * */
    public static void main(String[] args) {
        //------------ApplicationContext------------
        /*//获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //根据id获取bean对象
        AccountService aService = (AccountService)ac.getBean("accountService");
        AccountDao aDao = ac.getBean("accountDao",AccountDao.class);

        System.out.println(aService);
        System.out.println(aDao);*/

        //----------------BeanFactory------------------
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        AccountService aService = (AccountService)factory.getBean("accountService");
        System.out.println(aService);
    }
}
