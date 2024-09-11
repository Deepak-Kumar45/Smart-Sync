package com.smartsync.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smartsync.dto.SignupDTO;
import com.smartsync.entities.SmartUser;
import com.smartsync.services.SmartUserService;

@Controller
public class OperationalController {

    private SmartUserService userService;

    public OperationalController(SmartUserService userService){
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String handlingSignUp(@ModelAttribute("signupDto") SignupDTO signupDTO) {
        System.out.println("handling sign-up form");
        System.out.println(signupDTO);
        SmartUser user=userService.saveUser(signupDTO);
        System.err.println("Registration has been done user Id: "+user.getUserId());
        return "redirect:/sign-up";
    }
    

}
