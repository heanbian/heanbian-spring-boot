package com.heanbian.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("heanbian.elasticsearch")
public record ElasticsearchProperties(@DefaultValue("false") boolean enabled, String clusterNodes) {

}