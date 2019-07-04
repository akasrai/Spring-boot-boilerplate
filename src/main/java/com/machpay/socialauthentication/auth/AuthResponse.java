package com.machpay.socialauthentication.auth;

import com.machpay.socialauthentication.user.UserResponse;
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
    private String tokenType = "Bearer";
    private UserResponse user;

    public AuthResponse(String accessToken,UserResponse user) {
        this.accessToken = accessToken;
        this.user = user;
    }
}