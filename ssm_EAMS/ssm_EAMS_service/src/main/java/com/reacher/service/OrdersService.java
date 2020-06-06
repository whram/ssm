package com.reacher.service;

import com.reacher.domain.Orders;

import java.util.List;


public interface OrdersService {

    List<Orders> findAll(int page,int pageSize) throws Exception;

    Orders findById(String id) throws Exception;

}
