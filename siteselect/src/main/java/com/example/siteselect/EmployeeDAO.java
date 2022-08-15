package com.example.siteselect;

//import org.springframework.stereotype.Repository;
// Importing the employees class to use the defined properties in this class


//@Repository

// Class to create a list of employees
public class EmployeeDAO {

    private static Employees list= new Employees();

    // This static block is executed before executing the main block
    static{
        // Creating a few employees
        // and adding them to the list
        list.getEmployeeList().add(new employee(1,"Empl1", "Sur","example1"));
        list.getEmployeeList().add(new employee(2, "Empl2","Sur","example2"));
        list.getEmployeeList().add(new employee(3,"Empl3","Sur","example3"));
    }

    // Method to return the list
    public Employees getAllEmployees()
    {

        return list;
    }


    // Method to add an employee
    // to the employees list
    public void
    addEmployee(employee employee){
        list.getEmployeeList().add(employee);
    }
}