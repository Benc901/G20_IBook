package Mains;

import Views.LoginUI;
import Views.MainUI;


/**
 * The main class of IBook client 
 *
 */
public class Main {
	
	public static String host = "";
	
	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	
	public static void main(String[] args) {
		int port = 0;
		try
	    {
	      host = args[0];
	    }
	    catch(ArrayIndexOutOfBoundsException e)
	    {
	      host = "localhost";
	    }
		MainUI main=new MainUI();//Create the Main JFrame
		LoginUI frame = new LoginUI(host,DEFAULT_PORT);//Create the login frame
		main.setView(frame);//Set the login frame inside
		
		
	}

}
