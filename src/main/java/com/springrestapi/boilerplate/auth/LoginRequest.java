package com.springrestapi.boilerplate.auth;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LoginRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}