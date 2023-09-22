package com.spin.authorizationserver.controller;

import com.spin.authorizationserver.model.UserObj;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaveController {

    @PostMapping("/save")
    public String save(@RequestBody UserObj user){
        return user.getEmail()+" Saved!!";
    }
}
