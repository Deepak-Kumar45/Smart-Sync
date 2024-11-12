package com.smartsync.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartsync.dto.SignupDTO;
import com.smartsync.entities.SmartUser;
import com.smartsync.repositories.SmartUserRepository;
import com.smartsync.services.SmartUserService;
import com.smartsync.utility.AppConstants;

@Service
public class SmartUserServiceImpl implements SmartUserService {

    private SmartUserRepository userRepository;

    private PasswordEncoder encoder;

    public SmartUserServiceImpl(SmartUserRepository userRepository,PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public SmartUser saveUser(SignupDTO dto, String verficationToken) {
        String userId = UUID.randomUUID().toString();
        SmartUser queryUser = new SmartUser();
        queryUser.setSmartUserName(dto.getSignupFirstName() + " " + dto.getSignupLastName());
        queryUser.setUserMail(dto.getSignupMail());
        queryUser.setPassword(encoder.encode(dto.getSignupPassword()));
        queryUser.setRoles(List.of(AppConstants.ROLE_USER));
        queryUser.setUserId(userId);
        queryUser.setEnabled(false);
        queryUser.setPhoneNumber(dto.getSignupMobileNo());
        queryUser.setVerificationToken(verficationToken);

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
    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public SmartUser getUserByMail(String email) {
        SmartUser user = userRepository.findByUserMail(email).orElse(null);
        return user;
    }

    @Override
    public SmartUser getUserByVerficationToken(String verficationToken) {
        SmartUser user = userRepository.findByVerificationToken(verficationToken).orElse(null);
        return user;
    }

    @Override
    public SmartUser updateUser(SmartUser user, String verificationToken) {
        SmartUser oldUser=getUserByVerficationToken(verificationToken);
        oldUser.setEnabled(user.isEnabled());
        oldUser.setMailVarified(user.isMailVarified());
        SmartUser updatedUser=userRepository.save(oldUser);
        return updatedUser;
    }

}
