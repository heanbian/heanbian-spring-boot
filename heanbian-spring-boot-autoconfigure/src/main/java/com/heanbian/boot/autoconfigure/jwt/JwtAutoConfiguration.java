package com.heanbian.boot.autoconfigure.jwt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.heanbian.block.jwt.JwtTemplate;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "heanbian.jwt", name = "enabled", havingValue = "true")
	public JwtTemplate jwtTemplate(JwtProperties jp) {
		return new JwtTemplate();
	}
}
