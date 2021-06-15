package com.example.clean.architecture.core.transaction.gateway;

import com.example.clean.architecture.core.booking.entity.InsuranceBookingRequest;
import com.example.clean.architecture.core.transaction.entity.TransactionResponse;
import reactor.core.publisher.Mono;

public interface TransactionGateway {
    Mono<TransactionResponse> save(InsuranceBookingRequest insuranceBookingRequest);
    Mono<TransactionResponse> findById(Long id);
}
