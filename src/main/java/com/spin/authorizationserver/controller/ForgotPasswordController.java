package com.spin.authorizationserver.controller;

import com.spin.authorizationserver.model.ChangePasswordRequest;
import com.spin.authorizationserver.model.ForgotPasswordRequest;
import com.spin.authorizationserver.services.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/account/forgotpassword")
public class ForgotPasswordController {
    @Autowired
    ForgotPasswordService forgotPasswordService;
    @GetMapping
    public String forgotPasswordForm(Model model){
        ForgotPasswordRequest request = new ForgotPasswordRequest();
        model.addAttribute("request",request);
        return "forgotpassword";
    }

    @PostMapping
    public String forgotPassword(@ModelAttribute ForgotPasswordRequest request, Model model){
        //System.out.println(request.getEmail());
        String message = forgotPasswordService.forgotPassword(request.getEmail());
        model.addAttribute("result",message);
        return "confirm";
    }

    @GetMapping("/changepassword")
    public String changePasswordForm(@RequestParam("token") String token, Model model) {
        ChangePasswordRequest request = new ChangePasswordRequest();
        model.addAttribute("request", request);
        model.addAttribute("token","/api/account/forgotpassword/changepassword?token="+token);
        return "changepassword";
    }

    @PostMapping("/changepassword")
    public String changePassword(@ModelAttribute ChangePasswordRequest request, @RequestParam("token") String token, Model model){
        String message = forgotPasswordService.changePassword(token, request.getPassword());
        model.addAttribute("result", message);
        return "confirm";
    }
}
