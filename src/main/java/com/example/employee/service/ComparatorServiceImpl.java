package com.example.employee.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComparatorServiceImpl {

    public List<String> compareStringData(){

        Comparator<String> comparator = (o1, o2) -> {
            if(o1.charAt(o1.length()-1)>o2.charAt(o2.length()-1))
                return 1;
            else
                return -1;

        };

        Comparator<String> com =(str1,str2)->{
            return str1.length()> str2.length()?1:-1;
        };


        List<String> countyList = new ArrayList<String>();
        countyList.add("India");
        countyList.add("UAE");
        countyList.add("US");
        countyList.add("Chicago");
        countyList.add("France");
        //Collections.sort(list, comparator);

        countyList.sort(Comparator.comparing(country->country.charAt(country.length()-1)));
      ///  Collections.sort(countyList, com);
        return countyList;
    }

}
