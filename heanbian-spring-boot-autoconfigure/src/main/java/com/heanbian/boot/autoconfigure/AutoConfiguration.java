package com.heanbian.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.heanbian.block.crypto.AesTemplate;
import com.heanbian.block.crypto.EcTemplate;
import com.heanbian.block.crypto.ExRsaTemplate;
import com.heanbian.block.crypto.RsaTemplate;
import com.heanbian.block.elasticsearch.client.ElasticsearchTemplate;
import com.heanbian.block.email.EmailConfig;
import com.heanbian.block.email.EmailTemplate;
import com.heanbian.block.jwt.JwtTemplate;

@Configuration
@EnableConfigurationProperties({ //
		com.heanbian.boot.properties.ElasticsearchProperties.class, //
		com.heanbian.boot.properties.EmailProperties.class, //
		com.heanbian.boot.properties.OAuth2Properties.class })
public class AutoConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "heanbian.elasticsearch", name = "enabled", havingValue = "true")
	ElasticsearchTemplate elasticsearchTemplate(com.heanbian.boot.properties.ElasticsearchProperties ep) {
		return new ElasticsearchTemplate(ep.clusterNodes());
	}

	@Bean
	@ConditionalOnClass(AesTemplate.class)
	AesTemplate aesTemplate() {
		return new AesTemplate();
	}

	@Bean
	@ConditionalOnClass(RsaTemplate.class)
	RsaTemplate rsaTemplate() {
		return new RsaTemplate();
	}

	@Bean
	@ConditionalOnClass(ExRsaTemplate.class)
	ExRsaTemplate exRsaTemplate() {
		return new ExRsaTemplate();
	}

	@Bean
	@ConditionalOnClass(EcTemplate.class)
	EcTemplate ecTemplate() {
		return new EcTemplate();
	}

	@Bean
	@ConditionalOnProperty(prefix = "heanbian.email", name = "enabled", havingValue = "true")
	EmailTemplate emailTemplate(com.heanbian.boot.properties.EmailProperties ep) {
		return new EmailTemplate(
				EmailConfig.of(ep.host(), ep.port(), ep.username(), ep.password(), ep.from(), ep.debug()));
	}

	@Bean
	@ConditionalOnClass(JwtTemplate.class)
	JwtTemplate jwtTemplate() {
		return new JwtTemplate();
	}

}
