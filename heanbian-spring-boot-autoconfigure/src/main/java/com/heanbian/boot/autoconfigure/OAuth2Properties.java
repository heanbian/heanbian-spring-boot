package com.heanbian.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("heanbian.oauth2")
public record OAuth2Properties(String logoutUrl) {

}
