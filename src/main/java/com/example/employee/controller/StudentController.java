package com.example.employee.controller;

import com.example.employee.dto.StudentRequestDto;
import com.example.employee.dto.StudentResponseDto;
import com.example.employee.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<String> addStudents(@RequestBody List<StudentRequestDto> studentRequestDto) {

        return studentService.addStudents(studentRequestDto);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody StudentRequestDto studentRequestDto){

        return studentService.addStudent(studentRequestDto);
    }

    @GetMapping("/student")
    public ResponseEntity<StudentResponseDto> getStudent(@RequestParam("id") int rollnumber){
        return studentService.getStudent(rollnumber);
    }

    @GetMapping("/student/subject")
    public ResponseEntity<List<StudentResponseDto>> getStudentBasedOnSubject(@RequestParam String subject){
        return studentService.getStudentBasedOnSubject(subject);
    }
    @GetMapping("/student/subject/mark")
    public ResponseEntity<List<StudentResponseDto>> getStudentFilterbasedOnMarksAndSubject(@RequestParam("mark") float mark,
                                                                                           @RequestParam("subject") String subject){
        return studentService.filterOnMarks(mark,subject);
    }
    @GetMapping("/subject/marks/average")
    public ResponseEntity<Map<String,Double>> averageOfSubject(){

        return studentService.averageOfSubject();
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(@RequestParam int pagesize,@RequestParam int page){
        return studentService.getAllStudents(pagesize,page);
    }

}
