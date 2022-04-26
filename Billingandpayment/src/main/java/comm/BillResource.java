package comm;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("bill")
public class BillResource {
	
	billRepository repo1 =new billRepository();
	
	 // post method
	   @POST
	   @Path("bill")
	   @Consumes({MediaType.APPLICATION_ATOM_XML,MediaType.APPLICATION_JSON}) 
	    public Bill createBill(Bill bill1){
	       repo1.create(bill1);
	       return bill1;
	    }
		



}
