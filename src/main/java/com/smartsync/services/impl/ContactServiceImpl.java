package com.smartsync.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.smartsync.dto.ContactDTO;
import com.smartsync.entities.Contact;
import com.smartsync.entities.SmartUser;
import com.smartsync.repositories.ContactRepository;
import com.smartsync.repositories.SmartUserRepository;
import com.smartsync.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    private SmartUserRepository userRepository;

    public ContactServiceImpl(ContactRepository contactRepository, SmartUserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Contact saveContact(ContactDTO contactDTO) {

        SmartUser user = userRepository.findByUserMail(contactDTO.getUserEmail()).orElse(null);

        Contact contact = new Contact();
        contact.setAddress(contactDTO.getContactAddress());
        contact.setConcactId(UUID.randomUUID().toString());
        contact.setContactMail(contactDTO.getContactDtoEmail());
        contact.setContactName(contactDTO.getContactDtoName());
        contact.setContactPhoneNumber(contactDTO.getContactDtoMobile());
        contact.setFavourite(contactDTO.getIsFavourite());
        contact.setLinkedIn(contactDTO.getLinkedIn());
        contact.setSmartUser(user);
        contact.setTwitter(contactDTO.getTwitter());
        contact.setImagePublicId(contactDTO.getPublicId());
        contact.setContactProfilePic(contactDTO.getContactImageUrl());

        Contact savedContact = contactRepository.save(contact);
        return savedContact;
    }

    @Override
    public Optional<Contact> getContactById(String contactId) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<Contact> getContactsByUser(String email) {
        SmartUser user = userRepository.findByUserMail(email).orElse(null);

        List<Contact> contacts = contactRepository.findBySmartUser(user);

        return contacts;
    }
}
