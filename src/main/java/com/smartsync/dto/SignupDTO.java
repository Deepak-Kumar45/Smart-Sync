package com.smartsync.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupDTO {

    @NotBlank(message = "First name should not be Empty !!")
    @Size(min = 3,message = "Enter atleast 3 characters !!")
    private String signupFirstName;

    @NotBlank(message = "Last name should not be Empty !!")
    @Size(min = 3,message = "Enter atleast 3 characters !!")
    private String signupLastName;

    @NotBlank(message = "Mail-ID should not be Empty !!")
    @Email(message = "Please put a valid mail-id !!")
    private String signupMail;

    @NotBlank(message = "Password should not be empty !!")
    @Size(min = 8,message = "Password must contains 8 charaters")
    private String signupPassword;

    @NotBlank(message = "Mobile number should not be empty !!")
    @Pattern(regexp = "[0-9]{10}",message = "Only contains value 0-9 !!")
    @Size(max = 10, min = 10,message = "Mobile number must contains 10 charaters !!")
    private String signupMobileNo;

}
