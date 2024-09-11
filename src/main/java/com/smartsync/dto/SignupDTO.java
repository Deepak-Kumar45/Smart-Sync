package com.smartsync.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupDTO {

    private String signupFirstName;
    private String signupLastName;
    private String signupMail;
    private String signupPassword;
    private Long signupMobileNo;

}
