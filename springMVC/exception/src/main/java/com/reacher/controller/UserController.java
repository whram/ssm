package com.reacher.controller;

import com.reacher.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final String success = "success";

    @RequestMapping("/testException")
    public String testException() throws SysException {
        System.out.println("testException执行了...");
        try {
            //模拟异常
            int n = 1/0;
        } catch (Exception e) {
            //控制台打印
            e.printStackTrace();
            //抛出自定义异常信息
            throw new SysException("xxx错误...");
        }
        return success;
    }

}
