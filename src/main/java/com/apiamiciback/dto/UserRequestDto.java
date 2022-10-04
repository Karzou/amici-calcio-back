package com.apiamiciback.dto;


import com.apiamiciback.model.City;
import com.apiamiciback.model.Position;
import com.apiamiciback.model.Role;
import com.apiamiciback.model.TeamUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private int idUser;

    @NotBlank
    @Size(max = 100, message = "first_name max 100 characters")
    private String firstName;

    @NotBlank(message = "Must have min 1 character")
    @Size(max = 100, message = "last_name max 100 characters")
    private String lastName;

    @Email(message = "Must be a Email pattern.")
    @NotBlank
    @Size(max = 255, message = "Email max 255 characters")
    private String email;

    @NotBlank(message = "Must have min 1 character")
    @Size(min = 4, message = "Password minimum 4 characters")
    private String password;

    private Role role;

    private String description;

    @Size(max = 12, message = "Phone : Max 12 characters")
    private String phone;

    @Past(message = "Must be a date in Past")
    private Date birthdate;

    private String street;

    private String number;

    private int position;

    private int city;

    private List<TeamUser> teamUsersByIdUser = new ArrayList<>();
}
