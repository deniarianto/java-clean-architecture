package com.example.dummy.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class DummyController {

    AtomicInteger counter = new AtomicInteger(0);

    @GetMapping("/findAll")
    public Mono<List<Product>> findAll() throws InterruptedException {
        Thread.sleep(60000);
        System.out.println("Service dipanggil : " + counter.getAndIncrement());
        return Mono.defer(() -> {
            Product product = new Product();
            product.price = 20000.0;
            product.productCode = "QWKEJ";
            product.productName = "Product 1";

            Product product1 = new Product();
            product1.price = 30000.0;
            product1.productCode = "SJJSK";
            product1.productName = "Product 2";
            return Mono.just(Arrays.asList(product, product1));
        });
    }

    class Product {
        public String productCode;
        public String productName;
        public Double price;
    }
}
