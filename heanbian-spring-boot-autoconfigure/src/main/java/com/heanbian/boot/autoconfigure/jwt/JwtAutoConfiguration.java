package com.heanbian.boot.autoconfigure.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.heanbian.block.jwt.JwtTemplate;

@Configuration
public class JwtAutoConfiguration {

	@Bean
	public JwtTemplate jwtTemplate() {
		return new JwtTemplate();
	}
}
