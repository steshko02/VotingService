package com.senla.steshko.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "\"usrs\"")
public class User extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="owner", cascade = CascadeType.ALL)
    private Set<Vote> votes = new HashSet();

    @OneToMany(fetch = FetchType.LAZY,mappedBy="user", cascade = CascadeType.ALL)
    private Set<Candidate> candidates = new HashSet();

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet();

    public User() {

    }
}
