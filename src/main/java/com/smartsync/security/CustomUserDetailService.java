package com.smartsync.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartsync.entities.SmartUser;
import com.smartsync.exceptions.UserNotFoundException;
import com.smartsync.repositories.SmartUserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    SmartUserRepository userRepository;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SmartUser userDetails = userRepository.findByUserMail(username).orElseThrow(()->new UserNotFoundException("Credentionals Not found"));
        return userDetails;
    }

}
