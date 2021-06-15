package com.example.clean.architecture.provider.transaction.mysql.repository;

import com.example.clean.architecture.provider.transaction.mysql.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
