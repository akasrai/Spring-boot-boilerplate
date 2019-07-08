package com.springrestapi.boilerplate.user.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AddressResponse {
    private String addressLine = "135 Charkhal Rd, Kathmandu";

    private String city = "Kathmandu";

    private String country = "Nepal";

    private String state = "Bagmati";

    private String zipCode = "44600";
}

