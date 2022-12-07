package com.heanbian.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("heanbian.oss")
public record OssProperties(@DefaultValue("false") boolean enabled, //
		String endpoint, //
		String accessKeyId, //
		String accessKeySecret, //
		String bucketName, //
		String bucketUri) {

}
