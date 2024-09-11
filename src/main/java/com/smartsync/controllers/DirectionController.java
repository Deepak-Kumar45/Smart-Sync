package com.smartsync.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smartsync.dto.SignupDTO;



@Controller
public class DirectionController {

    @GetMapping({"/","/home"})
    public String home() {
        System.out.println("Home page handler...");
        return "home";
    }
    
    @GetMapping("/about-us")
    public String aboutUs() {
        System.out.println("aboutUs page handler...");
        return "about-us";
    }

    @GetMapping("/contact-us")
    public String contactUs() {
        System.out.println("contact-us page handler...");
        return "contact-us";
    }

    @GetMapping("/error-page")
    public String errorPage() {
        System.out.println("error page handler...");
        return "error-page";
    }

    @GetMapping("/services")
    public String services() {
        System.out.println("services page handler...");
        return "services";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        System.out.println("signup page handler...");
        model.addAttribute("signupDto", new SignupDTO());
        return "sign-up";
    }
}
