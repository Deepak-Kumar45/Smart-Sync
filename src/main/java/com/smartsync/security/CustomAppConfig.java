package com.smartsync.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CustomAppConfig {

    @Value("${cloudinary.cloud.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.cloud.api-key}")
    private String apiKey;

    @Value("${cloudinary.cloud.api-secret}")
    private String apiSecret;


    @Bean
    public Cloudinary cloudinary(){
        Map<String,Object> map=new HashMap<>();
        map.put("cloud_name", cloudName);
        map.put("api_key", apiKey);
        map.put("api_secret", apiSecret);
        map.put("secure",true);

        return new Cloudinary(map);
    }

}
