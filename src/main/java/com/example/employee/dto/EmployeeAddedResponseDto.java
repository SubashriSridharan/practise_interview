package com.example.employee.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeAddedResponseDto {
     private String message;
     private String statusCode;
}
