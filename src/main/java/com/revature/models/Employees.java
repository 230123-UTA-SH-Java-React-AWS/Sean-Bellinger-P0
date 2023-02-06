package com.revature.models;

public class Employees {
    
    private String email;
    private String password;
    public enum Role {
        EMPLOYEE("Employee"),
        MANAGER("Manager");

        public String value;
        
        Role(String rolesArg) {
            value = rolesArg;
        }

        public String getValue(){
            return value;
        }
    }

    private Role role = Role.EMPLOYEE;

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}