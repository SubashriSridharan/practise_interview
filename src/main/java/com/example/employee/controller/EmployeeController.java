package com.example.employee.controller;


import com.example.employee.dto.EmployeeAddedResponseDto;
import com.example.employee.dto.EmployeeRequestDto;
import com.example.employee.dto.EmployeeResponseDto;
import com.example.employee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private MathService mathService;
    @Autowired
    private ComparatorServiceImpl comparatorService;
    @Autowired
    private Demo demo;


    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeName(@PathVariable long id) {

        return  employeeService.getEmployee(id);
    }
    @PostMapping("/")
    public EmployeeAddedResponseDto saveEmployee(@RequestBody EmployeeRequestDto employee) {

        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/data")
    public String getData(){
        StringBuffer s = new StringBuffer();
    String str;
        // Adding elements in StringBuffer
        s.append("Hello");
        s.append("hi");
        str = s.toString();
        System.out.println(s.hashCode());
        System.out.println(str.hashCode());
        s.append("world");

        // String with the StringBuffer value
       // String str = s.toString();
        str = s.toString();
        System.out.println(s.hashCode());
        System.out.println(str.hashCode());
        System.out.println(s);
        System.out.println(str);
        return s.toString();
    }

    //Functional Interface -anonymous class

    @GetMapping("/add")
    public int functionalInterfaceCheck(@RequestParam("a") int a,@RequestParam("b") int b){

        MathService mathService1 =new MathService(){
            @Override
            public  int add(int a,int b){
                return a+b;
            }
        };
        return  mathService1.add(a,b);
    }

    //Functional Interface using Lmbbda

    @GetMapping("/square")
    public int addElementAndSquare(@RequestParam("a") int a,@RequestParam("b") int b){

        MathService m=(x,y)->{
            return x*y;
        };

        return (m.add(a,b)*m.add(a,b));

    }

    @GetMapping("/compare")
    public List<String> compareStringData(){

        return comparatorService.compareStringData();
    }

    @GetMapping("/comparable")
    public List<CompanyService> getCompanynameSortedByComparable(){

        return demo.getCompanyName();
    }


}
