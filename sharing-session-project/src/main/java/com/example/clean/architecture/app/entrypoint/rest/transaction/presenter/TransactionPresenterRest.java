package com.example.clean.architecture.app.entrypoint.rest.transaction.presenter;

import com.example.clean.architecture.app.entrypoint.rest.common.PresenterUtils;
import com.example.clean.architecture.app.entrypoint.rest.common.ResponsePresenter;
import com.example.clean.architecture.app.entrypoint.rest.transaction.entity.TransactionDetailResponseRest;
import com.example.clean.architecture.app.entrypoint.rest.transaction.entity.TransactionResponseRest;
import com.example.clean.architecture.core.transaction.entity.TransactionResponse;
import com.example.clean.architecture.core.transaction.usecase.TransactionPresenter;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

public class TransactionPresenterRest implements TransactionPresenter, ResponsePresenter<TransactionResponseRest> {
    final AtomicReference<TransactionResponseRest> responseRest = new AtomicReference<>();

    @Override
    public Mono<Void> execute(TransactionResponse response) {
        return PresenterUtils.present(() -> responseRest.set(map(response)));
    }

    private TransactionResponseRest map(TransactionResponse response){
        return TransactionResponseRest.builder()
                .id(response.getId())
                .transactionDetail(
                        TransactionDetailResponseRest.builder()
                        .productCode(response.getTransactionDetail().getProductCode())
                        .qty(response.getTransactionDetail().getQty())
                        .currency(response.getTransactionDetail().getCurrency())
                        .totalPrice(response.getTransactionDetail().getTotalPrice())
                        .id(response.getTransactionDetail().getId())
                        .build()
                )
                .totalPrice(response.getTransactionDetail().getTotalPrice())
                .currency(response.getCurrency())
                .fullName(response.getFullName())
                .email(response.getEmail())
                .phone(response.getPhone())
                .lang(response.getLang())
                .build();
    }

    @Override
    public Mono<TransactionResponseRest> getResponse() {
        return PresenterUtils.getResponse(responseRest::get);
    }
}
