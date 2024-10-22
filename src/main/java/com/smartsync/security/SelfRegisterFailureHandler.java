package com.smartsync.security;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.smartsync.utility.AlertMessage;
import com.smartsync.utility.AlertMessageType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SelfRegisterFailureHandler implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof DisabledException){
            System.err.println("Account is deactivated");
            HttpSession session=request.getSession(true);
            session.setAttribute("alertObject", new AlertMessage(
                "Account is deactivated!! Check your mail to activate your account", AlertMessageType.yellow));
            response.sendRedirect("/login");
        }else{
            response.sendRedirect("/login?error=true");
        }
    }

}
