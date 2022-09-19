package com.apiamiciback.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_user")
    private int idUser;

    @Column(name="first_name")
    private String firstName;

    @Column (name="last_name")
    private String lastName;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @ManyToOne
    @JoinColumn (name = "role_id")
    private Role role;
}
