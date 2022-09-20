package com.apiamiciback.model;


import lombok.*;

import javax.persistence.*;

/**
 * The type File data.
 */
@Entity
@Table(name = "file_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_file")
    private int id;

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_type")
    private String type;

    @Column(name = "file_path")
    private String filePath;
}
