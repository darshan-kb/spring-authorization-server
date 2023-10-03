package com.spin.authorizationserver.model;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String password;
    private String token;
}
