package com.example.clean.architecture.core.transaction.usecase;

import com.example.clean.architecture.core.transaction.entity.TransactionResponse;
import reactor.core.publisher.Mono;

public interface TransactionPresenter {
    Mono<Void> execute(TransactionResponse response);
}
