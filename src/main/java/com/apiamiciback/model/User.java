package com.apiamiciback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

/**
 * The type User.
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_user")
    private int idUser;

    @NotBlank
    @Size(max = 100, message = "first_name max 100 characters")
    @Column(name="first_name")
    private String firstName;

    @NotBlank(message = "Must have min 1 character")
    @Size(max = 100, message = "last_name max 100 characters")
    @Column (name="last_name")
    private String lastName;

    @Email(message = "Must be a Email pattern.")
    @NotBlank
    @Size(max = 255, message = "Email max 255 characters")
    @Column (name = "email")
    private String email;

    @JsonIgnore
    @NotBlank(message = "Must have min 1 character")
    @Size(min = 4, message = "Password minimum 4 characters")
    @Column (name = "password")
    private String password;

    @ManyToOne
    @JoinColumn (name = "role_id")
    private Role role;

    @Column(name = "description")
    private String description;

    @Size(max = 12, message = "Phone : Max 12 characters")
    @Column(name = "phone")
    private String phone;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "street")
    private String street;

    @Column(name = "number_address")
    private String number;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date(System.currentTimeMillis());

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
    public void setPosition(Position position){
        this.position = position;
    }

    public String adresseToString(){
        return getStreet() + " " + getNumber() + " " + getCity().getCodePostal() + " " + getCity().getCity();
    }

    public String positionToString(){
        return getPosition().getPosition();
    }

}




