package com.example.clean.architecture.app.entrypoint.rest.product.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductResponseRest {
    private String productName;
    private String productCode;
    private Double price;
}
