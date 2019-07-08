package com.springrestapi.boilerplate.user.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BasicInfoResponse {
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String imageUrl;

    private AddressResponse address;
}