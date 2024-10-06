package com.smartsync.dto;

import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ContactDTO {

    @NotBlank(message = "contact name is required!!")
    @Size(min = 3, message = "enter atleast 3 characters!!")
    private String contactDtoName;
    
    @NotBlank(message = "contact's email is required!!")
    @Email(message = "please provide valid email!!")
    private String contactDtoEmail;
    
    @NotBlank(message = "contact's mobile no is required!!")
    @Size(min = 10,max = 10,message = "enter a valid mobile number!!")
    private String contactDtoMobile;

    @URL
    private String linkedIn;
    
    @URL
    private String twitter;
    
    private String contactAddress;

    private Boolean isFavourite;

    private String userEmail;

    private String contactImageUrl;

    private String publicId;

    private MultipartFile contactImage;

}
