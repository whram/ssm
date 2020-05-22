package com.reacher.service.impl;

import com.reacher.dao.AccountDao;
import com.reacher.dao.impl.AccountDaoImpl;
import com.reacher.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/*
* 业务层实现类
*
* 曾经xml配置
*     <bean id = "accountService" class="com.reacher.service.impl.AccountServiceImpl"
*           scope="" init-method="" destroy-method="">
*         <property name="" value=""/ref=""></property>
*     </bean>
*
* 用于创建对象的 <bean>
        @Component:
        *   作用：用于把当前对象存入spring容器中
        *   属性：value用于指定bean的id，当不写时，默认值是当前类名首字母改小写
        @Controller：表现层
        @Service：业务层
        @Repository：持久层
        以上三个注解的作用和属性与Component相同
        * 这三个是spring框架为我们提供明确的三层使用的注解，使我们的三层对象更加清晰

* 用于注入数据的 <property>
        @Autowired：
                作用：自动按照类型注入，只要spring容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
                出现位置：可以是变量上和方法上
                *
                * 注：如果ioc容器中没有任何bean的类型和要注入的变量类型匹配。则报错
                *    如果ioc容器中有多个类型匹配时：先匹配bean类型，再找id和变量名的匹配
                *
                * 细节：在使用注解注入时，setXxx()就不是必须的了
         @Qualifier：
                *作用：再按照类中注入的基础之上再按照名称注入，它给类成员注入时不能单独使用，但是在给方法参数注入时可以
                * 属性：
                      value：用于指定注入bean的id
                      *
         @Resource
                作用：直接按照bean的id注入。可以独立使用
                属性：
                     name：用于指定bean的id

         * 以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现
         另外，集合类型的注入只能通过xml配置文件来实现
         *
         @Value
                作用：用于注入基本类型和String类型的数据
                属性：
                     value：用于指定数据的值，可以使用sping中SpEl（也就是spring的el表达式）
                                SpEl的写法：${表达式}

* 用于改变作用范围的
*       @Scope
*           作用：用于指定bean的作用范围
*           属性：
*               value：指定范围的取值，常用：singleton（默认） prototype
*
* 和生命周期相关 init-method destroy-method
*       @PreDestroy
*           作用：用于指定销毁方法
*       @PostConstruct
*           作用：用于指定初始化方法
 * */
@Service("accountService")
@Scope("singleton")
public class AccountServiceImpl implements AccountService {

    /*@Autowired
    @Qualifier("accountDao1")*/
    @Resource(name="accountDao1")
    private AccountDao accountDao2 = null;

    //构造函数，测试类被实例的时机
    public AccountServiceImpl() {
        System.out.println("对象被创建了");
    }

    public void saveAccount() {
        accountDao2.saveAccount();
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁...");
    }
}
