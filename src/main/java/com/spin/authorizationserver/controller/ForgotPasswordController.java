package com.spin.authorizationserver.controller;

import com.spin.authorizationserver.model.ForgotPasswordRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/account/forgotpassword")
public class ForgotPasswordController {

    @GetMapping
    public String forgotPasswordForm(Model model){
        ForgotPasswordRequest request = new ForgotPasswordRequest();
        model.addAttribute("request",request);
        return "forgotpassword";
    }

    @PostMapping
    public void forgotPassword(Model m){
        System.out.println(m.getAttribute("email"));
        //return "confirm";
    }
}
