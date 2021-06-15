package com.example.clean.architecture.core.product.gateway;

import com.example.clean.architecture.core.product.entity.ProductResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductGateway {
    Mono<List<ProductResponse>> findAllProduct();
}
