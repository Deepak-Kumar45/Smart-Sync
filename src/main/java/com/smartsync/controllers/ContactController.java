package com.smartsync.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartsync.dto.ContactDTO;
import com.smartsync.entities.Contact;
import com.smartsync.services.ContactService;
import com.smartsync.services.UploadImageService;
import com.smartsync.utility.AlertMessage;
import com.smartsync.utility.AlertMessageType;
import com.smartsync.utility.AppConstants;
import com.smartsync.utility.LoggedInUserUtil;
import com.smartsync.utility.SmartSyncUtil;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("user/contacts")
public class ContactController {

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    private ContactService contactService;

    private UploadImageService imageService;

    public ContactController(ContactService contactService, UploadImageService imageService) {
        this.contactService = contactService;
        this.imageService = imageService;
    }

    @GetMapping("/contact-list")
    public String showContactsList(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "dir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String dir,
            
            Model model, Authentication authentication) {
        logger.info("showing list of contacts..");
        String email = LoggedInUserUtil.getLoggedInUserMail(authentication);
        Page<Contact> contactList = contactService.getContactsByUser(email, pageNumber, pageSize, sortBy, dir);
                logger.info("Total pagination pages:{}",contactList.getTotalPages());
        model.addAttribute("contacts", contactList);
        return "user/user-contacts";
    }

    @GetMapping("/favourites")
    public String favourites() {
        logger.info("showing list of favourite contacts..");
        return "user/user-favourites";
    }

    @GetMapping("/add-contact")
    public String addContacts(Model model) {
        logger.info("Displaying form to add contacts");
        ContactDTO dto = new ContactDTO();
        model.addAttribute("contactDto", dto);
        return "user/add-contacts";
    }

    @PostMapping("/adding-contact")
    public String addingContact(@Valid @ModelAttribute("contactDto") ContactDTO contactDTO, BindingResult bindingResult,
            Authentication authentication, HttpSession httpSession) {
        logger.info("Adding contact in in process");

        if (bindingResult.hasErrors()) {
            return "user/add-contacts";
        }
        String userMail = LoggedInUserUtil.getLoggedInUserMail(authentication);
        contactDTO.setUserEmail(userMail);

        String publicId = UUID.randomUUID().toString();
        String urlImage = imageService.uploadImage(contactDTO.getContactImage(), publicId);

        contactDTO.setContactImageUrl(urlImage);
        contactDTO.setPublicId(publicId);

        // save the contact
        Contact saveContact = contactService.saveContact(contactDTO);

        httpSession.setAttribute("alertObject", new AlertMessage(
                saveContact.getContactName() + " has been saved successfully", AlertMessageType.green));
        return "redirect:/user/contacts/add-contact";
    }

}
