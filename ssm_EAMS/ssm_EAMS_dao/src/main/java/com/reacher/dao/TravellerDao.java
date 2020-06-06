package com.reacher.dao;

import com.reacher.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {

    //多表查询
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{OrderId})")
    List<Traveller> findByOrderId(String OrderId);

}
