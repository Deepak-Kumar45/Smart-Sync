package com.smartsync.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class TestController {

    // Test controller
    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("projectName","SmartSync");
        model.addAttribute("description","A smart contcat manager for your contacts");
        model.addAttribute("gitlink", "https://github.com/Deepak-Kumar45/Smart-Sync.git");
        return "test";
    }

    // About us test controller
    @GetMapping("/about")
    public String aboutHandler(Model model) {
        System.out.println("Handling about page");
        return "about";
    }
    
    // Services test controller
    @GetMapping("/services")
    public String servicesHandler(Model model) {
        System.out.println("Handling services page");
        return "services";
    }
    
}
