package com.smartsync.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

@Component
public class LoggedInUserUtil {

    public static String getLoggedInUserMail(Authentication authentication) {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String loginBy = token.getAuthorizedClientRegistrationId();
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal();
        String email = null;
        if (loginBy.equalsIgnoreCase("google")) {
            email = oAuth2User.getAttribute("email").toString();
        } else if (loginBy.equalsIgnoreCase("github")) {
            email = oAuth2User.getAttribute("email") == null
                    ? oAuth2User.getAttribute("login").toString().toLowerCase() + "@gmail.com"
                    : oAuth2User.getAttribute("email");
        } else {
            email = oAuth2User.getName();
        }
        return email;
    }
}
