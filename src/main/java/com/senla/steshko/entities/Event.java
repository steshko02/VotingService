package com.senla.steshko.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Event extends BaseEntity{

    @Column(name = "start")
    private Date start;

    @Column(name = "finish")
    private Date finish;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="event", cascade = {CascadeType.ALL})
    private Set<Candidate> candidates = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy="event", cascade = {CascadeType.ALL})
    private Set<Vote> votes = new HashSet<>();

}
