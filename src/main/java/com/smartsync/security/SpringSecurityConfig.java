package com.smartsync.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    OAuth2SuccessHandler oAuth2SuccessHandler;

    @Autowired
    SelfRegisterFailureHandler registerFailureHandler;

    // create bean AuthenticationProvider
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        return daoAuthenticationProvider;
    }

    // create bean for customize filterchain
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // disable csrf protection to customize logout page
        httpSecurity.csrf(csrf -> csrf.disable());

        // customize endpoints
        httpSecurity.authorizeHttpRequests(customizer -> {
            customizer.requestMatchers("/user/**").authenticated();
            customizer.anyRequest().permitAll();
        });

        // customize login page
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login")
                    .loginProcessingUrl("/authenticate")
                    .defaultSuccessUrl("/user/dashboard")
                    .failureUrl("/login?error=true")
                    .usernameParameter("userMail")
                    .passwordParameter("password")
                    .failureHandler(registerFailureHandler);
        });

        httpSecurity.logout(logout -> {
            logout.logoutUrl("/self-logout");
            logout.logoutSuccessUrl("/login?logout=true");
        });

        // oauth2 customization
        httpSecurity.oauth2Login(oauth2login -> {
            oauth2login.loginPage("/login");
            oauth2login.defaultSuccessUrl("/user/dashboard");
            oauth2login.successHandler(oAuth2SuccessHandler);
        });

        // customize Basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // building the DefaultSecurityFilterChain object
        return httpSecurity.build();
    }

    // create bean for password
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
