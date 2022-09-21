package com.apiamiciback.dto;

import com.apiamiciback.model.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class NewsRequestDto {
    private int id;

    @NotBlank(message = "Not blank")
    @Max(value = 255, message = "Titlt Max 255 characters")
    private String title;

    @Max(value = 3000, message = "Text max 3000 characters")
    private String text;


    private Date begin;

    private Date end;

    private Date createdAt;

    private Date lastUpdate;
    private User creator;
}
