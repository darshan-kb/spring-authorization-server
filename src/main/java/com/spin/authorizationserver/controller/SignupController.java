package com.spin.authorizationserver.controller;

import com.spin.authorizationserver.model.RegistrationRequest;
import com.spin.authorizationserver.model.UserObj;
import com.spin.authorizationserver.services.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignupController {
    private RegistrationService registrationService;
    @RequestMapping(value = "/api/registration/signup", method = RequestMethod.GET)
    public String signup(Model model){
        return "signup";
    }


    @PostMapping(value = "/api/registration/signup", consumes = "application/json")
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping("/api/registration/confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

}
