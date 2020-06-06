package com.reacher.controller;

import com.github.pagehelper.PageInfo;
import com.reacher.domain.Orders;
import com.reacher.service.OrdersService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    //查询全部订单---分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "pageSize", required = true, defaultValue = "4") Integer pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();

        List<Orders> ordersList = ordersService.findAll(page,pageSize);
        //PageInfo就是一个分页的bean
        PageInfo pageInfo = new PageInfo(ordersList);

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");

        return mv;
    }

    //根据id查询订单信息
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
