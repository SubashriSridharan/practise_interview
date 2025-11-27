package com.example.employee.controller;

import com.example.employee.service.WeatherServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class WeatherController {
    @Autowired
    private WeatherServiceImpl weatherService;


    @GetMapping("/weatherinfo")
    @CircuitBreaker(name ="weatherCircuitBreaker" , fallbackMethod = "weatherFallbackMethod")
    public String getWeatherInfo(@RequestParam("city") String city) {

        return weatherService.getWeatherInformation(city);

    }

    public String weatherFallbackMethod(Throwable throwable) {

        return throwable.getMessage();
    }


    public void calculateMaxValue(){
        List<Integer> numbers=new ArrayList<>();
        List<Integer> number=new ArrayList<>();
        numbers.add(10);
      //  numbers.add(3);
        numbers.add(15);
        Collections.sort(numbers);

        for(int num:numbers){
        }
        for(int i=0;i<String.valueOf(numbers).length();i++){

        }
    }
}
