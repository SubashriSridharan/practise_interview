package com.example.employee.service;

import com.example.employee.dto.MarkResponseDto;
import com.example.employee.dto.StudentRequestDto;
import com.example.employee.dto.StudentResponseDto;
import com.example.employee.entity.MarkPerSubject;
import com.example.employee.entity.Student;
import com.example.employee.exception.StudentNotFoundException;
import com.example.employee.repository.MarkRepository;
import com.example.employee.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private MarkRepository markRepository;

    @Override
    @Transactional
    public ResponseEntity<String> addStudent(StudentRequestDto studentRequestDto) {

       studentRepository.save(addStudentUtil(studentRequestDto));
        return new ResponseEntity<>("Student Added", HttpStatus.OK);
    }

    public ResponseEntity<String> addStudents(List<StudentRequestDto> studentRequestDto) {
        
        studentRepository.saveAll(studentRequestDto.stream()
                .map(student-> addStudentUtil(student)).collect(Collectors.toList()));
        return new ResponseEntity<>("Student Added", HttpStatus.OK);
    }

    public ResponseEntity<StudentResponseDto> getStudent(int rollNmber) {

       Student student=studentRepository.findByRollnumber(rollNmber)
                .orElseThrow(()->new StudentNotFoundException("Student Information Not Found"));
        List<MarkPerSubject> marks=markRepository.findByStudentRollnumber(rollNmber);
        List<MarkResponseDto> markResponse= marks.stream().map(mark->{
            MarkResponseDto markResponseDto =new MarkResponseDto();
            markResponseDto.setMark(mark.getMark());
            markResponseDto.setSubject(mark.getSubject());
             return markResponseDto;
        }).collect(Collectors.toList());

        StudentResponseDto studentResponseDto=StudentResponseDto.builder()
                .subjectEnrolled(student.getSubjectsEnrolled())
                .grade(student.getGrade()).name(student.getName())
                .markPerSubjects(markResponse).build();
        return new ResponseEntity<StudentResponseDto>(studentResponseDto,HttpStatus.OK);
    }

    public ResponseEntity<List<StudentResponseDto>> getStudentBasedOnSubject(String subject) {

        //List<StudentResponseDto> responseDtos=new ArrayList<>();
        List<StudentResponseDto> data=markRepository.findBySubject(subject).stream().map(subjects->{
            int rollNumber=subjects.getStudent().getRollnumber();
            Student student=studentRepository.findByRollnumber(rollNumber)
                    .orElseThrow(()-> new StudentNotFoundException("Student Not Found"));
            MarkResponseDto markResponseDto =new MarkResponseDto();
            markResponseDto.setMark(subjects.getMark());
            markResponseDto.setSubject(subjects.getSubject());
            StudentResponseDto studentResponseDto=StudentResponseDto.builder().name(student.getName()).subjectEnrolled(subjects.getSubject())
                    .grade(subjects.getStudent().getGrade()).markPerSubjects(List.of(markResponseDto)).build();
            //responseDtos.add(studentResponseDto);
            return studentResponseDto;
        }).collect(Collectors.toList());
        return new ResponseEntity<List<StudentResponseDto>>(data,HttpStatus.OK);
    }

    public ResponseEntity<List<StudentResponseDto>> filterOnMarks(float mark, String subject){

        List<StudentResponseDto> responseData= markRepository.findAll().stream()
                .filter(result->result.getMark()>=mark && result.getSubject().equalsIgnoreCase(subject)).
       map(marks-> {
                Student student=studentRepository.findByRollnumber(marks.getStudent().getRollnumber())
                        .orElseThrow(()-> new StudentNotFoundException("Student Not Found"));
                MarkResponseDto markResponseDto =new MarkResponseDto();
                markResponseDto.setMark(marks.getMark());
                markResponseDto.setSubject(marks.getSubject());
                StudentResponseDto studentResponseDto=StudentResponseDto.builder().name(student.getName()).subjectEnrolled(marks.getSubject())
                        .grade(marks.getStudent().getGrade()).markPerSubjects(List.of(markResponseDto)).build();
            return studentResponseDto;
    }).collect(Collectors.toList());

        return new ResponseEntity<List<StudentResponseDto>>(responseData,HttpStatus.OK);
    }

    public ResponseEntity<Map<String,Double>> averageOfSubject(){

        Map<String,Double> average=markRepository.findAll().stream()
                .collect(Collectors.groupingBy(MarkPerSubject::getSubject,Collectors.averagingDouble(MarkPerSubject::getMark)));


        return new ResponseEntity<Map<String,Double>>(average,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(int pagesize,int page) {
        Sort sort= Sort.by(Sort.Direction.DESC,"name");
        Pageable pageable= PageRequest.of(page,pagesize,sort);
        List<StudentResponseDto> students=studentRepository.findAll(pageable).stream().map(student->{
            List<MarkResponseDto> mark=markRepository.findByStudentRollnumber(student.getRollnumber())
                    .stream().map(marks->{
                        MarkResponseDto markResponseDto=MarkResponseDto.builder()
                                .mark(marks.getMark()).subject(marks.getSubject()).build();
                        return markResponseDto;
                    }).collect(Collectors.toList());

            StudentResponseDto studentResponseDto=new StudentResponseDto();
            studentResponseDto.setName(student.getName());
            studentResponseDto.setGrade(student.getGrade());
            studentResponseDto.setSubjectEnrolled(student.getSubjectsEnrolled());
            studentResponseDto.setMarkPerSubjects(mark);

            return studentResponseDto;
        }).collect(Collectors.toList());
        return new ResponseEntity<List<StudentResponseDto>>(students,HttpStatus.OK);
    }

    Student addStudentUtil(StudentRequestDto studentRequestDto) {
        Student student=new Student();
        student.setName(studentRequestDto.getName());
        student.setGrade(studentRequestDto.getGrade());
        student.setSubjectsEnrolled(studentRequestDto.getSubjectEnrolled());

        List<MarkPerSubject> markPerSubjectList= studentRequestDto.getMarks().entrySet().stream().map(subject->{
            MarkPerSubject markPerSubject=new MarkPerSubject();
            markPerSubject.setSubject(subject.getKey());
            markPerSubject.setMark(subject.getValue());
            markPerSubject.setStudent(student);
            return markPerSubject;
        }).collect(Collectors.toList());

        student.setMarksPerSubject(markPerSubjectList);
        return student;
    }

}
