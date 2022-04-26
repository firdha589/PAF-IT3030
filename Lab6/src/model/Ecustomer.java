package model;

import java.sql.*;

//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;

public class Ecustomer {
  //A common method to connect to the DB
  private Connection connect()
  {
    Connection con = null;
    try
      {
        Class.forName("com.mysql.jdbc.Driver");
        //Provide the correct details: DBServer/DBName, username, password
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lab", "root", "");
      }
    catch (Exception e)
      {
        e.printStackTrace();
      }
      return con;
  }
  
  public String insertCone(String electricID, String fullName, String phoneNo, String wiringMethod)
  {
     String output = "";
    try
    {
      Connection con = connect();
      if (con == null)
    {
      
      return "Error while connecting to the database for inserting."; 
    }
  
      // create a prepared statement
      String query = " insert into items(`ConID`,`electricID`,`fullName`,`phoneNo`,`wiringMethod`)"+ " values (?, ?, ?, ?, ?)";
          PreparedStatement preparedStmt = con.prepareStatement(query);
         
        // binding values
      preparedStmt.setInt(1, 0);
      preparedStmt.setString(2, electricID);
      preparedStmt.setString(3, fullName);
      preparedStmt.setString(4, phoneNo);
      preparedStmt.setString(5, wiringMethod);
  
      // execute the statement
      preparedStmt.execute();
      con.close();
      output = "Successfully Insert";
    }
    catch (Exception e)
    {
      output = "Error while inserting the item.";
      System.err.println(e.getMessage());
    }
    return output;
  }
  
  public String readCone()
  {
    String output = "";
      try
      {
        Connection con = connect();
        if (con == null)
      {  
        return "Error while connecting to the database for reading."; 
        }
        
        // Prepare the html table to be displayed
        output = "<table border='1'><tr><th>Electic ID</th><th>Full Name</th>" +
            "<th>Phone No</th>" + 
            "<th>Wiring Method</th>"+
            "<th>Update</th><th>Remove</th></tr>";
        
        String query = "select * from items";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    
       // iterate through the rows in the result set
      
      while (rs.next())
      {
        String ConID = Integer.toString(rs.getInt("ConID"));
        String electricID = rs.getString("electricID");
        String fullName = rs.getString("fullName");
        String phoneNo = rs.getString("phoneNo");
        String wiringMethod = rs.getString("wiringMethod");
        
        // Add into the html table
        output += "<tr><td>" + electricID + "</td>";
        output += "<td>" + fullName + "</td>";
        output += "<td>" + phoneNo + "</td>";
        output += "<td>" + wiringMethod + "</td>";
        // buttons
        output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
        + "<input name='electricID' type='hidden' value='" + ConID 
        + "'>" + "</form></td></tr>";
      }
        con.close();
        // Complete the html table
        output += "</table>";
      }
      catch (Exception e)
      {
        output = "Error while reading the items.";
        System.err.println(e.getMessage());
      }
    return output;
  }
  
  public String updateCone(String ConID,String electricID,String fullName,String phoneNo,String wiringMethod)
  {
    String output = "";
      try
      {
        Connection con = connect();
        if (con == null)
      {
        return "Error while connecting to the database for updating."; 
      }
        
        // create a prepared statement
          String query = "UPDATE items SET electricID=?,fullName=?,phoneNo=?,wiringMethod=? WHERE ConID=?";
          PreparedStatement preparedStmt = con.prepareStatement(query);
        
        // binding values
        preparedStmt.setString(1, electricID);
        preparedStmt.setString(2, fullName);
        preparedStmt.setString(3, phoneNo);
        preparedStmt.setString(5, wiringMethod);
        preparedStmt.setInt(6, Integer.parseInt(ConID));
       
        // execute the statement
        preparedStmt.execute();
        con.close();
        output = "Updated successfully";
      }
      catch (Exception e)
      {
        output = "Error while updating the item.";
        System.err.println(e.getMessage());
      }
    return output;
  }
      
  public String deleteCone(String ConID)
  {
    String output = "";
      try
      {
          Connection con = connect();
          if (con == null)
      {
        return "Error while connecting to the database for deleting."; 
      }
          
      // create a prepared statement
          String query = "delete from items where ConID=?";
          PreparedStatement preparedStmt = con.prepareStatement(query);
      
      // binding values
          preparedStmt.setInt(1, Integer.parseInt(ConID));
      
      // execute the statement
          preparedStmt.execute();
          con.close();
          output = "Deleted successfully";
      }
      catch (Exception e)
      {
          output = "Error while deleting the item.";
          System.err.println(e.getMessage());
      }
      return output;
      }
  }