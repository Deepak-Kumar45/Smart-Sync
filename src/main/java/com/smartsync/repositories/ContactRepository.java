package com.smartsync.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartsync.entities.Contact;
import com.smartsync.entities.SmartUser;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {

    List<Contact> findBySmartUser(SmartUser smartUser);

}
