package com.springrestapi.boilerplate.user.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StatusResponse {
    private Boolean isEmailVerified;

    private Boolean isPhoneNumberVerified;

}
