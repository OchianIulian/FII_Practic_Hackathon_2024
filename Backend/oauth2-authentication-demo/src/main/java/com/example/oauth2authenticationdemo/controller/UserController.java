package com.example.oauth2authenticationdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get-personal-id")
    public String getPersonalId(){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = "adasdasd";
//        if (authentication != null && authentication.isAuthenticated()) {
//            // Get the principal (authenticated user)
//            Object principal = authentication.getPrincipal();
//
//            // Print the username
//            if (principal instanceof com.example.oauth2authenticationdemo.model.User) {
//                email = ((User) principal).getEmail();
//                System.out.println("Authenticated user: " + email);
//            } else {
//                String username = principal.toString();
//                System.out.println("Authenticated user: " + username);
//            }
//        } else {
//            System.out.println("User not authenticated");
//        }

        return email;
    }
}
