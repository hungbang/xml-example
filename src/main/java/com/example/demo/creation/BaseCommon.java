package com.example.demo.creation;


import com.example.demo.model.Employees;

public class BaseCommon {

    public Employees prepareData(){
        Employees employees = new Employees();
        employees.setId("1");
        employees.setName("Rich");
        employees.setSalary("$1000");
        employees.setAddress("NY");
        return employees;
    }
}
