import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Mysql1 {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/test";
	   static final String USER = "user";
	   static final String PASS = "password";
	   
	   public static HashMap<String,String> map=new HashMap<String,String>(); 
	
public void  add(String username,String password,String question,String answer,String name,String userid,String contact ) throws SQLException{
	 
	  
	   String Username;
	   String databasePassword;
	   map.put(username, password);
	  
	   		
			Connection conn = null;
		      Statement stmt = null;
		      
			try {
				Class.forName("com.mysql.jdbc.Driver");
				 
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			     // System.out.println(name+password);

			      // stmt = conn.createStatement();
			       PreparedStatement updateemp = conn.prepareStatement("Insert into user_details values(?,?,?,?,?,?,?,?)");
		    	    //  updateemp.setInt(3,0000);
		    	      updateemp.setString(1,name);
		    	      updateemp.setString(2, password);
		    	      updateemp.setString(4,question);
		    	      updateemp.setInt(6,Integer.parseInt(contact));
		    	      updateemp.setString(5,answer);
		    	      updateemp.setInt(3, Integer.parseInt(userid));
		    	      updateemp.setInt(8,0);
		    	      updateemp.setString(7,username);
		    	      updateemp.executeUpdate();    
			    
			    	     

			}
			catch (Exception e) {
				System.out.println("ExceptionReg is " + e);
			}
			 
		
}

public int CheckPassword(String s1, String s2) throws SQLException {
	String databasePassword;
	String Username;
	int isPasswordOk=0,userid=-1;
	Connection conn = null;
      Statement stmt = null;
      /*if(map.get(s1)==s2)
      {
    	  isPasswordOk=1;
      }*/
	try {
		  Class.forName("com.mysql.jdbc.Driver");
		 
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	     // System.out.println(name+password);

	       stmt = conn.createStatement();
	    String SQL = "SELECT * FROM user_details ;";

	    ResultSet rs = stmt.executeQuery(SQL);
	    
	            // Check Username and Password
	    while (rs.next()) {
	    	Username = rs.getString("username");
		         databasePassword = rs.getString("passowrd");
		      System.out.println(Username+" "+databasePassword+s1+s2);      
	    if (s1.equalsIgnoreCase(Username) && s2.equals(databasePassword)) 
	    	{
	    		isPasswordOk=1;
	    		userid=rs.getInt("userID");
	    		
	    		break;
	    	} 
	    }
	    
	    if(isPasswordOk==1)
	    {
	    	System.out.println("Successful Login!\n----");
	    	isPasswordOk=0;
	    	
	    	
	    	
	    }else
	    {
	    	System.out.println("Login Failed!\n----");
	    	
	    	
	    }
	   rs.close();
	   
}
	catch (Exception e) {
		System.out.println("ExceptionPassword is " + e);
	}
	conn .close();
	  return userid;
}
public ResultSet getUserDetails(int user) throws SQLException{
	Connection conn = null;
    Statement stmt = null;
    conn = DriverManager.getConnection(DB_URL,USER,PASS);
	stmt=conn.createStatement();
	String SQL = "SELECT * FROM user_details WHERE userID = " + String.valueOf(user);
	ResultSet rs = stmt.executeQuery(SQL);
	//conn .close();
	return rs;
}
public int buyCredits(int credits,int user) throws SQLException {
	Connection conn=null;
	Statement stmt=null;
	try{
		
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		
		String sql=" Update user_details set credits = " + String.valueOf(credits) + " where userID = " + user;
		stmt.executeUpdate(sql);
		//lblCredit.setText(String.valueOf(credits));
		
	}catch(SQLException se)
	{
		se.printStackTrace();
	}
	//conn .close();
	return user;
}
public ResultSet getItemList() throws SQLException{
	Connection conn = null;
    Statement stmt = null;
    conn = DriverManager.getConnection(DB_URL,USER,PASS);
    stmt = conn.createStatement();
    String SQL = "SELECT * FROM item ;";
    ResultSet rs = stmt.executeQuery(SQL);
    //conn .close();
	return rs;
}
public int  addItem(String name,String description,String modelID,String status,int auctionPrice,int sellingPrice,int id,int userID ) throws SQLException{
	 
	  
	  String Username;
	   String databasePassword;
	
	  
	
			Connection conn = null;
		      Statement stmt = null;
		      
			try {
				Class.forName("com.mysql.jdbc.Driver");
				 
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      		  PreparedStatement updateemp = conn.prepareStatement("insert into item values(?,?,?,?,?,?,?,?,?)");
	      		  updateemp.setString(1,name);
	    	      updateemp.setString(2,description);
	    	      updateemp.setString(3,modelID);
	    	      updateemp.setString(4,status);
	    	      updateemp.setInt(5,auctionPrice);
	    	      updateemp.setInt(6,sellingPrice);
	    	      updateemp.setInt(7,id);
	    	      
	    	      updateemp.setInt(8,userID);
	    	      updateemp.setInt(9,userID);
	    	      updateemp.executeUpdate();
	    	 	 stmt=conn.createStatement();
	    	     String SQL="select * from item where name = '"+name+"'";
	    	     
	  			ResultSet rs = stmt.executeQuery(SQL);
	  			rs.next();
	  			id =rs.getInt("id");
	  			System.out.println("mysql item "+id);

	}
	catch (Exception e) {
		System.out.println("ExceptionRegItem is " + e);
	}
			  return id;
			
			//conn .close();
		
}
public int checkBid(int bid,int itemID,int buyerID){
	Connection conn=null;
	Statement stmt=null;
	try{
		
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		
		 stmt=conn.createStatement();
	 String SQL = "SELECT * FROM item WHERE id = " + String.valueOf(itemID);
	 String SQL1 = "SELECT * FROM user_details WHERE userID = " + String.valueOf(buyerID);

	 ResultSet rs=stmt.executeQuery(SQL);
	 rs.next();
	 String status=rs.getString("status");
	 ResultSet rs1=stmt.executeQuery(SQL1);
	 rs1.next();
	 int creds=rs1.getInt("credits");
	 System.out.println("bid is: "+ bid);
	 System.out.println("creds is: " + creds);
	 
	 if(creds>=bid && status.startsWith("available"))
	 {
		 return 1;
	 }
		 
	 else {
		 JOptionPane.showMessageDialog(null, "Low on credits ! Please Add credits!");
		 return 0;
	 }
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return 0;
}
public void setBid(int bid,int itemID,int buyerID){
	Connection conn=null;
	Statement stmt=null;
	try{
		
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		
	 stmt=conn.createStatement();
	 stmt=conn.createStatement();
		String SQL="select * from item where id = "+itemID;
		ResultSet rs = stmt.executeQuery(SQL);
		rs.next();
		int sellingPrice=rs.getInt("sellingPrice");
		int auctionPrice=rs.getInt("auctionPrice");
		if(bid>sellingPrice && bid>auctionPrice)
		{
			String SQL2="UPDATE item SET sellingPrice = "+String.valueOf(bid)+" , buyerID = "+ String.valueOf(buyerID)+ " WHERE id = "+String.valueOf(itemID);
			 stmt.executeUpdate(SQL2);
			 JOptionPane.showMessageDialog(null, "Bid Successful!");
		}else
		{
			JOptionPane.showMessageDialog(null, "Please Bid a higher amount!");
		}
	} catch (Exception ee) {
		ee.printStackTrace();
	}
	
}
public void setStatus(int id){
	Connection conn=null;
	Statement stmt=null;
	Statement stmt1=null;

	try{
		
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
		stmt1=conn.createStatement();
		stmt=conn.createStatement();
	
		String SQL="select * from item where id = "+id;
		ResultSet rs = stmt.executeQuery(SQL);
		rs.next();
		int seller=rs.getInt("sellerID");
		int buyer=rs.getInt("buyerID");
		int cdt=rs.getInt("sellingPrice");
		String SQL1="UPDATE user_details SET credits = credits - " + cdt + " where userID = " + buyer;
		stmt1.executeUpdate(SQL1);
		String SQLL="UPDATE user_details SET credits = credits + " + cdt + " where userID = " + seller;
		stmt.executeUpdate(SQLL);
		//System.out.println(seller+" "+buyer);
		//String S = "select * from item where "
		if(buyer != seller)
		{
			String SQL2="UPDATE item SET status = 'Sold'  WHERE id = "+id+";";
			stmt.executeUpdate(SQL2);
			JOptionPane.showMessageDialog(null, "Item "+id+" succesfully sold to buyer ID "+buyer);
		}

		else
		{
			String SQL2="UPDATE item SET status = 'Not sold'  WHERE id = "+id+";";
			stmt.executeUpdate(SQL2);
			JOptionPane.showMessageDialog(null, "Item "+id+" succesfully sold to buyer ID "+buyer);
		}
		/*ResultSet rs = stmt.executeQuery(SQL);
		rs.next();
		int sellingPrice=rs.getInt("sellingPrice");
		if(bid>sellingPrice)
		{
			String SQL2="UPDATE item SET sellingPrice = "+String.valueOf(bid)+" , buyerID = "+ String.valueOf(buyerID)+ " WHERE id = "+String.valueOf(itemID);
			 stmt.executeUpdate(SQL2);
			 JOptionPane.showMessageDialog(null, "Bid Successful!");
		}else
		{
			JOptionPane.showMessageDialog(null, "Please Bid a higher amount!");
		}*/
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	
	}

	public void setPassword(String pass,int userID)
	{
		Connection conn=null;
		Statement stmt=null;
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			stmt=conn.createStatement();
			String SQL3="UPDATE user_details SET passowrd = '" + pass + "' WHERE userID = "+userID+";";
			stmt.executeUpdate(SQL3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		
	}
}
