package com.smartsync.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
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
public class SmartUser implements UserDetails{

    // necessary info
    @Id
    private String userId;

    @Column(nullable = false)
    private String smartUserName;

    @Column(nullable = false)
    private String userMail;
    
    private String password;

    private String phoneNumber;
    private String profilePic;
    @Column(length = 10000)
    private String description;

    // account actvation info
    @Getter(value = AccessLevel.NONE)
    private boolean enabled;
    private boolean isPhoneNumberVarified;
    private boolean isMailVarified;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Providers provider = Providers.SELF;
    private String providerId;

    // concacts of user
    @OneToMany(mappedBy = "smartUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return userMail;
    }

}
