package com.maxim.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private Long id;

    private String surName;

    private String firstName;

    private String secondName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date dateOfBirth;

    private Long salary;

    private Long depId;
}
