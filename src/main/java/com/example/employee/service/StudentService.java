 package com.example.employee.service;

import com.example.employee.dto.StudentRequestDto;
import com.example.employee.dto.StudentResponseDto;
import com.example.employee.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

 public interface StudentService {

    ResponseEntity<String> addStudent(StudentRequestDto StudentRequestDto);
    ResponseEntity<String> addStudents(List<StudentRequestDto> StudentRequestDto);
    ResponseEntity<StudentResponseDto> getStudent(int rollNumber);
    ResponseEntity<List<StudentResponseDto>> getStudentBasedOnSubject(String subject);
    ResponseEntity<List<StudentResponseDto>> filterOnMarks(float mark, String subject);
     ResponseEntity<Map<String,Double>> averageOfSubject();
     ResponseEntity<List<StudentResponseDto>> getAllStudents(int pagesize,int page);
}
