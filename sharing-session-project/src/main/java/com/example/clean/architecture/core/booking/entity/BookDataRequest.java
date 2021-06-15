package com.example.clean.architecture.core.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuperBuilder
@Setter
@Getter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class BookDataRequest implements Serializable {
    private Map<String, String> general;
    private List<Map<String, String>> paxes;
    private Map<String, String> additionalData;
    private Map<String, PriceComponent> priceBreakdown;
    private PriceComponent totalPrice;
}
