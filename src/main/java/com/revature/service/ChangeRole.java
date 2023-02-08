package com.revature.service;

import java.io.IOException;

import com.revature.models.Employees;
import com.revature.models.Employees.Role;
import com.revature.repositories.EmployeeRepository;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ChangeRole {

    public String changeEmployeeRole(String jString){
        
        EmployeeRepository empRepo = new EmployeeRepository();
        
        ObjectMapper mapper = new ObjectMapper();

        try {

            JsonNode treeNode = mapper.readTree(jString);
            if(empRepo.checkManagerStatus(treeNode.get("firstEmail").asText())){
                if(empRepo.getAllEmployees().contains(treeNode.get("secondEmail").asText())){
                    empRepo.promoteEmployee(treeNode.get("secondEmail").asText());
                    return "Employee Promoted to Manager!";
                }else{
                    return "We only promote from within.";
                }
            }else{
                return "Only managers are allowed to promote.";
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
        return "Soemthing went wrong in the promotion function";
    }
}