package com.smartsync.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_smart_contact")
public class Contact {
    
    @Id
    private String concactId;

    @Column(nullable = false)
    private String contactName;
    
    @Column(nullable = false)
    private String contactMail;
    
    @Column(nullable = false)
    private String contactPhoneNumber;
    
    private String address;

    private String contactProfilePic;

    private String description;
    
    private String imagePublicId;

    private String linkedIn;
    
    private String twitter;
    
    private boolean favourite;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "contact",orphanRemoval = true)
    @Builder.Default
    private List<SocialLinks> socialLinks = new ArrayList<>();

    // info of user
    @ManyToOne
    private SmartUser smartUser;
}
