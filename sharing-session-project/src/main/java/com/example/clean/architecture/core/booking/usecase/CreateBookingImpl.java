package com.example.clean.architecture.core.booking.usecase;


import com.example.clean.architecture.core.booking.entity.InsuranceBookingRequest;
import com.example.clean.architecture.core.transaction.gateway.TransactionGateway;
import reactor.core.publisher.Mono;

public class CreateBookingImpl implements CreateBookingUseCase {

    private final TransactionGateway transactionGateway;

    public CreateBookingImpl(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }


    @Override
    public Mono<Void> execute(InsuranceBookingRequest request, CreateBookingPresenter presenter) {
        //do business logic here
        return transactionGateway.save(request).flatMap(presenter::execute);
    }
}
