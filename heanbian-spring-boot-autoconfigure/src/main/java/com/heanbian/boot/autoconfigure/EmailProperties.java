package com.heanbian.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("heanbian.email")
public record EmailProperties(@DefaultValue("false") boolean enabled, //
		String host, //
		int port, //
		String username, //
		String password, //
		String from, //
		boolean debug) {

}