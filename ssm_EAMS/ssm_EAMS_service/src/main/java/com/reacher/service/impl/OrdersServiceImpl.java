package com.reacher.service.impl;

import com.github.pagehelper.PageHelper;
import com.reacher.dao.OrdersDao;
import com.reacher.domain.Orders;
import com.reacher.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);//pageNum开始页码值，pageSize每页显示条数。必须挨着出巡
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return ordersDao.findById(id);
    }
}
