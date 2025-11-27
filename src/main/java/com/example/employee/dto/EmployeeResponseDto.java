package com.example.employee.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class EmployeeResponseDto {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private double salary;
}
