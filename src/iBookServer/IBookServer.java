package iBookServer;

import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Entities.BookET;
import Entities.ReviewET;
import Entities.UserET;
<<<<<<< HEAD
import iBookServer.serverUI;
import ocsf.server.*;
=======
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
>>>>>>> refs/heads/Gidi_Final



/**
 * <b>GHeathServer</b> - the server side main class. responsible of handle the 
 *<br> communication between the DB, through the JDBC driver, and the client side.
 *<br><br>
 *{@code extends AbstractServer} - from OCSF framework
 *
 * @author G11
 *@see ocsf.server.AbstractServer
 */
public class IBookServer extends AbstractServer {
	
	/**
	 * The UI farme instance for the server.
	 */
	private static IBookServer server;
	private static serverUI window;
	public static String IP;
	
	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;
	
	/**
	 * Register to the JDBC driver and initial sqlCon 
	 */
	static mysqlConnection sqlCon;

	/**
	 * <b>Constructor</b> - Initialize the server
	 * 
	 * @param port
	 */
	public IBookServer(int port) {
		super(port);
	}
	@Override
	protected void handleMessageFromClient(Object obj, ConnectionToClient client){
		
		HashMap<String, Object> returnObj = new HashMap<String, Object>(); 
		
		Map<String, Object> map = (HashMap<String, Object>) obj; 
		String op = (String) map.get("op");
		
		switch (op) {
			case "Login": 
				display(" : " + op, client);
				returnObj.put("op", "Login");
				returnObj.put("obj", sqlCon.login(map.get("obj")));
				break;
			case "RecoverPassword":
				display(" : " + op, client);
				returnObj.put("op", "RecoverPassword");
				returnObj.put("obj", sqlCon.RecoverPassword(map.get("obj")));				
				break;	
			case "Logout":
            	display(" : " + op, client);
				returnObj.put("op", "Logout");
				returnObj.put("obj", sqlCon.logout(map.get("obj")));
				break;	
			case "SearchBook": 
				display(" : " + op, client);
				returnObj.put("op", "SearchBook");
				returnObj.put("arr", sqlCon.SearchBook((String)map.get("text"),(ArrayList<Integer>)map.get("cb")));
				break;
			case "SearchReview": 
				display(" : " + op, client);
				returnObj.put("op", "SearchReview");
				returnObj.put("arr", sqlCon.SearchReview((String)map.get("text"),(String)map.get("cb")));
				break;	
			case "EnablePayment":
				display(" : " + op, client);
				returnObj.put("op", "EnablePayment");
				returnObj.put("rdr", sqlCon.EnablePayment((ArrayList<Object>)map.get("obj"),(UserET)map.get("us")));
				break;
			case "GetBook":
				display(" : " + op, client);
				returnObj.put("op", "GetBook");
				returnObj.put("obj", sqlCon.GetBook((UserET)map.get("user"),(BookET)map.get("book")));
				break;
			case "BookList":
				display(" : " + op, client);
				returnObj.put("op", "BookList");
				returnObj.put("obj", sqlCon.BookList((int)map.get("us")));
				break;
			case "CheckApplication":
				display(" : " + op, client);
				returnObj.put("op", "CheckApplication");
				returnObj.put("obj", sqlCon.CheckApplication((int)map.get("us")));
				break;		
			case "PublishReview":
				display(" : " + op, client);
				returnObj.put("op", "PublishReview");
				returnObj.put("obj", sqlCon.PublishReview((ReviewET)map.get("obj")));
				break;	
			case "AddUser":
				display(" : " + op, client);
				returnObj.put("op", "AddUser");
				returnObj.put("obj", sqlCon.AddUser(map.get("obj")));	
				break;
			case "HideBook":
				display(" : " + op, client);
				returnObj.put("op", "HideBook");
				returnObj.put("obj", sqlCon.HideBook((String)map.get("obj"),(int)map.get("hide")));	
				break;	
			case "FreezeUser":
				display(" : " + op, client);
				returnObj.put("op", "FreezeUser");
				returnObj.put("obj", sqlCon.FreezeUser((String)map.get("obj"),(int)map.get("freeze")));	
				break;	
			case "ChangePermission":
				display(" : " + op, client);
				returnObj.put("op", "ChangePermission");
				returnObj.put("obj", sqlCon.ChangePermission((String)map.get("obj"),(int)map.get("newPer")));	
				break;	
<<<<<<< HEAD
			case "SearchAdv":
				display(" : " + op, client);
				returnObj.put("op", "SearchAdv");
				returnObj.put("arr", sqlCon.SearchAdv((ArrayList<String>)map.get("obj")));	
				break;	
			case "BringBook":
				display(" : " + op, client);
				returnObj.put("op", "BringBook");
				returnObj.put("obj", sqlCon.BringBook((int)map.get("obj")));
				break;
			case "UpdateBook":
				display(" : " + op, client);
				returnObj.put("op", "UpdateBook");
				returnObj.put("obj", sqlCon.UpdateBook((BookET)map.get("obj")));
				break;
			case "BringGandS":
				display(" : " + op, client);
				returnObj.put("op", "BringGandS");
				returnObj.put("obj", sqlCon.BringGandS());
				break;
			case "ViewBook":
				display(" : " + op, client);
				returnObj.put("op", "ViewBook");
				returnObj.put("obj", sqlCon.ViewBook((int)map.get("obj")));
				break;
			case "AddBook":
				display(" : " + op, client);
				returnObj.put("op", "AddBook");
				returnObj.put("obj", sqlCon.AddBook((BookET)map.get("obj")));
				break;
			case "GetPaymentList":
				display(" : " + op, client);
				returnObj.put("op", "GetPaymentList");
				returnObj.put("obj", sqlCon.GetPaymentList());
				break;
			case "pConfirm":
				display(" : " + op, client);
				returnObj.put("op", "pConfirm");
				returnObj.put("obj", sqlCon.pConfirm((int)map.get("id"),(int)map.get("confirm")));
				break;	
			case "GetReviewList":
				display(" : " + op, client);
				returnObj.put("op", "GetReviewList");
				returnObj.put("obj", sqlCon.GetReviewList());
				break;
			case "rConfirm":
				display(" : " + op, client);
				returnObj.put("op", "rConfirm");
				returnObj.put("obj", sqlCon.rConfirm((int)map.get("id"),(int)map.get("confirm")));
				break;
			case "EditReview":
				display(" : " + op, client);
				returnObj.put("op", "EditReview");
				returnObj.put("obj", sqlCon.EditReview((int)map.get("id"),(String)map.get("review")));
				break;
=======
			case "UserReport":
				display(" : " + op, client);
				returnObj.put("op", "UserReport");
				returnObj.put("obj", sqlCon.UserReport((String)map.get("obj"),(String) map.get("from"),(String) map.get("to")));	
				break;	
			case "BookReport":
				display(" : " + op, client);
				returnObj.put("op", "BookReport");
				returnObj.put("obj", sqlCon.BookReport(Integer.parseInt((String) map.get("obj")),(int)map.get("choice"),(String) map.get("from"),(String) map.get("to")));	
				break; // in obj we have the hashmap returned from BookReport in mysqlConnection
			case "BookRank":
				display(" : " + op, client);
				returnObj.put("op", "BookRank");
				returnObj.put("obj", sqlCon.BookRank(Integer.parseInt((String) map.get("obj")),(int)map.get("choice")));	
				break;	
>>>>>>> refs/heads/Gidi_Final
		}
		
		try {
			client.sendToClient(returnObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		int port = 0;      // Port to listen on
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		try {
			port = Integer.parseInt(args[0]);     // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT;    // Set port to 5555
		}
	
		
		IBookServer sv = new IBookServer(port);
		window= new serverUI(sv);
		server=sv;
		StartServer(5555);
		
		sqlCon = new mysqlConnection(window,"root","1234");
		
		window.setVisible(true);
		/*try {
			sv.listen(); // Start listening for connections 
		} catch (Exception ex) {
			display("ERROR - Could not listen for clients!", null);
		}*/
	}

	
	public static void StartServer(int port){
		
		
		try {
			server.listen();
		} catch (IOException e) {
			System.out.println("ERROR - Could not listen for clients!");
		}
		if(server.isListening()){
			  System.out.println("Listenning on port "+ port);
			  window.label_2.setText("Online");
			  window.label_2.setForeground(Color.GREEN);
		}else {
			window.label_2.setText("Offline");
			  window.label_2.setForeground(Color.RED);
		}
		
		try {IP = "" + InetAddress.getLocalHost().getHostAddress();} 
		catch (UnknownHostException e) {e.printStackTrace();}
		window.lblIp.setText(IP);
	}
	
public static void StopServer(){
		
		boolean catched = false;
		
		try {
			server.close();
		} catch (IOException e) {
			System.err.println("Cant close connection - StopServer");
			catched=true;
		}
		if(!catched){
			window.label_2.setText("Offline");
			  window.label_2.setForeground(Color.RED);
		}else{
			window.label_2.setText("Online");
			  window.label_2.setForeground(Color.GREEN);
		}
		
		
	}
	

public static void ConnectToSQL(String username,String password){
	try {

		sqlCon = new mysqlConnection(window,username,password);
		

	}catch (Exception ex){
		//System.err.println("Sql Connection error");
		}// exception thrown in the constructor if needed.
	
		
}

	private static void display(String message, ConnectionToClient client) {
		window.display(message, client);
		
	}
	
	
	
}// End of EchoServer class
