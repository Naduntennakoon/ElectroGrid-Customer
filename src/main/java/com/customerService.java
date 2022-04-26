package com;
import model.customer;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 


@Path("/customer")
public class customerService {
	customer customerObj = new customer();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readcustomer()
	 {
		return customerObj.readcustomer();
	 } 
	
	
	
	
	
	

	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(
	@FormParam("Firstname") String Firstname,
	 @FormParam("Lastname") String Lastname,
	 @FormParam("Occupation") String Occupation,
	 @FormParam("DOB") String DOB,
	 @FormParam("Gender") String Gender,
	 @FormParam("Province") String Province,
	 @FormParam("City") String City)
	{
	 String output = customerObj.insertCustomer(Firstname, Lastname, Occupation, DOB, Gender, Province, City);
	 
	return output;
	
	}
	
	
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatecustomer(String customerData)
	{
	//Convert the input string to a JSON object
		
	 JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 
	 String CustomerID = customerObject.get("CustomerID").getAsString();
	 String Firstname = customerObject.get("Firstname").getAsString();
	 String Lastname = customerObject.get("Lastname").getAsString();
	 String Occupation = customerObject.get("Occupation").getAsString();
	 String DOB = customerObject.get("DOB").getAsString();
	 String Gender = customerObject.get("Gender").getAsString();
	 String Province = customerObject.get("Province").getAsString();
	 String City = customerObject.get("City").getAsString();

	 String output = customerObj.updatecustomer(CustomerID, Firstname, Lastname, Occupation, DOB, Gender, Province, City);
	 
	return output;
	}
	
	
	
	
	
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletecustomer(String customerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String CustomerID = doc.select("CustomerID").text();
	 String output = customerObj.deletecustomer(CustomerID);
	return output;
	}


	
	
	
	

}
