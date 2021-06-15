package com.example.clean.architecture.core.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Setter
@Getter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class PriceComponent implements Serializable {
    private double price;
    private double basePrice;
    private double nta;
    private double tax;
    private double commission;
}
