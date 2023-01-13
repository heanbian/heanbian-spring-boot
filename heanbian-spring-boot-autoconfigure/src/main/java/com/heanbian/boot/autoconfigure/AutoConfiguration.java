package com.heanbian.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
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
		com.heanbian.boot.autoconfigure.ElasticsearchProperties.class, //
		com.heanbian.boot.autoconfigure.EmailProperties.class, //
		com.heanbian.boot.autoconfigure.OAuth2Properties.class, //
		com.heanbian.boot.autoconfigure.OssProperties.class })
public class AutoConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "heanbian.elasticsearch", name = "enabled", havingValue = "true")
	public ElasticsearchTemplate elasticsearchTemplate(com.heanbian.boot.autoconfigure.ElasticsearchProperties ep) {
		return new ElasticsearchTemplate(ep.clusterNodes());
	}

	@Bean
	@ConditionalOnClass(AesTemplate.class)
	public AesTemplate aesTemplate() {
		return new AesTemplate();
	}

	@Bean
	@ConditionalOnClass(RsaTemplate.class)
	public RsaTemplate rsaTemplate() {
		return new RsaTemplate();
	}
	
	@Bean
	@ConditionalOnClass(ExRsaTemplate.class)
	public ExRsaTemplate exRsaTemplate() {
		return new ExRsaTemplate();
	}

	@Bean
	@ConditionalOnClass(EcTemplate.class)
	public EcTemplate ecTemplate() {
		return new EcTemplate();
	}

	@Bean
	@ConditionalOnProperty(prefix = "heanbian.email", name = "enabled", havingValue = "true")
	public EmailTemplate emailTemplate(com.heanbian.boot.autoconfigure.EmailProperties ep) {
		return new EmailTemplate(
				new EmailConfig(ep.host(), ep.port(), ep.username(), ep.password(), ep.from(), ep.debug()));
	}

	@Bean
	@ConditionalOnClass(JwtTemplate.class)
	public JwtTemplate jwtTemplate() {
		return new JwtTemplate();
	}
	
	@Bean
	@ConditionalOnProperty(prefix = "heanbian.oss", name = "enabled", havingValue = "true")
	public OSS oss(com.heanbian.boot.autoconfigure.OssProperties op) {
		return new OSSClientBuilder().build(op.endpoint(), op.accessKeyId(), op.accessKeySecret());
	}
}
