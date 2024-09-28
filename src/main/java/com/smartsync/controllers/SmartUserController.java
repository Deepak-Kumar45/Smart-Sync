package com.smartsync.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class SmartUserController {


    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }
    
    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }
    
}
