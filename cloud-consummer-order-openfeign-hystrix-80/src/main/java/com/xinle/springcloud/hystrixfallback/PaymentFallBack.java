package com.xinle.springcloud.hystrixfallback;

import com.xinle.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallBack implements PaymentService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "服务器崩溃/(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_timeOut(Integer id) {
        return "服务器崩溃/(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_error(Integer id) {
        return "服务器崩溃/(ㄒoㄒ)/~~";
    }
}
