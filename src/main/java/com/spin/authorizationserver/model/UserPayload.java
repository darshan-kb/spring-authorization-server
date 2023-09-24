package com.spin.authorizationserver.model;

import com.spin.authorizationserver.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserPayload {
    private String username;
    private String email;
    private String role;

    public UserPayload(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getAuthority();
    }
}
