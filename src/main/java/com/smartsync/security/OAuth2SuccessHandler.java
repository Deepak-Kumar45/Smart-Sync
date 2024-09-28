package com.smartsync.security;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.smartsync.entities.Providers;
import com.smartsync.entities.SmartUser;
import com.smartsync.repositories.SmartUserRepository;
import com.smartsync.services.SmartUserService;
import com.smartsync.utility.AppConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private Logger log = LoggerFactory.getLogger(OAuth2SuccessHandler.class);

    @Autowired
    private SmartUserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // identify the type of oauth2 login
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String provider = token.getAuthorizedClientRegistrationId();
        log.info("Authentication Success: {}", provider);

        DefaultOAuth2User authUser = (DefaultOAuth2User) token.getPrincipal();

        authUser.getAttributes().forEach((k, v) -> log.info(k + " : " + v));

        // create new user
        SmartUser smartUser = new SmartUser();
        smartUser.setUserId(UUID.randomUUID().toString());
        smartUser.setRoles(List.of(AppConstants.ROLE_USER));

        if (provider.equalsIgnoreCase("google")) {
            smartUser.setProvider(Providers.GOOGLE);
            smartUser.setProviderId(authUser.getAttribute("sub").toString());
            smartUser.setUserName(authUser.getAttribute("name").toString());
            smartUser.setUserMail(authUser.getAttribute("email").toString());
            smartUser.setProfilePic(authUser.getAttribute("picture").toString());
            smartUser.setMailVarified(authUser.getAttribute("email_verified"));
            smartUser.setEnabled(true);
        } else if (provider.equalsIgnoreCase("github")) {
            String email=authUser.getAttribute("email")==null? authUser.getAttribute("login").toString().toLowerCase()+"@gmail.com":authUser.getAttribute("email");
            smartUser.setUserMail(email);
            smartUser.setProviderId(authUser.getAttribute("id").toString());
            smartUser.setProfilePic(authUser.getAttribute("avatar_url"));
            smartUser.setUserName(authUser.getAttribute("name"));
            smartUser.setDescription(authUser.getAttribute("bio"));
            smartUser.setEnabled(true);
            smartUser.setProvider(Providers.GITHUB);
        }

        // save the user
        SmartUser userByMail = userRepository.findByUserMail(smartUser.getUserMail()).orElse(null);
        if(userByMail==null){
            userRepository.save(smartUser);
        }

        // redirect to dashboard
        response.sendRedirect("/user/dashboard");
    }

}
