package com.smartsync.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartsync.entities.Contact;
import com.smartsync.entities.SmartUser;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {

    // Page will give you a List of contacts based on your defained parameters in Pageable
    Page<Contact> findBySmartUser(SmartUser smartUser, Pageable page);

    Page<Contact> findBySmartUserAndContactNameContaining(SmartUser user, String name, Pageable pageable);

    Page<Contact> findBySmartUserAndContactMailContaining(SmartUser user, String mail, Pageable pageable);

    Page<Contact> findBySmartUserAndContactPhoneNumberContaining(SmartUser user, String phone, Pageable pageable);


}
