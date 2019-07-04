package com.springrestapi.boilerplate.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserResponse {
       private Long id;

       private String firstName;

       private String middleName;

       private String lastName;

       private String email;

       private String phoneNumber;

       private String imageUrl;

       private Boolean isEmailVerified;

       private Boolean isPhoneNumberVerified;

       private String refreshToken;

}