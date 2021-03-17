package com.xinle.springcloud.service;

import com.xinle.springcloud.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment findById(Long id);
}
