package com.reacher.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
* 创建Bean对象的工厂
*       创建service和dao对象的
*
* Bean在计算机英语中，有可重用组件的含义
* JAVABean：用java语言编写的可重用组件
*       JavaBean远大与实体类
*
* 1.需要一个配置文件来配置service和dao
*       配置内用：唯一标识对应全限定类名
* 2.通过读取配置文件中的配置内容，反射创建的对象
*
* 配置文件可以是xml和properties
* */
public class BeanFactory {
    //定义一个Properties对象
    private static Properties props;

    //定义一个map，用于存储创建的对象我们把它称作容器
    // 不使用map时创建对象时是多例的性能低于单例，然而service不存在线程安全问题，因此使用map改造成单例
    private static Map<String,Object> beans;

    //使用静态代码块
    static {
        try {
            //实例化对象
            props = new Properties();
            //获取properties文件流对象
            InputStream is =BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(is);
            //实例化容器
            beans = new HashMap<String, Object>();
            //取出配置文件中所有的key
            Enumeration keys = props.keys();
            //遍历枚举
            while (keys.hasMoreElements()){
                //取出每个key
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath = props.getProperty(key);
                //使用反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                //把key和value存入容器中
                beans.put(key,value);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    /*
    * 改造后的创建单例对象
    * */
    public static Object getBean(String name) {
        return beans.get(name);
    }

    /*
    * 根据bean的名称获取bean对象
    * */
    /*public static Object getBean(String beanName){
        Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }*/
}
