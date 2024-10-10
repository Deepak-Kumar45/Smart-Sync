package com.smartsync.utility;

import java.util.*;
import org.springframework.stereotype.Component;

import com.smartsync.entities.Contact;

@Component
public class SmartSyncUtil {

    public static List<Contact> getDummyContacts() {

        List<Contact> contacts = new ArrayList<>();
        contacts.add(Contact.builder().contactName("Bonnie Green").contactMail("bonnie@green.com").address("london")
                .contactPhoneNumber("7987123405")
                .contactProfilePic("https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/bonnie-green.png")
                .linkedIn("http://www.google.com").twitter("http://www.twitter.com").build());

        contacts.add(Contact.builder().contactName("Helene Engels").contactMail("helene@engels.com").address("boston")
                .contactPhoneNumber("1236547891")
                .contactProfilePic("https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/helene-engels.png")
                .linkedIn("http://www.google.com").twitter("http://www.twitter.com").build());

        contacts.add(Contact.builder().contactName("Jese Leos").contactMail("Jese@Leos.com").address("gorgia")
                .contactPhoneNumber("7852146985")
                .contactProfilePic("https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/jese-leos.png")
                .linkedIn("http://www.google.com").twitter("http://www.twitter.com").build());

        contacts.add(Contact.builder().contactName("Joseph Mcfall").contactMail("Joseph@Mcfall.com").address("arkensas")
                .contactPhoneNumber("7987123405")
                .contactProfilePic("https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/joseph-mcfall.png")
                .linkedIn("http://www.google.com").twitter("http://www.twitter.com").build());

        contacts.add(Contact.builder().contactName("Lana Byrd").contactMail("Lana@Byrd.com").address("alabama")
                .contactPhoneNumber("9987452163")
                .contactProfilePic("https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/sofia-mcguire.png")
                .linkedIn("http://www.google.com").twitter("http://www.twitter.com").build());

        contacts.add(Contact.builder().contactName("Leslie Livingston").contactMail("Leslie@Livingston.com")
                .address("oregon")
                .contactPhoneNumber("7987123405")
                .contactProfilePic("https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/thomas-lean.png")
                .linkedIn("http://www.google.com").twitter("http://www.twitter.com").build());

        contacts.add(Contact.builder().contactName("Michael Gough").contactMail("Michael@Gough.com")
                .address("california")
                .contactPhoneNumber("774851254698")
                .contactProfilePic("https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/michael-gouch.png")
                .linkedIn("http://www.google.com").twitter("http://www.twitter.com").build());

        contacts.add(Contact.builder().contactName("Neil Sims").contactMail("Neil@Sims.com").address("washington")
                .contactPhoneNumber("7987123405")
                .contactProfilePic(
                        "https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/neil-sims.png")
                .linkedIn("http://www.google.com").twitter("http://www.twitter.com").build());

        return contacts;
    }

}
