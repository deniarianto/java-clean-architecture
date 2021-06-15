package com.example.clean.architecture.core.booking.usecase;

import com.example.clean.architecture.core.transaction.entity.TransactionResponse;
import reactor.core.publisher.Mono;

public interface CreateBookingPresenter {
    Mono<Void> execute(TransactionResponse response);
}
