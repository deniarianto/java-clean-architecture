package com.example.clean.architecture.app.entrypoint.rest.booking.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class InsuranceBookingResponseRest {
    private Long transactionId;
    private Long transactionDetailId;
    private String productCode;
    private int qty;
    private String lang;
    private String fullName;
    private String email;
    private String phone;
    private String currency;
    private double totalPrice;
}
