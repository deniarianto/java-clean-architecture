package com.example.clean.architecture.app.entrypoint.rest.transaction.controller;

import com.example.clean.architecture.app.entrypoint.rest.transaction.entity.TransactionResponseRest;
import com.example.clean.architecture.app.entrypoint.rest.transaction.presenter.TransactionPresenterRest;
import com.example.clean.architecture.core.transaction.usecase.TransactionFindByIdUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionFindByIdUseCase transactionFindByIdUseCase;

    public TransactionController(TransactionFindByIdUseCase transactionFindByIdUseCase) {
        this.transactionFindByIdUseCase = transactionFindByIdUseCase;
    }

    @GetMapping("/{id}")
    public Mono<TransactionResponseRest> getById(@PathVariable Long id){
        return Mono.defer(() -> {
            TransactionPresenterRest presenter = new TransactionPresenterRest();
            return transactionFindByIdUseCase.execute(id, presenter)
                    .then(presenter.getResponse());
        });
    }
}
