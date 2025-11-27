package com.example.employee.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeRequestDto {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private double salary;
}
