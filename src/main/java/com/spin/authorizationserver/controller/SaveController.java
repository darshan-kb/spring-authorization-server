package com.spin.authorizationserver.controller;

import com.spin.authorizationserver.model.RegistrationRequest;
import com.spin.authorizationserver.model.UserObj;
import com.spin.authorizationserver.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SaveController {
    @Autowired
    RegistrationService registrationService;

    @GetMapping("/api/registration")
    public String getapi(Model model){
        RegistrationRequest request = new RegistrationRequest();
        model.addAttribute("request",request);
        return "testpost";
    }

    @PostMapping("/api/registration")
    public String register(@ModelAttribute("request") RegistrationRequest request, Model model){
        System.out.println(request);
        String message = registrationService.register(request);
        model.addAttribute("result",message);
        return "emailcheck";
    }

    @GetMapping("/api/registration/confirm")
    public String confirm(@RequestParam("token") String token, Model model) {
        String message = registrationService.confirmToken(token);
        model.addAttribute("result",message);
        return "confirm";
    }

}
