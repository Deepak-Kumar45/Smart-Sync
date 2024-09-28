package com.smartsync.services;

import java.util.Optional;

import com.smartsync.dto.SignupDTO;
import com.smartsync.entities.SmartUser;

public interface SmartUserService {

    SmartUser saveUser(SignupDTO dto);

    Optional<SmartUser> getUserById(String userId);

    boolean isUserExists(String userId);

    Optional<SmartUser> updateUser(SmartUser user);

    void deleteUser(String userId);

    SmartUser getUserByMail(String email);
}
