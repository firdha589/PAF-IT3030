package com;

import model.Ecustomer;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Econ")
public class EcustomerService
{
	
	Ecustomer cusconne = new Ecustomer();


//GET
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readCone()
{
	return cusconne.readCone();
}

//post
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertCone(@FormParam("electricID") String electricID,
@FormParam("fullName") String fullName,
@FormParam("phoneNo") String phoneNo,
@FormParam("wiringMethod") String wiringMethod )
{
	String output = cusconne.insertCone(electricID,fullName, phoneNo, wiringMethod);
	return output;
}

//PUT
@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateCone(String ConeData)
{
//Convert the input string to a JSON object
 JsonObject ConeObject = new JsonParser().parse(ConeData).getAsJsonObject();
//Read the values from the JSON object
 String ConID = ConeObject.get("ConID").getAsString();
 String electricID = ConeObject.get("electricID").getAsString();
 String fullName = ConeObject.get("fullName").getAsString();
 String phoneNo = ConeObject.get("phoneNo").getAsString();
 String wiringMethod = ConeObject.get("wiringMethod").getAsString();
 String output = cusconne.updateCone(ConID, electricID, fullName,phoneNo,wiringMethod);
return output;
}

//DELETE
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteCone(String ConeData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(ConeData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String ConID = doc.select("ConID").text();
 String output =cusconne.deleteCone(ConID);
return output;
}

}