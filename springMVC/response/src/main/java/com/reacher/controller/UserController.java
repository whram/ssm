package com.reacher.controller;

import com.reacher.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    private final String success="success";

    /*
    * 返回String
    * */
    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString方法执行...");
        //模拟从数据库中查询对象
        User user = new User();
        user.setUsername("啊啊");
        user.setPassword("123");
        user.setAge(20);
        //model对象
        model.addAttribute("user",user);
        return success;
    }

    /*
    * 返回void
    * 无返回值默认请求 请求路径.jsp页面
    *
    * 请求转发是一次请求，不用编写项目名称
    * */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("testVoid方法执行...");
        //手动请求转发
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);

        //重定向
        //response.sendRedirect(request.getContextPath()+"/index.jsp");

        //设置中文乱码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        //直接进行响应
        response.getWriter().println("你好");
        return;
    }

    /*
    * 返回ModelAndView对象
    * */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        System.out.println("testModelAndView方法执行...");
        //模拟从数据库中查询对象
        User user = new User();
        user.setUsername("啊啊");
        user.setPassword("123");
        user.setAge(20);
        //把user对象存储到mv对象中，同时也会存入request对象中
        mv.addObject("user",user);

        //设置跳转页面
        mv.setViewName(success);//调用视图选择器

        return mv;
    }

    /*
    * 使用关键字的方式进行转发和重定向
    * */
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        System.out.println("testForwardOrRedirect方法执行...");

        //请求转发
        //return "forward:/WEB-INF/pages/success.jsp";

        //重定向
        return "redirect:/index.jsp";//底层已经将项目名称添加
    }

    /*
    * 模拟异步请求和响应的过程
    * */
    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println("testAjax方法执行...");
        //客户端发送ajax请求，传的是json字符串，后端把json字符串封装到user对象中
        System.out.println(user);
        //响应，模拟查询数据
        user.setUsername("宝宝");
        user.setAge(21);
        //做响应
        return user;
    }

}
