package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.models.Employees;
import com.revature.models.Tickets;
import com.revature.repositories.EmployeeRepository;
import com.revature.repositories.TicketRepositoriy;

public class reviewTickets {

    /*
     * use getAllEmployees() to pull list of employess WHERE role is manager
     * If email associated is manager proceed
     * pull tickets into a list, if WHERE status is pending
     * order by ID
     * create another service to approve or deny by ticket id number.
     */

     public String collectTickets(String jString){
        
        EmployeeRepository employeeRepo = new EmployeeRepository();
        TicketRepositoriy ticketRepo = new TicketRepositoriy();
        
        ObjectMapper mapper = new ObjectMapper();
        String currentTicket = "";
        
        try {
            JsonNode treeNode = mapper.readTree(jString);
            String treeNodeEmail = treeNode.get("email").asText();

            if(employeeRepo.checkManagerStatus(treeNodeEmail)){
                ArrayList<Tickets> ticketList = ticketRepo.getAllPendingTickets();
                currentTicket = mapper.writeValueAsString(ticketList);
            }else{
                System.out.println("Only Managers can review tickets.");
            }
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
        return currentTicket;
    }

}