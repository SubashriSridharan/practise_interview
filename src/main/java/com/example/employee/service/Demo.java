package com.example.employee.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class Demo {

        public List<CompanyService> getCompanyName() {
            List<CompanyService> companies = new ArrayList<CompanyService>();
            companies.add(new CompanyService("Amazon", "Amazon"));
            companies.add(new CompanyService("Ford", "France"));
            companies.add(new CompanyService("Google", "Silicon Valley"));

           Collections.sort(companies);
            //companies.sort(Comparator.comparing(CompanyService::getLocation));
            return companies;
        }
}
