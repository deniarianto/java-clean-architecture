package com.example.clean.architecture.core.booking.usecase;

import com.example.clean.architecture.core.booking.entity.InsuranceBookingRequest;
import reactor.core.publisher.Mono;

public interface CreateBookingUseCase {
    Mono<Void> execute(InsuranceBookingRequest request, CreateBookingPresenter presenter);
}
