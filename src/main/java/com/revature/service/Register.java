package com.revature.service;
import java.io.IOException;

import com.revature.models.Employees;
import com.revature.repositories.EmployeeRepository;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Register {
    public void registerEmployee(String employeeJson){

        EmployeeRepository repo = new EmployeeRepository();

        ObjectMapper mapper = new ObjectMapper();

        try {

            JsonNode user = mapper.readTree(employeeJson);
            
            if (!user.get("email").asText().equals("bob@gfake.com")){
                Employees newEmployee = mapper.readValue(employeeJson, Employees.class);
                repo.Save(newEmployee);
                System.out.println("Registered new employee.");
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

    public void findEmplyee(){
        
    }
}
