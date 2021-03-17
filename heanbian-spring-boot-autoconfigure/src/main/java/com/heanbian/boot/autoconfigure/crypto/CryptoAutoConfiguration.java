package com.heanbian.boot.autoconfigure.crypto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.heanbian.block.crypto.AesTemplate;
import com.heanbian.block.crypto.RsaTemplate;

@Configuration
public class CryptoAutoConfiguration {

	@Bean
	public AesTemplate aesTemplate() {
		return new AesTemplate();
	}

	@Bean
	public RsaTemplate rsaTemplate() {
		return new RsaTemplate();
	}
}
