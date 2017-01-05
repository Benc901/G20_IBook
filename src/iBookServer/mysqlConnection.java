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
							returnObj.SetFromSQL(rs.getInt(1),rs.getInt(4),rs.getString(6), rs.getString(7),rs.getString(8));
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
	


	
private void display(String message) {
	window.display(message);
	
}

}


