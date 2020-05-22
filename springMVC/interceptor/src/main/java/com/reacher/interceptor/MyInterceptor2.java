package com.reacher.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 自定义拦截器
* */
public class MyInterceptor2 implements HandlerInterceptor {

    /*
    * 预处理，controller方法前执行
    * return true 放行，执行下一个拦截器，如果没有则执行controller的方法
    *        false 不放行，跳转页面或者其他处理
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor2拦截器执行了...前");
        //request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
        return true;
    }

    /*
    * 后处理方法，controller方法执行后，success.jsp执行之前
    * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor2拦截器执行了...后");
        //request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
    }

    /*
    * success.jsp页面执行后执行
    * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor2拦截器执行了...success后");
    }
}
