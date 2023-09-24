package com.spin.authorizationserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountDetailsController {

    @GetMapping("/hello")
    public String TestHello(){
        return "Hello World";
    }
}
