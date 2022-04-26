package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class customer {
	
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/customer", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 
	
	
	
	
	
	public String insertCustomer(String Firstname, String Lastname, String Occupation, String DOB, String Gender, String Province, String City){
	
		String output = "";
	 try{
		 Connection con = connect();
		 if (con == null){
			 return "Error while connecting to the database for inserting."; 
			 }
	 // create a prepared statement
		 
	 String query = " insert into customer_details(`CustomerID`,`Firstname`,`Lastname`,`Occupation`,`DOB`,`Gender`,`Province`,`City`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, Firstname);
	 preparedStmt.setString(3, Lastname);
	 preparedStmt.setString(4, Occupation);
	 preparedStmt.setString(5, DOB);
	 preparedStmt.setString(6, Gender);
	 preparedStmt.setString(7, Province);
	 preparedStmt.setString(8, City);
	 // execute the statement
	
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e){
	 output = "Error while inserting the item.";
	 System.err.println(e.getMessage());
	 	}
	 return output;
	 } 

	
	
	
	
	
	public String readcustomer(){
	 String output = "";
	 try{
		 Connection con = connect();
		 if (con == null){
			 return "Error while connecting to the database for reading."; 
			 }
		 
	 // Prepare the html table to be displayed
		 
	 output = "<table border='1'>"
	 		+ "<tr>"
	 		+ "<th>First Name</th>"
	 		+ "<th>Last Name</th>" 
	 		+ "<th>Occupation</th>" 
	 		+ "<th>Date of Birth</th>" 
	 		+ "<th>Gender</th>" 
	 		+ "<th>Province</th>" 
	 		+ "<th>City</th>" 
	 		+ "<th>Update</th>"
	 		+ "<th>Remove</th>"
	 		+ "</tr>";

	 String query = "select * from customer_details";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 
	 while (rs.next()){
		 
		 String CustomerID = Integer.toString(rs.getInt("CustomerID"));
		 String Firstname = rs.getString("Firstname");
		 String Lastname = rs.getString("Lastname");
		 String Occupation = rs.getString("Occupation");
		 String DOB = rs.getString("DOB");
		 String Gender = rs.getString("Gender");
		 String Province = rs.getString("Province");
		 String City = rs.getString("City");




	 // Add into the html table
		 output += "<tr><td>" + Firstname + "</td>";
		 output += "<td>" + Lastname + "</td>";
		 output += "<td>" + Occupation + "</td>";
		 output += "<td>" + DOB + "</td>";
		 output += "<td>" + Gender + "</td>";
		 output += "<td>" + Province + "</td>";
		 output += "<td>" + City + "</td>";


	 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='items.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='CustomerID' type='hidden' value='" + CustomerID
				 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e){
		 
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
	 }
	 	return output;
	 } 
	
	
	
	

	
	
	
	public String updatecustomer(String CustomerID, String Firstname, String Lastname, String Occupation, String DOB, String Gender, String Province, String City){
		 String output = "";
		 try{
			 Connection con = connect();
			 if (con == null){
				 return "Error while connecting to the database for updating."; 
				 }
			 
		 // create a prepared statement
			 
		 String query = "UPDATE customer_details SET Firstname=?,Lastname=?,Occupation=?,DOB=?,Gender=?,Province=?,City=? WHERE CustomerID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 
		 preparedStmt.setString(1, Firstname);
		 preparedStmt.setString(2, Lastname);
		 preparedStmt.setString(3, Occupation);
		 preparedStmt.setString(4, DOB);
		 preparedStmt.setString(5, Gender);
		 preparedStmt.setString(6, Province);
		 preparedStmt.setString(7, City);

		 preparedStmt.setInt(8, Integer.parseInt(CustomerID));
		 
		 // execute the statement
		 
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e){
			 
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
	
	
	
	public String deletecustomer(String CustomerID){
	 String output = "";
	 try{
		 Connection con = connect();
		 if (con == null){
			 return "Error while connecting to the database for deleting."; 
			 }
	 // create a prepared statement
		 
	 String query = "delete from customer_details where CustomerID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(CustomerID));
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
