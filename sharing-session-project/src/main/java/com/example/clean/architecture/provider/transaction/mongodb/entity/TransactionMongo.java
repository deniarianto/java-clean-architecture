package com.example.clean.architecture.provider.transaction.mongodb.entity;

import com.example.clean.architecture.provider.transaction.mysql.entity.TransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "transaction")
public class TransactionMongo {
    @Id
    @Field(value = "_id")
    private String id;

    @Field
    private Long transactionId;

    @Field
    private String lang;

    @Field
    private String currency;

    @Field
    private String title;

    @Field
    private String fullName;

    @Field
    private String email;

    @Field
    private String phone;

    @Field
    private double totalPrice = 0;

    @Version
    @Field
    private long version;
}
