package com.example.clean.architecture.provider.transaction.mysql.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "insurance_transaction")
public class Transaction implements EntityMarker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String lang;
    @Column
    private String currency;
    @Column
    private String title;
    @Column
    private String fullName;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private double totalPrice = 0;
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionDetail> transactionDetails = new ArrayList<>();
    @Version
    @Column
    private long version;
}
