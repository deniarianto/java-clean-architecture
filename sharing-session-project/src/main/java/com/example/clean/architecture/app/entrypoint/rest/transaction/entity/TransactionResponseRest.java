package com.example.clean.architecture.app.entrypoint.rest.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TransactionResponseRest {
    private Long id;
    private TransactionDetailResponseRest transactionDetail;
    private String lang;
    private String fullName;
    private String email;
    private String phone;
    private String currency;
    private double totalPrice;
}
