package com.example.clean.architecture.core.transaction.entity;

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
public class TransactionResponse {
    private Long id;
    private TransactionDetailResponse transactionDetail;
    private String lang;
    private String fullName;
    private String email;
    private String phone;
    private String currency;
    private double totalPrice;
}
