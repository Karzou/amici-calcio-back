package com.apiamiciback.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * The type New.
 */
@Entity
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_new")
    private int id;

    @NotBlank(message = "Not Blank")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Not Blank")
    @Column(name = "text")
    private String text;

    @Column(name = "display_begin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date begin;

    @Column(name = "display_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date(System.currentTimeMillis());;

    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn (name = "creator_id")
    private User creator;

}
