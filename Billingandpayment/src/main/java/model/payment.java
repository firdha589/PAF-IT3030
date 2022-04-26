package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class payment {
	//Create connection
		public Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paymentdb","root", "");
				 	
			//For testing
				 System.out.print("Successfully connected");
			
			} catch(Exception e){
					e.printStackTrace();
			}

				
			return con;
		}
		
	//Add a payment
	public String insertPayment(String itemCode,String customerId, String amount, String pMethod, String cardNo,String paymentDate) {
		
		String output = "";
		System.out.println(itemCode);
		try {
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database";
			}

		
				// create a prepared statement
				String query = " insert into payments(`itemCode`,`customerId`,`bidId`,`amount`,`pMethod`,`cardNo`,`paymentDate`)"
					+ " values (?, ?, ?,?, ?, ?,?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
			
				preparedStmt.setString(1, itemCode);
				preparedStmt.setString(2, customerId);
				preparedStmt.setDouble(4, Double.parseDouble(amount));
				preparedStmt.setString(5, pMethod);
				preparedStmt.setString(6, cardNo); 
				preparedStmt.setString(7, paymentDate); 
	
				//execute the statement
				preparedStmt.execute();
				con.close();
				output = "Payment is successfully added";
			
		}catch (Exception e) {
				output = "Error while inserting";
				System.err.println(e.getMessage());
		}
		 	
		return output;
	}
	
	//select  payments for a customer
	public String getPayment(String customerId) {
		String output = "";
				
		try {
			Connection con = this.connect();
			if (con == null) {
				return "Error while connecting to retrieving payment details";
			}		
			output = "<table border='1'><tr>"
					+ "<th> Customer Id</th>"
					+ "<th> Payment Id</th>"
					+"<th> Unit Id</th>"
					+ "<th>Amount</th>"
					+ "<th>Payment Method</th>"
					+ "<th>Card No</th>"
					+ "<th>Payment Date</th></tr>";
					
			String query = "select * from payments where customerId = ?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setString(1, customerId);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {	
				String payId = rs.getString("payid");
				String itemCode = rs.getString("Code");
				customerId = rs.getString("customerId");
				String amount = Double.toString(rs.getDouble("Amount"));
				String pMethod = rs.getString("pMethod");
				String cardNo = rs.getString("cardNo");
				String paymentDate = rs.getString("paymentDate");
				
				// Add a row into the html table
				output += "<tr><td>" + customerId + "</td>";
				output += "<td>" + payId + "</td>";
				output += "<td>" + itemCode + "</td>";
				output += "<td>" + amount + "</td>"; 
				output += "<td>" + pMethod +"</td>";
				output += "<td>" +  cardNo  +"</td>";
				output += "<td>" + paymentDate   +"</td></tr>";
	 

			}
			
			con.close();			
			
		} catch (Exception e) {
			output = "Error while getting Payment details ";
			System.err.println(e.getMessage());
		}
		
		return output;
	}



	
}
