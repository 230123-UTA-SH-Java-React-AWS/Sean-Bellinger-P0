package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;

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

     public void collectTickets(String employee){
        
        EmployeeRepository employeeRepo = new EmployeeRepository();
        TicketRepositoriy ticketRepo = new TicketRepositoriy();
        
        ObjectMapper mapper = new ObjectMapper();

        try {

            Employees currentEmployee = mapper.readValue(employee, Employees.class);

            if(employeeRepo.checkManagerStatus(currentEmployee)){
                ArrayList<Tickets> ticketList = ticketRepo.getAllPendingTickets();
                System.out.println(
                    "Reviewing ticket for: " + ticketList.get(0).getEmail() + 
                    "\n" + "Amount: $" + ticketList.get(0).getAmount() + 
                    "\n" + "Description: " + ticketList.get(0).getDescription()
                    );
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

    }

}