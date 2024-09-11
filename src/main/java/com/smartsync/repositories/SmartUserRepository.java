package com.smartsync.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartsync.entities.SmartUser;

@Repository
public interface SmartUserRepository extends JpaRepository<SmartUser,String>{

}
