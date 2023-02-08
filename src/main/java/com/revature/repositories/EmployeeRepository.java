package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.revature.models.Employees;
import com.revature.utils.ConnectionUtil;


public class EmployeeRepository {

    public HashSet<String> getAllEmployees(){

        String sql = "select * from employees";
        HashSet<String> employeeList = new HashSet<String>();

        try(Connection con = ConnectionUtil.getConnection()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                employeeList.add(rs.getString(2));
                employeeList.add(rs.getString(3));
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public boolean checkManagerStatus(String emailString){

        
        String sql = "select employeerole from employees where employeeemail = ?";
        // boolean isManager = false;

        try(Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, emailString);
            ResultSet rs = prstmt.executeQuery();
            rs.next();
            String employeeRole = rs.getString(1);
            
            if(employeeRole.equals("Manager")){
                return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void promoteEmployee(String employeeEmail){

        String sql = "update employees set employeerole = ? where employeeemail = ?";
   

        try(Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, "Manager");
            prstmt.setString(2, employeeEmail);
            prstmt.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }

    public String checkPassword(Employees employee){

        String sql = "select employeepassword from employees where employeeemail = ?";
        String password = "";

        try(Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, employee.getEmail());
            ResultSet rs = prstmt.executeQuery();
            rs.next();
            password = rs.getString(1);
            


        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
    
    public void Save(Employees employee){

        String sql = "insert into employees (employeeemail, employeepassword, employeerole) values (?, ?, ?)";

            try(Connection con = ConnectionUtil.getConnection()) {
                PreparedStatement prstmt = con.prepareStatement(sql);

                prstmt.setString(1, employee.getEmail());
                prstmt.setString(2, employee.getPassword());
                prstmt.setString(3, employee.getRole().getValue());

                prstmt.execute();


            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }


    }

}
