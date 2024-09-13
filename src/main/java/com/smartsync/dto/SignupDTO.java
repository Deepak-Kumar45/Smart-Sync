package com.smartsync.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 3, message = "First name should be greater than 3 characters")
    private String signupFirstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, message = "Last name should be greater than 3 characters")
    private String signupLastName;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    private String signupMail;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should be greater than 8 characters")
    private String signupPassword;

    // @NotNumber(message = "Mobile number is not valid")
    // @Size(min = 10, max = 10, message = "Mobile number should be 10 digits")
    @NotNull(message = "Mobile number is required")
    private Long signupMobileNo;

}
