package com.example.clean.architecture.app.entrypoint.rest.product.presenter;

import com.example.clean.architecture.app.entrypoint.rest.common.PresenterUtils;
import com.example.clean.architecture.app.entrypoint.rest.common.ResponsePresenter;
import com.example.clean.architecture.app.entrypoint.rest.product.entity.ProductResponseRest;
import com.example.clean.architecture.core.product.entity.ProductResponse;
import com.example.clean.architecture.core.product.usecase.ProductPresenter;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ProductRestPresenter implements ProductPresenter, ResponsePresenter<List<ProductResponseRest>> {

    final AtomicReference<List<ProductResponseRest>> responseRest = new AtomicReference<>();

    @Override
    public Mono<Void> execute(List<ProductResponse> response) {
        return PresenterUtils.present(() -> responseRest.set(map(response)));
    }

    private List<ProductResponseRest> map(List<ProductResponse> responses) {
        List<ProductResponseRest> responseList = new ArrayList<>();
        for(ProductResponse response : responses){
            ProductResponseRest prodRes = ProductResponseRest.builder()
                    .productCode(response.getProductCode())
                    .productName(response.getProductName())
                    .price(response.getPrice())
                    .build();
            responseList.add(prodRes);
        }
        return responseList;
    }

    @Override
    public Mono<List<ProductResponseRest>> getResponse() {
        return PresenterUtils.getResponse(responseRest::get);
    }
}
