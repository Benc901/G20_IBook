package Mains;


import java.io.IOException;

import javax.swing.JOptionPane;

import ocsf.client.ObservableClient;


public class IBookClient extends ObservableClient {
	
	private static IBookClient firstInstance = null;
	/**
	 * constructor for connection with server
	 * @param host
	 * @param port
	 */
	private IBookClient(String host, int port) {
		super(host, port);
		try {
			openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**function that check if the static instance of IBookClient is null
	 * if true create new instance and save it on the static field
	 * if false return the static instance
	 * @param host - ip to connect
	 * @param port - port foe connect
	 * @return IBookClient instance
	 */
	public static IBookClient getInstance(String host, int port) {
		if(firstInstance == null) {
			firstInstance = new IBookClient(host, port);
		}
		return firstInstance;
	}
	

	public void handleMessageFromUI(Object obj) {
	
		
		try {
			sendToServer(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void handleMessageFromServer(Object obj) {

		if(obj instanceof String) {
			String str = (String) obj;
			if(str.equals("Server Shutdown!!!")) {
				JOptionPane.showMessageDialog(null, str, "Server Error", JOptionPane.ERROR_MESSAGE);
				try {
					closeConnection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		setChanged();
		notifyObservers(obj);
	}

	/**function that return the IBookClient static instance if not null
	 * @return IBookClient static instance
	 */
	public static IBookClient getInstance() {
		if(firstInstance == null) {
			System.out.println("The instance of the client was not created yet"); //No reason to print that. 
																				  //This constructor is called just after the initialization. 
		} else
			return firstInstance;
		
		return null;
	}

	
	
	
}