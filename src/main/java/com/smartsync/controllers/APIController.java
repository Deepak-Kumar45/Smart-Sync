package com.smartsync.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartsync.dto.ContactDTO;
import com.smartsync.entities.Contact;
import com.smartsync.services.ContactService;

@RestController
@RequestMapping("/api")
public class APIController {

    private ContactService contactService;

    public APIController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<ContactDTO> getContact(@PathVariable("id") String id) {
        Contact contact = contactService.getContactById(id).get();
        ContactDTO dto=new ContactDTO();
        dto.setContactAddress(contact.getAddress());
        dto.setContactDtoEmail(contact.getContactMail());
        dto.setContactDtoMobile(contact.getContactPhoneNumber());
        dto.setContactDtoName(contact.getContactName());
        dto.setContactImageUrl(contact.getContactProfilePic());
        dto.setLinkedIn(contact.getLinkedIn());
        dto.setTwitter(contact.getTwitter());
        dto.setIsFavourite(contact.isFavourite());
        return ResponseEntity.status(HttpStatus.FOUND).body(dto);
    }  

}
