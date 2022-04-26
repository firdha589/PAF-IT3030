package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Customer;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Customer") 			
public class CustomerService {
	
	//notice object 
	Customer customerObj = new Customer(); 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)   
	public String readCustomer(){
	 return customerObj.readCustomer();
	 
  }

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)   
	@Produces(MediaType.TEXT_PLAIN)					   	
	public String insertCustomer(@FormParam("Name") String Name,
			@FormParam("Address") String Address,
			@FormParam("Phone") String Phone,
			@FormParam("NIC") String NIC)
	{
		String output = customerObj.insertCustomer(Name, Address, Phone,NIC);
		return output;
  }



	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData){
		
		//Convert the input string to a JSON object
		JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
		
		//Read the values from the JSON object
		String CID = customerObject.get("CID").getAsString();
		String Name = customerObject.get("Name").getAsString();
		String Address = customerObject.get("Address").getAsString();
		String Phone = customerObject.get("Phone").getAsString();
		String NIC = customerObject.get("NIC").getAsString();
		String output = customerObj.updateCustomer(CID, Name, Address, Phone,NIC) ;
	
		return output;
  }

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData){
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String CID = doc.select("CID").text();
	 String output = customerObj.deleteCustomer(CID);
	
	 return output;
  }


}