package com.heanbian.boot.autoconfigure.email;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.heanbian.block.email.EmailConfig;
import com.heanbian.block.email.EmailTemplate;

@Configuration
@EnableConfigurationProperties(EmailProperties.class)
public class EmailAutoConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "heanbian.email", name = "enabled", havingValue = "true")
	public EmailTemplate emailTemplate(EmailProperties ep) {
		return new EmailTemplate(new EmailConfig(ep.getHost(), ep.getPort(), ep.getUsername(), ep.getPassword(),
				ep.getFrom(), ep.isDebug()));
	}
}
