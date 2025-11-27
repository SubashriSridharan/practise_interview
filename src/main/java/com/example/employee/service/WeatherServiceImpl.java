package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherServiceImpl {

    @Autowired
    WebClient webClient;


    public String getWeatherInformation(String city) {
        String url="https://api.weatherapi.com/v1/current.json";
        return webClient.get().uri(uriBuilder ->
               uriBuilder.scheme("https").host("api.weatherapi.com").path("/v1/current.json")
                       .queryParam("key","30580fffc50a49e183d04952252006 ")
                       .queryParam("q",city).build()).retrieve().bodyToMono(String.class).block();


    }
}
