package com.smartsync.services;

import java.util.List;
import java.util.Optional;

import com.smartsync.dto.ContactDTO;
import com.smartsync.entities.Contact;

public interface ContactService {

    public Contact saveContact(ContactDTO contactDto);
    
    public Optional<Contact> getContactById(String contactId);

    public List<Contact> getContactsByUser(String email);

}
