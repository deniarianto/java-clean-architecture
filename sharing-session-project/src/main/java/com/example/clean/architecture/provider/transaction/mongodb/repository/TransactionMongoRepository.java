package com.example.clean.architecture.provider.transaction.mongodb.repository;

import com.example.clean.architecture.provider.transaction.mongodb.entity.TransactionMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TransactionMongoRepository extends ReactiveMongoRepository<TransactionMongo, String> {
    Mono<TransactionMongo> findByTransactionId(Long id);
}
