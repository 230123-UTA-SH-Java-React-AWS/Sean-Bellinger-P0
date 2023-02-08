package com.revature.models;

import java.math.BigDecimal;

public class Tickets {
    
    private BigDecimal amount;
    private String description;
    private String email;
    private int ticketID;

    public enum Status {
        PENDING("Pending"),
        DECLINED("Declined"),
        APPROVED("Approved");
        public String value;
        
        Status(String statusArg) {
            value = statusArg;
        }

        public String getValue(){
            return value;
        }

    }
    

    public int getTicketID() {
        return ticketID;
    }
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }
    private Status status = Status.PENDING;
    
    public Status getStatus() {
        return status;
    }
    public void setStatus(String status) {
        if(status.equals("Pending")){
            this.status = Status.PENDING;
        }else if(status.equals("Declined")){
            this.status = Status.DECLINED;
        }else{
            this.status = Status.APPROVED;
        }

    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}