package com.reacher.service;

import com.reacher.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
