package com.stayroute.backend.user;

import com.stayroute.backend.common.audit.BaseEntity;
import com.stayroute.backend.user.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name",nullable = false,length = 80)
    private String firstName;

    @Column(name = "last_name",nullable = false,length = 80)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 30)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private Role role = Role.GUEST;

    @Column(nullable = false)
    private boolean active = true;

}
