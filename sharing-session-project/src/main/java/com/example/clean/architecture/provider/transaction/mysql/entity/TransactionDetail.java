package com.example.clean.architecture.provider.transaction.mysql.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "insurance_transaction_detail")
public class TransactionDetail implements EntityMarker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String currency;

    @Column
    private String reference;

    @Column
    private int qty = 0;

    @Column
    private String productCode;

    @Column
    private double totalPrice = 0.0;

    @Version
    @Column
    private long version;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
//    private Transaction transaction;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "transactionId")
    private Transaction transaction;
}
