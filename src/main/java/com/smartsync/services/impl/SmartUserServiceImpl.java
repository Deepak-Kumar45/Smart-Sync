package com.smartsync.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.smartsync.dto.SignupDTO;
import com.smartsync.entities.SmartUser;
import com.smartsync.repositories.SmartUserRepository;
import com.smartsync.services.SmartUserService;

@Service
public class SmartUserServiceImpl implements SmartUserService {

    private SmartUserRepository userRepository;

    public SmartUserServiceImpl(SmartUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SmartUser saveUser(SignupDTO dto) {
        String userId = UUID.randomUUID().toString();
        SmartUser queryUser = new SmartUser();
        queryUser.setUserName(dto.getSignupFirstName() + " " + dto.getSignupLastName());
        queryUser.setUserMail(dto.getSignupMail());
        queryUser.setPassword(dto.getSignupPassword());
        queryUser.setUserId(userId);
        queryUser.setPhoneNumber(dto.getSignupMobileNo().toString());
        SmartUser insertedUser = userRepository.save(queryUser);
        return insertedUser;
    }

    @Override
    public Optional<SmartUser> getUserById(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

    @Override
    public boolean isUserExists(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUserExists'");
    }

    @Override
    public Optional<SmartUser> updateUser(SmartUser user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

}