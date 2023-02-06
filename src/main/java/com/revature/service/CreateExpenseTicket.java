package com.revature.service;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.models.Tickets;
import com.revature.repositories.EmployeeRepository;
import com.revature.repositories.TicketRepositoriy;

public class CreateExpenseTicket {
    public void createTicket(String ticketJson){
        EmployeeRepository employeeRepo = new EmployeeRepository();
        TicketRepositoriy repo = new TicketRepositoriy();

        ObjectMapper mapper = new ObjectMapper();

        try {

            Tickets newTicket = mapper.readValue(ticketJson, Tickets.class);
            
            if(employeeRepo.getAllEmployees().contains(newTicket.getEmail())){

                repo.Save(newTicket);
                System.out.println("New Ticket Added");
            } else {
                System.out.println("Please register as an employee first.");
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
