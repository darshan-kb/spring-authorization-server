package com.spin.authorizationserver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String clientId;
    private String secret;

    private String redirectUri;
    private String scope;
    private String authMethod;
    private String grantType;

    public Client(String clientId, String secret, String redirectUri, String scope, String authMethod, String grantType) {
        this.clientId = clientId;
        this.secret = secret;
        this.redirectUri = redirectUri;
        this.scope = scope;
        this.authMethod = authMethod;
        this.grantType = grantType;
    }
}
