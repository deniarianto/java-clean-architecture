package com.example.clean.architecture.core.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@SuperBuilder
@Setter
@Getter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest implements Serializable {
    private String productCode;
    private int qty;
    private Date dateFrom;
    private Date dateTo;
    private BookDataRequest bookData;
}
