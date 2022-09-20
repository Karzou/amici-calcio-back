package com.apiamiciback.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "positions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_position")
    private int id;

    @Column(name = "position_name")
    private String position;
}
