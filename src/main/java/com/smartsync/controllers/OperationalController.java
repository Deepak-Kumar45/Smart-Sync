package com.smartsync.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smartsync.dto.SignupDTO;
import com.smartsync.entities.SmartUser;
import com.smartsync.services.SmartUserService;
import com.smartsync.utility.AlertMessage;
import com.smartsync.utility.AlertMessageType;

import jakarta.servlet.http.HttpSession;

@Controller
public class OperationalController {

    private SmartUserService userService;

    public OperationalController(SmartUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String handlingSignUp(@ModelAttribute("signupDto") SignupDTO signupDTO, HttpSession httpSession) {
        System.out.println("handling sign-up form");
        System.out.println(signupDTO);
        SmartUser user = userService.saveUser(signupDTO);
        httpSession.setAttribute("alertObject", new AlertMessage(
                "Registration successfull!! Check your mail to activate your account", AlertMessageType.green));
        return "redirect:/sign-up";
    }

}
