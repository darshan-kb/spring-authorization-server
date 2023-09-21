package com.spin.authorizationserver.controller;

import com.spin.authorizationserver.model.UserObj;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignupController {
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model){
        return "signup";
    }

}
