package com.example.clean.architecture.app.entrypoint.rest.product.controller;

import com.example.clean.architecture.app.entrypoint.rest.product.entity.ProductResponseRest;
import com.example.clean.architecture.app.entrypoint.rest.product.presenter.ProductRestPresenter;
import com.example.clean.architecture.core.product.usecase.ProductFindAllUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductFindAllUseCase productFindAllUseCase;

    public ProductController(ProductFindAllUseCase productFindAllUseCase) {
        this.productFindAllUseCase = productFindAllUseCase;
    }

    @GetMapping("/findAll")
    public Mono<List<ProductResponseRest>> getById(){
        return Mono.defer(() -> {
            ProductRestPresenter presenter = new ProductRestPresenter();
            return productFindAllUseCase.execute(presenter)
                    .then(presenter.getResponse());
        });
    }
}
