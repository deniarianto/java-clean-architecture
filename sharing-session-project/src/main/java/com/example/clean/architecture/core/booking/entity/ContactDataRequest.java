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
public class ContactDataRequest implements Serializable {
    private String title;
    private String firstName;
    private String lastName;
    private Date dob;
    private String email;
    private String phoneCode;
    private String phone;
}
