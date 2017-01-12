package iBookServer;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Entities.BookET;
import Entities.ReviewET;
import Entities.UserET;
import ocsf.server.ConnectionToClient;

public class mysqlConnection {
	Connection con;
	private serverUI window;
	//final private String url = "jdbc:mysql://localhost/database";
	//final private String username = "root";
	//final private String password = "1234";
	final private String url = "jdbc:mysql://localhost:3306/test";
	final private String username;
	final private String password;
	
	
	/**
	 * Constructor
	 */
	public mysqlConnection(serverUI window,String un,String pw) {
		username=new String(un);
		password=new String(pw);
		boolean catched=false;
		this.window = window;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();//Register for the jdbc driver.
		} catch (Exception ex) {/* handle the error */
		}

		try {
			con = DriverManager.getConnection(url, username, password);
			display("SQL Server connecton created.");
		} catch (SQLException ex) {/* handle any errors */
			display("SQLException: " + ex.getMessage());
			catched=true;
		}
		if(!catched){
			
			  window.lblSQL.setText("Online");
			  window.lblSQL.setForeground(Color.GREEN);
		}else{
			window.lblSQL.setText("Offline");
			  window.lblSQL.setForeground(Color.RED);
		}
	}
	
public Object login(Object obj) {
		
		UserET returnObj = (UserET) obj;
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM user WHERE userName = ? AND passWord = ?");
			pStmt.setString(1, returnObj.getUserName());
			pStmt.setString(2, returnObj.getPassWord());
			ResultSet rs = pStmt.executeQuery();

			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No user
										// found).
				display("no user in the ResultSet of login.");
				return "3";

			} else {
				pStmt = con
						.prepareStatement("SELECT * FROM user WHERE userName = ? AND passWord = ? AND status = 0");
				pStmt.setString(1, returnObj.getUserName());
				pStmt.setString(2, returnObj.getPassWord());
				rs = pStmt.executeQuery();

				if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
											// user found).
					display("user is already logged in the system.");
					return "1";
				}

				else if (rs.next()) { // Build the entity and return it to the
							returnObj.SetFromSQL(rs.getInt(1),rs.getInt(4),rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9));
										// server and then to the client.
					rs.close();
					
					PreparedStatement rStmt = con
							.prepareStatement("UPDATE user SET status = 1 WHERE userName = ?");
					rStmt.setString(1, returnObj.getUserName());
					rStmt.executeUpdate();

					
					return returnObj;
				}
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}// End login	
	
public Object logout(Object obj) {
	UserET returnObj = (UserET) obj;
	try {
		PreparedStatement rStmt = con
				.prepareStatement("UPDATE user SET status = 0 WHERE userName = ?");
		rStmt.setString(1, returnObj.getUserName());
		rStmt.executeUpdate();
		rStmt.close();
		display(returnObj.getUserName() + " logged out from the system.");
		return "User logged out of the system";
	} catch (SQLException e) {
		e.printStackTrace();

	}
	return null;
	
}//End logout

	public Object SearchBook(String text,ArrayList<Integer> cb){
		ArrayList<BookET> returnObj=new ArrayList<BookET>();
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM books");
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
				// user found).
				display("No Results");
				return "1";
			}
			else{
				while(rs.next()){
					for(int i=0;i<cb.size();i++){
						if((rs.getString(cb.get(i)).toLowerCase()).contains(text.toLowerCase())){
							i=cb.size()-1;
							returnObj.add(
								new BookET(rs.getInt(1),
										rs.getString(2),rs.getString(3),
										rs.getString(4),rs.getString(5),
										rs.getString(6),rs.getString(7),
										rs.getString(8),rs.getString(9),
										rs.getString(10),rs.getInt(11),
										rs.getInt(12),rs.getInt(13),
										rs.getInt(14),rs.getInt(15)
								));
						}
				}}
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	public Object SearchReview(String text,String cb){
		ArrayList<ReviewET> returnObj=new ArrayList<ReviewET>();
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM review");
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
				// user found).
				display("No Results");
				return "1";
			}
			else{
				while(rs.next()){
					if((rs.getString(Integer.parseInt(cb)).toLowerCase()).contains(text.toLowerCase())){
						returnObj.add(
								new ReviewET(rs.getInt(1),
										rs.getInt(2),rs.getString(3),
										rs.getString(4),rs.getInt(5),
										rs.getString(6),rs.getString(7),
										rs.getString(8), rs.getInt(9),
										rs.getInt(10)	,rs.getInt(11)
								));
				}}
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnObj;
	}	
	
	public boolean EnablePayment(ArrayList<Object> details,UserET user){
	
		try {
			Statement  rStmt = con.createStatement();
			rStmt.executeUpdate("INSERT INTO reader VALUES ("+user.getId()+","
															 +details.get(0)+","
															+"\""+details.get(1)+"\""+","
															+"\""+details.get(2)+"\""+","
															+"\""+details.get(3)+"\""+","
															+"\""+details.get(4)+"\""+","
															+"\""+details.get(5)+"\""+","
															+0+","+details.get(6));
			display(" User sent enable payment");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public void closeSqlConnection(){

		

		boolean catched=false;
		// crashes if the connection is close when method starts
		// from http://stackoverflow.com/questions/2225221/closing-database-connections-in-java
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			System.out.println("SQL Connection closed");
			catched=true;
		}
		
		if(!catched){
			  window.lblSQL.setText("Offline");
			  window.lblSQL.setForeground(Color.RED);
		}else{
			window.lblSQL.setText("Online");
			  window.lblSQL.setForeground(Color.GREEN);
		}
	}
	public int AddUser(Object obj) {
		UserET user = (UserET) obj;
		try
		{
			Statement pStmt = con.createStatement();
			pStmt.execute("SELECT MAX(id) FROM user");
			ResultSet rs = pStmt.getResultSet();
			int id=0;
			if(rs.next())
				id=(int) rs.getObject(1);
			id+=1;
			display(Integer.toString(id));
			PreparedStatement rStmt = con.prepareStatement("SELECT * FROM user WHERE userName = ?");
			rStmt.setString(1, user.getUserName());
			rs = rStmt.executeQuery();
			if (!rs.isBeforeFirst()) 
			{
				/*
				String query = "INSERT INTO Users ("
					    + " id,"
					    + " userName,"
					    + " passWord,"
					    + " permission,"
					    + " status,"
					    + " firstName,"
					    + " lastName,"
					    + " email,"
					    + " photo ) VALUES ("
					    + "?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				
				rStmt =  con.prepareStatement(query);
				rStmt.setInt(1, id);
				rStmt.setString(2, user.getUserName());
				rStmt.setString(3, user.getPassWord());
				rStmt.setInt(4, 1);
				rStmt.setInt(5, 0);
				rStmt.setString(6, user.getFirstName());
				rStmt.setString(7, user.getLastName());
				rStmt.setString(8, user.getEmail());
				rStmt.setString(9, user.getFirstName());
				rStmt.executeUpdate();*/
				
				display(" User insert to DB");
				/*display("INSERT INTO Users ("
					    + " id,"
					    + " userName,"
					    + " passWord,"
					    + " permission,"
					    + " status,"
					    + " firstName,"
					    + " lastName,"
					    + " email,"
					    + " photo ) VALUES ("
					    + "?, ?, ?, ?, ?, ?, ?, ?, ?)");*/
				return 1;
			}
			display("The UserName already in the DB.");
			return 0;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
	}


	
private void display(String message) {
	window.display(message);
	
}

}


