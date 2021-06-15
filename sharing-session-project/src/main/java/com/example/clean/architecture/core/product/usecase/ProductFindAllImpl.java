package com.example.clean.architecture.core.product.usecase;

import com.example.clean.architecture.core.product.gateway.ProductGateway;
import reactor.core.publisher.Mono;

public class ProductFindAllImpl implements ProductFindAllUseCase {

    private final ProductGateway productGateway;

    public ProductFindAllImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Mono<Void> execute(ProductPresenter presenter) {
        return productGateway.findAllProduct().flatMap(presenter::execute);
    }
}
