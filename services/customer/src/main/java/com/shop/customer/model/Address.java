package com.shop.customer.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
