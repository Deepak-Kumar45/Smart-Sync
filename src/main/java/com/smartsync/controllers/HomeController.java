package com.smartsync.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.val;



@Controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(Model model){
        System.out.println("Home page handler");
        model.addAttribute("Name", "Deepak Kumar");
        model.addAttribute("Mail", "deepverma@gmail.com");
        model.addAttribute("link", "www.github.com");
        // model.addAttribute("val", "false");

        return "home";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        System.out.println("Handling about page");
        return "about-us";
    }
    
    @GetMapping("/services")
    public String services(Model model) {
        System.out.println("Handling services page");
        model.addAttribute("sitename", "Smartsync");
        model.addAttribute("creationdate", "09/01/2024");
        return "services";
    }

    @GetMapping("/showAdmin")
    public String getMethodName(@RequestParam("val") String param,Model model) {
        System.out.println(param);
        model.addAttribute("val", param);
        return "home";
    }
    
    

}
