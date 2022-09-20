package com.apiamiciback.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

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

    @Column(name = "description")
    private String description;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "street")
    private String street;

    @Column(name = "number_address")
    private String number;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn (name = "position_id")
    private Position position;

    @ManyToOne
    @JoinColumn (name = "city_id")
    private City city;

    @OneToMany(mappedBy = "user")
    private List<TeamUser> teamUsersByIdUser = new ArrayList<>();

}




