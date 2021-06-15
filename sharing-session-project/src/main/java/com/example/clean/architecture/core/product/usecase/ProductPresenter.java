package com.example.clean.architecture.core.product.usecase;

import com.example.clean.architecture.core.product.entity.ProductResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductPresenter {
    Mono<Void> execute(List<ProductResponse> response);
}
