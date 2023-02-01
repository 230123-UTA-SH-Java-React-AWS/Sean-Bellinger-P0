package com.revature.repositories;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.models.Employees;
import com.revature.utils.ConnectionUtil;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class EmployeeRepository {

    public HashSet<String> getAllEmployees(){

        String sql = "select employeeemail from employees";
        HashSet<String> employeeList = new HashSet<String>();

        try(Connection con = ConnectionUtil.getConnection()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Employees newEmployee = new Employees();

                // newEmployee.setEmail(rs.getString(1));

                employeeList.add(rs.getString(1));
            }

        } catch (Exception e) {

        }
        return employeeList;
    }
    
    public void Save(Employees employee){




        // ObjectMapper mapper = new ObjectMapper();
        // String jsonObject = "";

        // try {
        //     jsonObject = mapper.writeValueAsString(employee);

        //     File employeeFile = new File("./src/main/java/com/revature/repositories/employee.json");
        //     employeeFile.createNewFile();
        //     FileWriter writer = new FileWriter("./src/main/java/com/revature/repositories/employee.json");
        //     writer.write(jsonObject);
        //     writer.close();

        // } catch (JsonGenerationException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (JsonMappingException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        String sql = "insert into employees (employeeemail, employeepassword) values (?, ?)";

            try(Connection con = ConnectionUtil.getConnection()) {
                PreparedStatement prstmt = con.prepareStatement(sql);
                System.out.println("We made it to the preparedstatement!");

                prstmt.setString(1, employee.getEmail());
                prstmt.setString(2, employee.getPassword());

                prstmt.execute();


            } catch (Exception e) {
                // TODO: handle exception
            }


    }

}
