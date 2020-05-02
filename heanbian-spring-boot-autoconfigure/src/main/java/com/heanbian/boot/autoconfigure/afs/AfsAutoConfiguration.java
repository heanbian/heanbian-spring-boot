package com.heanbian.boot.autoconfigure.afs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

@Configuration
@EnableConfigurationProperties(AfsProperties.class)
public class AfsAutoConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "heanbian.afs", name = "enable", havingValue = "true")
	public IAcsClient acsClient(AfsProperties ap) {
		DefaultProfile.addEndpoint(ap.getRegionId(), "afs", ap.getEndpoint());
		IClientProfile profile = DefaultProfile.getProfile(ap.getRegionId(), ap.getAccessKeyId(),
				ap.getAccessKeySecret());
		return new DefaultAcsClient(profile);
	}
}
