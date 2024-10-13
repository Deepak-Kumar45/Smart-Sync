package com.smartsync.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Contact> getContactsByUser(String email, Integer pageNumber, Integer pageSize, String sortBy,
            String dir) {
        SmartUser user = userRepository.findByUserMail(email).orElse(null);
        // Direction direction=dir.equals("desc").
        Sort sort = dir.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Contact> contacts = contactRepository.findBySmartUser(user, pageable);

        return contacts;
    }

    @Override
    public Page<Contact> findContactsByName(String email, String name, Integer pageNumber, Integer pageSize,
            String sortBy, String dir) {
        SmartUser smartUser = userRepository.findByUserMail(email).orElse(null);
        Sort sort = dir.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Contact> contacts = contactRepository.findBySmartUserAndContactNameContaining(smartUser, name, pageable);

        return contacts;
    }

    @Override
    public Page<Contact> findContactsByMail(String email, String mail, Integer pageNumber, Integer pageSize,
            String sortBy, String dir) {
        SmartUser smartUser = userRepository.findByUserMail(email).orElse(null);
        Sort sort = dir.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Contact> contacts = contactRepository.findBySmartUserAndContactMailContaining(smartUser, mail, pageable);
        contacts.forEach(c->System.out.println(c.getContactName()));

        return contacts;
    }

    @Override
    public Page<Contact> findContactsByPhone(String email, String phone, Integer pageNumber, Integer pageSize,
            String sortBy, String dir) {
        SmartUser smartUser = userRepository.findByUserMail(email).orElse(null);
        Sort sort = dir.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Contact> contacts = contactRepository.findBySmartUserAndContactPhoneNumberContaining(smartUser, phone, pageable);

        return contacts;
    }
}
