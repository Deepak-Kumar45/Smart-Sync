package com.smartsync.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.smartsync.entities.SmartUser;
import com.smartsync.services.SmartUserService;
import com.smartsync.utility.LoggedInUserUtil;


@ControllerAdvice
public class SessionController {


    @Autowired
    SmartUserService userService;

    @ModelAttribute
    public void setUserInSession(Authentication authentication,Model model){
        if(authentication==null){
            return;
        }
        String email=LoggedInUserUtil.getLoggedInUserMail(authentication);
            SmartUser user = userService.getUserByMail(email);
        if(user==null){
            model.addAttribute("authorizedUser", null);
        }else{
            model.addAttribute("authorizedUser", user);
        }
    }
}
