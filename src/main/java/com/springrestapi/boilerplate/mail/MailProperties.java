package com.springrestapi.boilerplate.mail;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    private String host;

    private String port;

    private String username;

    private String password;

    private String from;

    private String fromName;

    private String verificationApi;
}