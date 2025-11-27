package com.example.employee.exception;

import com.example.employee.util.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

   @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ResponseError> handleStudentNotFoundException(StudentNotFoundException ex) {
        ResponseError error=ResponseError.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
