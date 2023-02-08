package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.models.Employees;
import com.revature.models.Tickets;
import com.revature.models.Tickets.Status;
import com.revature.repositories.EmployeeRepository;
import com.revature.repositories.TicketRepositoriy;

public class processTicket {
    
    public String approveProcess(String jString){

        EmployeeRepository empRepo = new EmployeeRepository();
        TicketRepositoriy tickRepo = new TicketRepositoriy();
        ObjectMapper mapper = new ObjectMapper();
        
        try{
            JsonNode treeNode = mapper.readTree(jString);
            String treeNodeEmail = treeNode.get("email").asText();
            // String treeNodeStatus = treeNode.get("status").asText();
            // String treeNodeID = treeNode.get("ticketID").asText();
            if(empRepo.checkManagerStatus(treeNodeEmail)){
                // ArrayList<Tickets> pendingList = tickRepo.getAllPendingTickets();
                tickRepo.Update(treeNode);
                return "Ticket has been approved";
            }else{
                return "Only Managers can process tickets.";
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
        return "Something went wrong in the approval process function";
    }

}