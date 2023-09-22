package com.spin.authorizationserver.controller;

import com.spin.authorizationserver.model.RegistrationRequest;
import com.spin.authorizationserver.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationRestController {
    @Autowired
    RegistrationService registrationService;


    @GetMapping("/api/registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        System.out.println("In confirmation token");
        return registrationService.confirmToken(token);
    }

    @PostMapping(value = "/api/registration/emailsent", consumes = "application/json")
    public String register(@RequestBody RegistrationRequest request){
        System.out.println(request);
        return registrationService.register(request);
    }
}
