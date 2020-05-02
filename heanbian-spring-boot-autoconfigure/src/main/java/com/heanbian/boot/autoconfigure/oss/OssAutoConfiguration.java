package com.heanbian.boot.autoconfigure.oss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "heanbian.oss", name = "enable", havingValue = "true")
	public OSS oss(OssProperties op) {
		return new OSSClientBuilder().build(op.getEndpoint(), op.getAccessKeyId(), op.getAccessKeySecret());
	}
}
