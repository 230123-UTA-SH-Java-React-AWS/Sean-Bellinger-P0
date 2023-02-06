package com.revature.service;

import java.io.IOException;


import com.revature.models.Employees;
import com.revature.repositories.EmployeeRepository;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class Register {

    public void registerEmployee(String employee){

        EmployeeRepository repo = new EmployeeRepository();

        ObjectMapper mapper = new ObjectMapper();


        try {

            Employees newEmployee = mapper.readValue(employee, Employees.class);
            if(!repo.getAllEmployees().contains(newEmployee.getEmail())){
                repo.Save(newEmployee);
                System.out.println("Employee Registered");
            } else if(newEmployee.getPassword().equals(repo.checkPassword(newEmployee))){
                System.out.println("You're Logged in!");
            } else {
                System.out.println("Wrong Password.");
            }
            // repo.Save(newEmployee);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
