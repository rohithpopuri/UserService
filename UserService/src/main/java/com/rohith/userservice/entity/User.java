package com.rohith.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users",indexes ={@Index(name="indx_name",columnList = "name")} )
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    // constructors, getters, settersm.
}
