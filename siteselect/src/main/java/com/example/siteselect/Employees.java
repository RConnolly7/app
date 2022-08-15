package com.example.siteselect;

import java.util.ArrayList;
import java.util.List;

// Class to store the list of all the employees in an Array List
public class Employees {
    private List<employee> employeeList;
    // Method to return the list of employees
    public List<employee> getEmployeeList() {
        if (employeeList == null) {
            employeeList = new ArrayList<>();
        }
        return employeeList;
    }
    public void setEmployeeList(
            List<employee> employeeList){
            this.employeeList = employeeList;
    }
}