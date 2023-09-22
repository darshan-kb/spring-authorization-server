package com.spin.authorizationserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserObj {
    private String email;
    private String password;
}
