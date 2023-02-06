package com.revature.service;

import java.io.IOException;

import com.revature.models.Employees;
import com.revature.models.Employees.Role;
import com.revature.repositories.EmployeeRepository;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ChangeRole {

    public void changeEmployeeRole(String employee){
        
        EmployeeRepository repo = new EmployeeRepository();
        
        ObjectMapper mapper = new ObjectMapper();

        try {

            Employees currentEmployee = mapper.readValue(employee, Employees.class);

            if(repo.getAllEmployees().contains(currentEmployee.getEmail())){
                currentEmployee.setRole(Role.MANAGER);
                repo.promoteEmployee(currentEmployee);
                System.out.println("Employee Promoted to Manager!");
            }else{
                System.out.println("We only promote from within.");
            }
        }catch (JsonParseException e) {
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