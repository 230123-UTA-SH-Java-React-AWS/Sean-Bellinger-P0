package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;

import com.revature.models.Employees;
import com.revature.models.Tickets;
import com.revature.utils.ConnectionUtil;

public class TicketRepositoriy {

    public void Save(Tickets ticket){
        
        String sql = "insert into tickets (amount, description, status, employeeemail) values (?, ?, ?, ?)";

            try(Connection con = ConnectionUtil.getConnection()) {
                
                PreparedStatement prstmt = con.prepareStatement(sql);

                prstmt.setBigDecimal(1, ticket.getAmount());
                prstmt.setString(2, ticket.getDescription());
                prstmt.setString(3, ticket.getStatus().getValue());
                prstmt.setString(4, ticket.getEmail());

                prstmt.execute();

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }


    }

    public void Update(JsonNode treeNode){
        
        String sql = "update tickets set status = ? where ticketID = ?";

            try(Connection con = ConnectionUtil.getConnection()) {
                
                PreparedStatement prstmt = con.prepareStatement(sql);

                prstmt.setString(1, treeNode.get("status").asText());
                prstmt.setInt(2, treeNode.get("ticketID").asInt());

                prstmt.execute();

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }


    }

    public ArrayList<Tickets> getAllPendingTickets(){

        String sql = "select * from tickets where status = ?";
        ArrayList<Tickets> ticketList = new ArrayList<Tickets>();

        try(Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, "Pending");
            ResultSet rs = prstmt.executeQuery();
            while(rs.next()){
                Tickets newTicket = new Tickets();
                newTicket.setTicketID(rs.getInt(1));
                newTicket.setAmount(rs.getBigDecimal(2));
                newTicket.setDescription(rs.getString(3));
                newTicket.setEmail(rs.getString(5));
                ticketList.add(newTicket);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return ticketList;
        
    }

    public ArrayList<Tickets> getAllPersonalTickets(JsonNode jNode){

        String sql = "select * from tickets where employeeemail = ?";
        ArrayList<Tickets> ticketList = new ArrayList<Tickets>();


        try(Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, jNode.get("email").asText());
            ResultSet rs = prstmt.executeQuery();
            while(rs.next()){
                Tickets newTicket = new Tickets();
                newTicket.setTicketID(rs.getInt(1));
                newTicket.setAmount(rs.getBigDecimal(2));
                newTicket.setDescription(rs.getString(3));
                newTicket.setStatus(rs.getString(4));
                newTicket.setEmail(rs.getString(5));
                ticketList.add(newTicket);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return ticketList;
        
    }

    public ArrayList<Tickets> getAllSpecifiedPersonalTickets(JsonNode jNode){

        String sql = "select * from tickets where status = ? and employeeemail = ?";
        ArrayList<Tickets> ticketList = new ArrayList<Tickets>();


        try(Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, jNode.get("status").asText());
            prstmt.setString(2, jNode.get("email").asText());
            ResultSet rs = prstmt.executeQuery();
            while(rs.next()){
                Tickets newTicket = new Tickets();
                newTicket.setTicketID(rs.getInt(1));
                newTicket.setAmount(rs.getBigDecimal(2));
                newTicket.setDescription(rs.getString(3));
                newTicket.setStatus(rs.getString(4));
                newTicket.setEmail(rs.getString(5));
                ticketList.add(newTicket);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return ticketList;
        
    }

    public ArrayList<Tickets> getAllEmployeeTickets(String empEmail){

        String sql = "select * from tickets where employeeemail = ?";
        ArrayList<Tickets> ticketList = new ArrayList<Tickets>();

        try(Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, empEmail);
            ResultSet rs = prstmt.executeQuery();
            while(rs.next()){
                Tickets newTicket = new Tickets();
                newTicket.setTicketID(rs.getInt(1));
                newTicket.setAmount(rs.getBigDecimal(2));
                newTicket.setDescription(rs.getString(3));
                newTicket.setEmail(rs.getString(5));
                ticketList.add(newTicket);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return ticketList;
        
    }

}