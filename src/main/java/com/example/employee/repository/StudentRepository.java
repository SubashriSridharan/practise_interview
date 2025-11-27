package com.example.employee.repository;

import com.example.employee.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> getByRollnumber(int rollnumber);

    Optional<Student> findByRollnumber(int rollnumber);
}
