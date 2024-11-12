package com.smartsync.services;

import java.util.Optional;

import com.smartsync.dto.SignupDTO;
import com.smartsync.entities.SmartUser;

public interface SmartUserService {

    SmartUser saveUser(SignupDTO dto, String verficationToken);

    Optional<SmartUser> getUserById(String userId);

    boolean isUserExists(String userId);

    SmartUser updateUser(SmartUser user,String verificationToken);

    void deleteUser(String userId);

    SmartUser getUserByMail(String email);

    SmartUser getUserByVerficationToken(String verficationToken);
}
