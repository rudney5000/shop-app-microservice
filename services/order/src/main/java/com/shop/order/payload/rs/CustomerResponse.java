package com.shop.order.payload.rs;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
