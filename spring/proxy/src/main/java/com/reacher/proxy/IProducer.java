package com.reacher.proxy;

/*
* 生产者的接口，规定生产者的功能
* */
public interface IProducer {

    /*
     * 销售
     * */
    void saleProduct(float money);

    /*
     * 售后
     * */
    void afterService(float money);

}
