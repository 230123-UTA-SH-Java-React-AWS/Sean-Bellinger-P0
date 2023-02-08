package com.revature.service;

import java.io.IOException;
import java.math.BigDecimal;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.models.Tickets;
import com.revature.repositories.EmployeeRepository;
import com.revature.repositories.TicketRepositoriy;

public class CreateExpenseTicket {
    public String createTicket(String ticketJson){
        EmployeeRepository employeeRepo = new EmployeeRepository();
        TicketRepositoriy repo = new TicketRepositoriy();

        ObjectMapper mapper = new ObjectMapper();

        try {
            Tickets newTicket = mapper.readValue(ticketJson, Tickets.class);
            if(newTicket.getAmount().compareTo(new BigDecimal("0")) > 0 && !(newTicket.getDescription() == null)){
                
                if(employeeRepo.getAllEmployees().contains(newTicket.getEmail())){

                    repo.Save(newTicket);
                    return "New Ticket Added";
                } else {
                    return "Please register as an employee first.";
                }
            } else{
                return "Please fill the fields with valid entries.";
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
        return "Something went wrong in the ticket creation function";
    }
}
