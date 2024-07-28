package com.shop.order.payload;

import com.shop.order.model.PaymentMethod;
import com.shop.order.payload.rs.CustomerResponse;
import com.shop.order.payload.rs.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
