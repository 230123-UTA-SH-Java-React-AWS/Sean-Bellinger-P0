package com.revature.models;

import java.math.BigDecimal;

public class Tickets {
    
    private BigDecimal amount;
    private String description;
    private String email;
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
    


    private Status status = Status.PENDING;
    
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
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