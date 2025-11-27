package com.example.employee.dto;

import com.example.employee.entity.MarkPerSubject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {
    private String name;
    private String grade;
    private String subjectEnrolled;
    List<MarkResponseDto> markPerSubjects;
}
