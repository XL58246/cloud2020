package com.xinle.springcloud.dao;

import com.xinle.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment findById(@Param("id") Long id);

}
