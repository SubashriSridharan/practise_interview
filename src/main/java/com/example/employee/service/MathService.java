package com.example.employee.service;

@FunctionalInterface
public interface MathService {

    int add(int a, int b);
    default int subtract(int a, int b) {
        return a - b;
    }
}
