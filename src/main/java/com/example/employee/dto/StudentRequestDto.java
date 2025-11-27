package com.example.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {
    private String name;
    private String grade;
    private String subjectEnrolled;
    private Map<String,Float> marks;
}
