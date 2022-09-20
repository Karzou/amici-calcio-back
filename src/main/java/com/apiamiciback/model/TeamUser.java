package com.apiamiciback.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "team_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_team_user")
    private int id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="Test")
    private String test;

}
