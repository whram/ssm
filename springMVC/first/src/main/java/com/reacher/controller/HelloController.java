package com.reacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器类
 * */
@Controller//springMVC是基于方法的，类是单例的服务启动便会创建，因此前端的请求会直接找到方法
@RequestMapping(path = "/user")//模块化
public class HelloController {

    /*
    * 前端控制器DispatcherServlet
    * 处理器映射器HandlerMapping
    * 处理器适配器HandlerAdapter
    * 视图解析器ViewResolver
    *
    * 处理器映射器HandlerMapping会根据请求路径映射到方法 将方法返回给前端控制器DispatcherServlet
    * DispatcherServlet 将方法请求给处理器适配器HandlerAdapter去执行
    * 执行完成后将方法返回结果ModelAndView返回给DispatcherServlet
    * DispatcherServlet将ModelAndView请求给视图解析器ViewResolver
    * 视图解析器ViewResolver会根据返回的结果返回view
    * */
    @RequestMapping(path="/hello")//请求路径
    public String sayHello () {
        System.out.println("Hello SpringMVC!");
        return "success";//视图解析器会根据返回值找到要跳转的页面
    }

    @RequestMapping(path = "/testRM")
    public String testRM(){
        System.out.println("test");
        return "success";
    }

}
