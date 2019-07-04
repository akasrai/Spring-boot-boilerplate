package com.springrestapi.boilerplate.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springrestapi.boilerplate.auth.AuthProvider;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Email
    @Column(nullable = false)
    private String email;

    @Column
    private String phoneNumber;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean isEmailVerified = false;

    @Column(nullable = false)
    private Boolean isPhoneNumberVerified = false;

    @Column
    private String refreshToken;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;
}