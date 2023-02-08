package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.Node;

import com.revature.models.Employees;
import com.revature.models.Tickets;
import com.revature.repositories.EmployeeRepository;
import com.revature.repositories.TicketRepositoriy;

public class viewPersonalTickets {

    public String viewYourTickets(String jString){
        EmployeeRepository empRepo = new EmployeeRepository();
        TicketRepositoriy tickRepo = new TicketRepositoriy();
        ObjectMapper mapper = new ObjectMapper();
        String currentTicket = "";

        try{
            JsonNode treeNode = mapper.readTree(jString);
            if(treeNode.findValue("status") == null){
                ArrayList<Tickets> ticketList = tickRepo.getAllPersonalTickets(treeNode);
                return currentTicket = mapper.writeValueAsString(ticketList);
            } else {
                ArrayList<Tickets> ticketList = tickRepo.getAllSpecifiedPersonalTickets(treeNode);
                return currentTicket = mapper.writeValueAsString(ticketList);
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
