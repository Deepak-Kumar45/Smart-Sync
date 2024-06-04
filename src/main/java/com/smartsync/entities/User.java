package com.smartsync.entities;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_smartsync_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true ,nullable = false)
    private String userMail;

    @Column(nullable = false)
    private String password;
    private String phoneNo;

    @Column(length = 1000)
    private String description;

    @Column(length = 10000)
    private String profilePic;

    // Additional info
    private boolean enabled=false;
    private boolean emailVarified=false;
    private boolean phoneVarified=false;
    private boolean active=false;

    // Provider info
    private Providers provider=Providers.SELF;
    private String providerId;
}
