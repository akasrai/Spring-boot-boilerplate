package com.springrestapi.boilerplate.auth;

import com.springrestapi.boilerplate.user.dto.BasicInfoResponse;
import com.springrestapi.boilerplate.user.dto.StatusResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AuthResponse {
    private String accessToken;

    private String refreshToken;

    private String tokenType = "Bearer";

    private BasicInfoResponse user;

    private StatusResponse status;
}