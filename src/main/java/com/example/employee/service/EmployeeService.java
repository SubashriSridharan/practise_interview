package com.example.employee.service;

import com.example.employee.dto.EmployeeAddedResponseDto;
import com.example.employee.dto.EmployeeRequestDto;
import com.example.employee.dto.EmployeeResponseDto;
import org.springframework.http.ResponseEntity;


public interface EmployeeService {
    public ResponseEntity<EmployeeResponseDto> getEmployee(Long id);
    public EmployeeAddedResponseDto saveEmployee(EmployeeRequestDto employee);

}
