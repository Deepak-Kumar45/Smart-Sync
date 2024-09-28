package com.smartsync.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.smartsync.dto.SignupDTO;


@Controller
public class DirectionController {

    private Logger log = LoggerFactory.getLogger(DirectionController.class);

    @GetMapping({ "/", "/home" })
    public String home() {
        log.info("Home page handler");
        return "home";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        log.info("About-us page handler");
        return "about-us";
    }

    @GetMapping("/contact-us")
    public String contactUs() {
        log.info("Contact-us page handler");
        return "contact-us";
    }

    @GetMapping("/error-page")
    public String errorPage() {
        log.info("Error page handler");
        return "error-page";
    }

    @GetMapping("/services")
    public String services() {
        log.info("Services page handler");
        return "services";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        log.info("SmartSync Sign-up page");
        model.addAttribute("signupDto", new SignupDTO());
        return "sign-up";
    }

    @GetMapping("/login")
    public String signIn() {
        log.info("SmartSync login page");
        return "login-page";
    }
    
}
