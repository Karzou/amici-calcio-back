package com.apiamiciback.dto;


import com.apiamiciback.model.Role;
import com.apiamiciback.model.TeamUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type User request dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private String role;

    private String description;

    @Size(max = 12, message = "Phone : Max 12 characters")
    private String phone;

    @Past(message = "Must be a date in Past")
    private Date birthdate;

    private String street;

    private String number;

    private int codePostal;

    private String position;

    private List<TeamUser> teamUsersByIdUser = new ArrayList<>();
}
