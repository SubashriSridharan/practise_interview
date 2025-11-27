package com.example.employee.service;

import com.example.employee.dto.EmployeeAddedResponseDto;
import com.example.employee.dto.EmployeeRequestDto;
import com.example.employee.dto.EmployeeResponseDto;
import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public EmployeeAddedResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto) {

        Employee employee = new Employee();
        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        employee.setAge(employeeRequestDto.getAge());
        employee.setSalary(employeeRequestDto.getSalary());
        employee.setGender(employeeRequestDto.getGender());
        employee.setUsername(employeeRequestDto.getFirstName().substring(0,3).concat(String.valueOf(employeeRequestDto.getAge())));
        employee.setPassword(employeeRequestDto.getFirstName().substring(0,3).
                concat(employeeRequestDto.getLastName()));
        employeeRepository.save(employee);
        EmployeeAddedResponseDto response= EmployeeAddedResponseDto.builder().message("Employee added successfully")
                .statusCode(String.valueOf(HttpStatus.OK)).build();
        return response;
    }

    @Override
    public ResponseEntity<EmployeeResponseDto> getEmployee(Long id) {

        Optional<Employee> employee= employeeRepository.findById(id);

       if(employee.isPresent()){
           Employee employeedatas =employee.get();
            EmployeeResponseDto employeeResponse = EmployeeResponseDto.builder().firstName(employeedatas.getFirstName())
                    .lastName(employeedatas.getLastName())
                    .gender(employeedatas.getGender())
                    .age(employeedatas.getAge())
                    .salary(employeedatas.getSalary())
                    .build();
           return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
        }
       else {
           return null;
       }

    }


}
