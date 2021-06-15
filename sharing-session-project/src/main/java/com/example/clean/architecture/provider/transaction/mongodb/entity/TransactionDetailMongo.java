package com.example.clean.architecture.provider.transaction.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "transaction_detail")
public class TransactionDetailMongo {
    @Id
    @Field(value = "_id")
    private String id;

    @Field
    private Long transactionDetailId;

    @Field
    private String currency;

    @Field
    private String reference;

    @Field
    private int qty = 0;

    @Field
    private String productCode;

    @Field
    private double totalPrice = 0.0;

    @Field
    private Long transactionId;
}
