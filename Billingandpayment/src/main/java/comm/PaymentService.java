package comm;

import model.payment;

//For generating  date
import java.text.SimpleDateFormat;
import java.util.Date;


//For REST Service

import org.glassfish.jersey.internal.routing.CombinedMediaType;
import org.glassfish.jersey.server.wadl.internal.WadlResource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;



@Path("/payment")
public class PaymentService<Client> {

	
	 payment paymentObj = new payment();
	 @POST
	 @Path("/")
	 @Consumes(jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(jakarta.ws.rs.core.MediaType.TEXT_PLAIN)
	 public String insertPayment(@FormParam(value = "itemCode") String itemCode,
			 @FormParam(value = "customerId") String customerId,
			 @FormParam(value = "amount") String amount,
			 @FormParam(value = "pMethod") String pMethod,
			 @FormParam(value = "cardNo")String cardNo) {
		 
		 	//Get current date
		 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		 	String paymentDate = formatter.format( new Date());
		 	
		 				 
		 	Object client;
			WadlResource webResource= ((Object) client).resource("http://localhost:8088/PaymentManagement/PaymentService" + customerId);
		 	ClientResponseFilter response = webResource.type("application/xml").get(ClientResponseContext.class);
		 	
		 	
		 	String accepted = response.getEntity(String.class);
		 	System.out.println("State" +accepted);
		 	if(accepted.equals("Yes") ) {
		 	
		 	
			 
	 }
	 
	 @GET
	 @Path("/{cId}")
	 public String getPayment(@PathParam(value="cId") String customerId) {
		return paymentObj.getPayment(customerId);
		 
		 
	 }
	 
	 

}

