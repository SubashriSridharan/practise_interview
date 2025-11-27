package com.example.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="subjectMarks")
public class MarkPerSubject {

    @Id
    @GeneratedValue
    private int subjectId;
    private String subject;
    private float mark;
    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;
}
