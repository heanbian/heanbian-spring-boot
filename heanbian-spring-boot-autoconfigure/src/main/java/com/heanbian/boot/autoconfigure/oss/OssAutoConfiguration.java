package com.heanbian.boot.autoconfigure.oss;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {

//	@Bean
//	@ConditionalOnProperty(prefix = "heanbian.oss", name = "enable", havingValue = "true")
//	public OssTemplate ossTemplate(OssProperties op) {
//		return new OssTemplate(op);
//	}
}
