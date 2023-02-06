package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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