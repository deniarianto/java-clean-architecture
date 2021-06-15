package com.example.clean.architecture.provider.transaction.mysql.repository;

import com.example.clean.architecture.provider.transaction.mysql.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
}
