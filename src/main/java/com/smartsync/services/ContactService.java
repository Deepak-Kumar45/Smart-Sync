package com.smartsync.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.smartsync.dto.ContactDTO;
import com.smartsync.entities.Contact;

public interface ContactService {

    public Contact saveContact(ContactDTO contactDto);
    
    public Optional<Contact> getContactById(String contactId);

    public void deleteConatctById(String id);

    public Page<Contact> getContactsByUser(String email,Integer pageNumber, Integer pageSize,String sortBy,String dir);

    public Page<Contact> findContactsByName(String email,String name,Integer pageNumber, Integer pageSize,String sortBy,String dir);

    public Page<Contact> findContactsByMail(String email,String mail,Integer pageNumber, Integer pageSize,String sortBy,String dir);
    
    public Page<Contact> findContactsByPhone(String email,String phone,Integer pageNumber, Integer pageSize,String sortBy,String dir);

}
