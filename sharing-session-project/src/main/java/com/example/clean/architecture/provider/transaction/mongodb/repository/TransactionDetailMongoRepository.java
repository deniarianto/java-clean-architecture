package com.example.clean.architecture.provider.transaction.mongodb.repository;

import com.example.clean.architecture.provider.transaction.mongodb.entity.TransactionDetailMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransactionDetailMongoRepository extends ReactiveMongoRepository<TransactionDetailMongo, String> {
}
