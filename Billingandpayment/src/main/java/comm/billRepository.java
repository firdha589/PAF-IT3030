package comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class billRepository {
	 Connection con = null;

	    public  billRepository () {

	        String url = "jdbc:mysql://localhost:3306/billing_system";
	        String username = "sajitha";
	        String password = "root";
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection(url, username, password);
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }
	    
	    public void create(Bill bill){
	        String sql = "insert into bill values (?,?,?,?,?) ";
	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            st.setInt(1,bill.getBill_no());
	            st.setInt(2,bill.getUnit());
	            st.setInt(3,bill.getTax());
	            st.setInt(4,bill.getFixed_charge());
	            st.setInt(5,bill.getTotal());
	            st.executeUpdate();
	        }catch (Exception e){
	            System.out.println(e);
	        }
	      }
	    
	    
	    
	 
	    public Bill getBill(int id) {
	    String sql = "select * "
	    		+ "from customer c INNER JOIN bill b "
	    		+ " where c.cid=b.cid";
	    Bill bill1 = new Bill();
	    try {
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        if (rs.next()) {
	           
	            bill1.setBill_no(rs.getInt(1));
	            bill1.setUnit(rs.getInt(2));
	            bill1.setTotal(rs.getInt(3));
	            bill1.setFixed_charge(rs.getInt(4));
	            bill1.setTax(rs.getInt(5));
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	       return bill1;
	    }
	      
	    

}
