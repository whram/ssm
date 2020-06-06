package com.reacher.controller;

import com.reacher.domain.Product;
import com.reacher.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    //查询全部产品
    @RequestMapping("/findAll.do")
    //@RolesAllowed({"ADMIN"})//指定使用方法的角色 JSR-250技术的注解 可以将ROLE_前缀省略
    @Secured("ROLE_ADMIN")//指定使用方法的角色 Secured技术的注解 不能省略ROLE_前缀
    public ModelAndView findAll() throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.findAll();
        mv.addObject("productList",productList);
        mv.setViewName("product-list");

        return mv;
    }

    //添加产品
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

}
