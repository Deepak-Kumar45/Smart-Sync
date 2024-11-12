package com.smartsync.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartsync.dto.SignupDTO;
import com.smartsync.entities.SmartUser;
import com.smartsync.services.EmailService;
import com.smartsync.services.SmartUserService;
import com.smartsync.utility.AlertMessage;
import com.smartsync.utility.AlertMessageType;
import com.smartsync.utility.AppConstants;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class OperationalController {

    private SmartUserService userService;

    private EmailService emailService;

    public OperationalController(SmartUserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/registration")
    public String handlingSignUp(@Valid @ModelAttribute("signupDto") SignupDTO signupDTO, BindingResult result,
            HttpSession httpSession) {
        System.out.println("handling sign-up form");
        if (result.hasErrors()) {
            return "sign-up";
        }
        String verficationToken = UUID.randomUUID().toString();
        String verificationLink = AppConstants.APPDOMAIN + "account/verify?verification-token=" + verficationToken;

        String html="<!DOCTYPE html>\r\n" + //
						"<html lang=\"en\">\r\n" + //
						"<head>\r\n" + //
						"    <meta charset=\"UTF-8\">\r\n" + //
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
						"    <title>Email Verification</title>\r\n" + //
						"    <style>\r\n" + //
						"        body {\r\n" + //
						"            font-family: Arial, sans-serif;\r\n" + //
						"            margin: 0;\r\n" + //
						"            padding: 0;\r\n" + //
						"            background-color: #f4f4f4;\r\n" + //
						"        }\r\n" + //
						"        .container {\r\n" + //
						"            max-width: 600px;\r\n" + //
						"            margin: 20px auto;\r\n" + //
						"            background: #ffffff;\r\n" + //
						"            padding: 20px;\r\n" + //
						"            border-radius: 8px;\r\n" + //
						"            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\r\n" + //
						"        }\r\n" + //
						"        h1 {\r\n" + //
						"            color: #333;\r\n" + //
						"        }\r\n" + //
						"        p {\r\n" + //
						"            line-height: 1.5;\r\n" + //
						"            color: #555;\r\n" + //
						"        }\r\n" + //
						"        .button {\r\n" + //
						"            display: inline-block;\r\n" + //
						"            padding: 10px 20px;\r\n" + //
						"            margin: 20px 0;\r\n" + //
						"            color: #ffffff;\r\n" + //
						"            background-color: #007BFF;\r\n" + //
						"            text-decoration: none;\r\n" + //
						"            border-radius: 5px;\r\n" + //
						"        }\r\n" + //
						"        .footer {\r\n" + //
						"            margin-top: 20px;\r\n" + //
						"            font-size: 12px;\r\n" + //
						"            color: #aaa;\r\n" + //
						"        }\r\n" + //
						"    </style>\r\n" + //
						"</head>\r\n" + //
						"<body>\r\n" + //
						"    <div class=\"container\">\r\n" + //
						"        <h1>Email Verification</h1>\r\n" + //
						"        <p>Hi there!</p>\r\n" + //
						"        <p>Thank you for signing up. To complete your registration, please verify your email address by clicking the button below:</p>\r\n" + //
						"        <a href=\""+verificationLink+"\" class=\"button\">Verify Email</a>\r\n" + //
						"        <p>If you did not create an account, please ignore this email.</p>\r\n" + //
						"        <p>Thank you!</p>\r\n" + //
						"        <div class=\"footer\">\r\n" + //
						"            <p>&copy;SmartSync Your Company. All rights reserved.</p>\r\n" + //
						"        </div>\r\n" + //
						"    </div>\r\n" + //
						"</body>\r\n" + //
						"</html>\r\n" + //
						"";
        emailService.sendMailWithHtml(signupDTO.getSignupMail(), "SmartSync verification", html);
        userService.saveUser(signupDTO, verficationToken);
        httpSession.setAttribute("alertObject", new AlertMessage(
                "Registration successfull!! Check your mail to activate your account", AlertMessageType.green));
        return "redirect:/sign-up";
    }

    @GetMapping("account/verify")
    public String verifyAccount(@RequestParam("verification-token") String verficationToken,HttpSession httpSession){
        SmartUser user=userService.getUserByVerficationToken(verficationToken);
        if(user!=null){
            user.setEnabled(true);
            user.setMailVarified(true);
            userService.updateUser(user,verficationToken);
            httpSession.setAttribute("alertObject", new AlertMessage("Verified account. Try login..", AlertMessageType.blue));
            return "redirect:/login";
        }else{
            httpSession.setAttribute("alertObject", new AlertMessage("Email-id not found. Register now.", AlertMessageType.red));
            return "redirect:/login";
        }
    }


}
