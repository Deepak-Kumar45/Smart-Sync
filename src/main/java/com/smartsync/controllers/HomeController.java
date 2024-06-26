package com.smartsync.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(){
        System.out.println("Home page handler..");
        return "home";
    }

    @GetMapping({"/sign-up"})
    public String showSignPage(){
        System.out.println("Showing sign-up page");
        return "sign-up-page";
    }
    
}
