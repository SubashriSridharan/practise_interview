package com.example.employee.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyService implements Comparable<CompanyService> {

    private String name;
    private String Location;
    /*public CompanyService() {}
    public CompanyService(String name, String location) {
        this.name = name;
        Location = location;
    }

    @Override
    public String toString() {
        return "CompanyService{" +
                "name='" + name + '\'' +
                ", Location='" + Location + '\'' +
                '}';
    }
*/
        public int compareTo(CompanyService o) {
            return this.name.charAt(this.name.length()-1)>o.name.charAt(o.name.length()-1)?1:-1;
        }

}
