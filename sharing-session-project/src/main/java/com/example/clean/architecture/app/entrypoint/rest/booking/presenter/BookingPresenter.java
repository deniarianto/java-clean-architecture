package com.example.clean.architecture.app.entrypoint.rest.booking.presenter;

import com.example.clean.architecture.app.entrypoint.rest.booking.entity.InsuranceBookingResponseRest;
import com.example.clean.architecture.app.entrypoint.rest.common.PresenterUtils;
import com.example.clean.architecture.app.entrypoint.rest.common.ResponsePresenter;
import com.example.clean.architecture.core.booking.usecase.CreateBookingPresenter;
import com.example.clean.architecture.core.transaction.entity.TransactionResponse;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

public class BookingPresenter implements CreateBookingPresenter, ResponsePresenter<InsuranceBookingResponseRest> {
    final AtomicReference<InsuranceBookingResponseRest> responseRest = new AtomicReference<>();

    @Override
    public Mono<Void> execute(TransactionResponse response) {
        return PresenterUtils.present(() -> responseRest.set(map(response)));
    }

    private InsuranceBookingResponseRest map(TransactionResponse response){
        return InsuranceBookingResponseRest.builder()
                .transactionId(response.getId())
                .transactionDetailId(response.getTransactionDetail().getId())
                .productCode(response.getTransactionDetail().getProductCode())
                .totalPrice(response.getTransactionDetail().getTotalPrice())
                .currency(response.getCurrency())
                .qty(response.getTransactionDetail().getQty())
                .fullName(response.getFullName())
                .email(response.getEmail())
                .phone(response.getPhone())
                .lang(response.getLang())
                .build();
    }

    @Override
    public Mono<InsuranceBookingResponseRest> getResponse() {
        return PresenterUtils.getResponse(responseRest::get);
    }
}
