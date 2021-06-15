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
public class TransactionDetailResponse {
    private Long id;
    private String currency;
    private int qty = 0;
    private String productCode;
    private double totalPrice = 0.0;
}
