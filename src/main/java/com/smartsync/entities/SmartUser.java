package com.smartsync.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "tbl_smart_user")
public class SmartUser {

    // necessary info
    @Id
    private String userId;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userMail;
    @Column(nullable = false)
    private String password;

    private String phoneNumber;
    private String profilePic;
    @Column(length = 10000)
    private String description;

    // account actvation info
    private boolean enabled;
    private boolean isPhoneNumberVarified;
    private boolean isMailVarified;

    @Enumerated
    private Providers provider = Providers.SELF;
    private String providerId;

    // concacts of user
    @OneToMany(mappedBy = "smartUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

}
