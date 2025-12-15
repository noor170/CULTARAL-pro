package com.bangla.lms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secret = "bangla-learning-default-secret-key-please-change";
    private long expiration = 86400000L;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = (secret == null || secret.isBlank()) ? this.secret : secret;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration <= 0 ? this.expiration : expiration;
    }
}
