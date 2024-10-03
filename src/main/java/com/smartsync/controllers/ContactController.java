package com.smartsync.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("user/contacts")
public class ContactController {

    @GetMapping("/contact-list")
    public String showContactsList() {
        return "user/user-contacts";
    }
    
    @GetMapping("/favourites")
    public String favourites() {
        return "user/user-favourites";
    }
    
    @GetMapping("/add-contact")
    public String addContacts() {
        return "user/add-contacts";
    }
    
    
}
