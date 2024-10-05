package com.smartsync.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartsync.dto.ContactDTO;
import com.smartsync.entities.Contact;
import com.smartsync.repositories.ContactRepository;
import com.smartsync.services.ContactService;
import com.smartsync.utility.AlertMessage;
import com.smartsync.utility.AlertMessageType;
import com.smartsync.utility.LoggedInUserUtil;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
@RequestMapping("user/contacts")
public class ContactController {

    private Logger logger=LoggerFactory.getLogger(ContactController.class);

    private ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService = contactService;    
    }

    @GetMapping("/contact-list")
    public String showContactsList() {
        logger.info("showing list of contacts..");
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
        ContactDTO dto=new ContactDTO();
        model.addAttribute("contactDto", dto);
        return "user/add-contacts";
    }

    @PostMapping("/adding-contact")
    public String addingContact(@Valid @ModelAttribute("contactDto") ContactDTO contactDTO, BindingResult bindingResult,Authentication authentication,HttpSession httpSession) {
        logger.info("Adding contact in in process");

        if(bindingResult.hasErrors()){
            return "user/add-contacts";
        }
        String userMail=LoggedInUserUtil.getLoggedInUserMail(authentication);
        contactDTO.setUserEmail(userMail);

        // save the contact
        Contact saveContact = contactService.saveContact(contactDTO);

        httpSession.setAttribute("alertObject",new AlertMessage(saveContact.getContactName()+" has been saved successfully", AlertMessageType.green));
        return "redirect:/user/contacts/add-contact";
    }
    
    
    
}
