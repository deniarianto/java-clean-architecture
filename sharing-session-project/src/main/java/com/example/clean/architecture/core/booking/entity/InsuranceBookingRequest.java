package com.example.clean.architecture.core.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsuranceBookingRequest implements Serializable {

    private ContactDataRequest contact;
    private List<BookRequest> bookRequests;

}
