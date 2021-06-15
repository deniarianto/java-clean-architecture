package com.example.clean.architecture.core.transaction.usecase;

import reactor.core.publisher.Mono;

public interface TransactionFindByIdUseCase {
    Mono<Void> execute(Long id, TransactionPresenter presenter);
}
