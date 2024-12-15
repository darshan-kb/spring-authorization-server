package com.spin.authorizationserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Component
public class CORSCustomizer {
    @Value("${slotclienturi}")
    private String slotClientUri;

    @Value("${gatewayuri}")
    private String gatewayuri;
    public void corsCustomizer(HttpSecurity http) throws Exception{
        http.cors(c->{
            CorsConfigurationSource source = s ->{
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowCredentials(true);
                cc.setAllowedOrigins(List.of("http://localhost:3000",slotClientUri,gatewayuri));
                cc.setAllowedHeaders(List.of("*"));
                cc.setAllowedMethods(List.of("*"));
                return cc;
            };
            c.configurationSource(source);
        });
    }
}
