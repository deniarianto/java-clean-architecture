package com.example.clean.architecture.core.transaction.usecase;

import com.example.clean.architecture.core.transaction.gateway.TransactionGateway;
import reactor.core.publisher.Mono;

public class TransactionFindByIdImpl implements TransactionFindByIdUseCase {
    private final TransactionGateway transactionGateway;

    public TransactionFindByIdImpl(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public Mono<Void> execute(Long id, TransactionPresenter presenter) {
        return transactionGateway.findById(id).flatMap(presenter::execute);
    }
}
