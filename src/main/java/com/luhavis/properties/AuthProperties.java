package com.luhavis.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@PropertySource(value = "classpath:auth.yaml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "auth")
@Getter
@Setter
public class AuthProperties {

    private Map<String, String> google;
    private Map<String, String> naver;
}
