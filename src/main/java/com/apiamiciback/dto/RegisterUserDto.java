package com.apiamiciback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    private String name;
    private String fistName;
    private String mail;
    private String phone;
    private Date birthdate;

}