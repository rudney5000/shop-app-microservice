package com.shop.order.payload.rq;

import com.shop.order.model.PaymentMethod;
import com.shop.order.payload.rs.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
