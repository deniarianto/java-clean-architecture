package com.example.clean.architecture.core.product.usecase;

import reactor.core.publisher.Mono;

public interface ProductFindAllUseCase {
    Mono<Void> execute(ProductPresenter presenter);
}
