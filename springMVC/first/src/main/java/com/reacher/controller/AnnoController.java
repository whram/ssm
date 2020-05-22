package com.reacher.controller;

import com.reacher.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/*
* 常用的注解
* */
@Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"msg"})//把msg又存入到session域中一份
public class AnnoController {

    private final String success = "success";

    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name = "name",required = true) String username) {
        System.out.println(username);
        return success;
    }

    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println(body);
        return success;
    }

    @RequestMapping(value = "/testPathVariable/{sid}")
    public String testPathVariable(@PathVariable(name = "sid") String id) {
        System.out.println(id);
        return success;
    }

    /*
    * 获取请求头信息
    * */
    @RequestMapping(value = "/testRequestHeader")
    public String testRequestHeader(@RequestHeader("Accept") String header) {
        System.out.println(header);
        return success;
    }

    /*
     * 获取cookie的值
     * */
    @RequestMapping(value = "/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String cookieValue) {
        System.out.println(cookieValue);
        return success;
    }

    /*
    * ModelAttribute注解
    * */
    @RequestMapping(value = "/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("abc") User user) {
        System.out.println("testModelAttribute...");
        System.out.println(user);
        return success;
    }

    /*
    * @ModelAttribute
    *   加了此注解的方法会优先于控制器中的其他方执行
    * 有返回值 将补的属性返回为对象
    * */
    /*@ModelAttribute
    public User showUser(String uname) {
        System.out.println("showUser...");
        //通过用户名查数据（模拟）
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        return user;
    }*/

    /*
    * 无返回值
    *   将补的值存入map中
    * */
    @ModelAttribute
    public void showUser(String uname, Map<String,User> map) {
        System.out.println("showUser...");
        //通过用户名查数据（模拟）
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        map.put("abc",user);
    }

    /*
    * SessionAttributes的注解
    *
    * */
    @RequestMapping(value = "/testSessionAttributes")
    public String testSessionAttributes(Model model) {
        System.out.println("testSessionAttributes...");
        model.addAttribute("msg", "阿啊啊");
        return success;
    }

    /*
    * 从session域中取值
    * */
    @RequestMapping(value = "/testGetSessionAttributes")
    public String testGetSessionAttributes(ModelMap model) {
        System.out.println("testSessionAttributes...");
        System.out.println((String) model.get("msg"));
        return success;
    }

    @RequestMapping(value = "/testDelSessionAttributes")
    public String testDelSessionAttributes(SessionStatus status) {
        System.out.println("testSessionAttributes...");
        status.setComplete();
        return success;
    }

}
