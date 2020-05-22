package com.reacher.cglib;

import com.reacher.proxy.IProducer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
* 模拟一个消费者
* */
public class Client {

    public static void main(String[] args) {
        final Producer producer = new Producer();

        /*
        * 动态代理：
        *       特点：字节码随用随创建，随用随加载
        *       作用：不修改源码的基础上对方法增强
        *       分类：
        *           基于子类的动态代理：
        *                   涉及的类：Enhancer
        *                   提供者：cglib
        *           如何创建代理对象：
        *               使用Enhancer类中的create方法
        *           创建代理对象的要求：
        *               被代理类不能是最终类（最终类不能创建子类）
        *           create方法的参数：
        *               Class：字节码
        *                       用于指定被代理对象的字节码
        *               Callback：用于提供增强的代码
        *                       让我们写如何代理，一般都是写一些该接口的实现类，通常情况下都是匿名内部类，但不是必须的
        *                       此接口的实现类都是谁用谁写
        *                       一般写的都是该接口的子接口实现类：MethodInterceptor
        * */
        Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /*
             * 执行被代理对象对象的任何方法都会经过该方法
             *       Object o：代理对象的引用
             *      Method method：当前执行的方法
             *      Object[] objects：当前执行的方法所需的参数
             *          以上三个参数与基于接口的一样
             *      MethodProxy methodProxy：当前执行方法的代理对象
             * */
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                //提供增强的代码
                Object returnValue = null;
                //1.获取方法执行的参数
                Float money = (Float) objects[0];
                //2.判断当前方法是不是销售
                if ("saleProduct".equals(method.getName())) {
                    returnValue = method.invoke(producer, money * 0.8f);
                }
                return returnValue;
            }
        });

        cglibProducer.saleProduct(10000f);
    }

}
