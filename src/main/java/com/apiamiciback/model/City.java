package com.apiamiciback.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_city")
    private int id;

    @Column(name ="code_postal")
    private int codePostal;

    @Column(name="city")
    private String city;

}
