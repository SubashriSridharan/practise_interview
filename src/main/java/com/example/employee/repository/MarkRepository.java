package com.example.employee.repository;

import com.example.employee.entity.MarkPerSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<MarkPerSubject, Integer> {
    List<MarkPerSubject> findByStudentRollnumber(int rollNmber);

    List<MarkPerSubject> findBySubject(String subject);
}
