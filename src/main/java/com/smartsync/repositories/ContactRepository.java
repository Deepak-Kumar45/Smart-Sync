package com.smartsync.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartsync.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {

}
