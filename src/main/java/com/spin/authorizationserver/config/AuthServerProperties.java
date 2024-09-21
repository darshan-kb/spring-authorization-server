package com.spin.authorizationserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public class AuthServerProperties {
    @ConfigurationProperties("auth")
    @Component
    @Data
    static class AuthProperties{
        private String publicKey;
        private String privateKey;
        private String keyId;
    }
}
