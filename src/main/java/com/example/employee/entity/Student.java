package com.example.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue
    private int rollnumber;
    private String name;
    private String Grade;
    private String subjectsEnrolled;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<MarkPerSubject> marksPerSubject;
}
