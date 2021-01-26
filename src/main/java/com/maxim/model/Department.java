package com.maxim.model;

import lombok.Data;

import java.util.List;

@Data
public class Department {

    private Long id;

    private String name;

    private List<Employee> employees;

    private double avgSalary;
}
