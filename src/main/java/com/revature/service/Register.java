package com.revature.service;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.revature.models.Employees;
import com.revature.repositories.EmployeeRepository;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class Register {

    public void registerEmployee(String employeeJson){

        EmployeeRepository repo = new EmployeeRepository();

        ObjectMapper mapper = new ObjectMapper();

        try {

            Employees newEmployee = mapper.readValue(employeeJson, Employees.class);
            if(!repo.getAllEmployees().contains(newEmployee.getEmail())){
                repo.Save(newEmployee);
                System.out.println("Employee Registered");
            }
            



            // JsonNode user = mapper.readTree(employeeJson);
            // // JsonNode employeeList = mapper.readTree(new File("src/main/java/com/revature/repositories/employee.json"));
            // // System.out.println(employeeList.elements());
            // if (!user.get("email").asText().equals("greg@gfake.com")){
            //     Employees newEmployee = mapper.readValue(employeeJson, Employees.class);
            //     repo.Save(newEmployee);
            //     System.out.println("Registered new employee.");
            // } 

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

    public void findEmplyee(){
        
    }
}
