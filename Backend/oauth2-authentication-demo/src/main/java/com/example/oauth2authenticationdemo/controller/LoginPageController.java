package com.example.oauth2authenticationdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
}
