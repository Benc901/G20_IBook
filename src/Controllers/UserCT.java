package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.UIManager.LookAndFeelInfo;

import Entities.UserET;
import Mains.IBookClient;
import Mains.Main;
import Views.LoginUI;
import Views.MainUI;
import Views.ReaderUI;
import Views.SearchBookUI;
import Views.SearchReviewUI;






public class UserCT implements Observer, ActionListener {

	public static UserCT userCT;
	public static IBookClient client;
	private static LoginUI loginFrame;
	private static ReaderUI readerFrame;
	private static UserET userET;
	private static SearchBookUI searchbookFrame;
	private static SearchReviewUI searchreviewFrame;
	public static int port;
	public  static String host;
	public static Object current;
	
	public UserCT(LoginUI frame, int port){
		userCT=this;
		this.port = port;
		loginFrame = frame;
		loginFrame.btnLogin.addActionListener((ActionListener) this);
		
		
	}
	public void changeObserver(Observer a,Observer b){
		client.addObserver(a);
		client.deleteObserver(b);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginFrame.btnLogin){
			host=loginFrame.getIP();
			client = IBookClient.getInstance(host, port);
			client.addObserver(this);
			login(loginFrame.getUsername(), loginFrame.getPassword());
		}
		if(readerFrame!=null){
			if(e.getSource()==readerFrame.btnLogout){
			logout();
			}
			if(e.getSource()==readerFrame.btnSearchBook){
			searchbookFrame=new SearchBookUI();
			searchbookFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(searchbookFrame);
			}
			if(e.getSource()==readerFrame.btnSearchReview){
			searchreviewFrame=new SearchReviewUI();
			searchreviewFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(searchreviewFrame);
			}
		}
		if(searchbookFrame!=null){
			if(e.getSource()==searchbookFrame.btnBack){
				changeObserver(this,BookCT.bookCT);
				MainUI.MV.setView(readerFrame);
			}
			
		}
		if(searchreviewFrame!=null){
			if(e.getSource()==searchreviewFrame.btnBack){
				changeObserver(this,ReviewCT.reviewCT);
				MainUI.MV.setView(readerFrame);
			}
		}
		
	}

	@Override
	public void update(Observable o, Object obj) {
		if (obj instanceof String)
			System.out.println(obj);

		else {
			Map<String, Object> map = (HashMap<String, Object>) obj;

			String op = (String) map.get("op");
			
			// what operation was made in the server and how to respond.
			switch (op) {
			case "Login":
				
				if (map.get("obj") == null) {
					JOptionPane.showMessageDialog(null, "User not exist",
							"User Not Found", JOptionPane.ERROR_MESSAGE);
					loginFrame.clearFields();
					
				}

				else if (map.get("obj").equals("1")) {
					JOptionPane.showMessageDialog(null,
							"User is already logged in the system - please contact system admin",
							"User Is Logged", JOptionPane.WARNING_MESSAGE);
					loginFrame.clearFields();
				}

				else if (map.get("obj").equals("3")) {
					JOptionPane.showMessageDialog(null,
							"Username or password are incorrect - please renter your details OR contact system admin",
							"User Details Incorrect", JOptionPane.WARNING_MESSAGE);
					loginFrame.clearFields();
				}
				
				else
				{
					userET = (UserET) map.get("obj");
					//loginFrame.setVisible(false);
					// Build the GUI depends on the permission.

							readerFrame=new ReaderUI(userET);
							readerFrame.btnLogout.addActionListener((ActionListener) this);
							readerFrame.btnSearchBook.addActionListener((ActionListener) this);
							readerFrame.btnSearchReview.addActionListener((ActionListener) this);
							MainUI.MV.setView(readerFrame);
							
				}
				break;

			case "Logout":
				if (map.get("obj").equals("User logged out of the system"))
					JOptionPane.showMessageDialog(null,
							"User is logged out from the system",
							"User Is LoggedOut", JOptionPane.WARNING_MESSAGE);
				MainUI.MV.setView(loginFrame);
				loginFrame.clearFields();
				break;
			default : 
				System.out.println("problem here");
		}//end switch case
		}//end else
		
	}
	
	public void login(String userName, String passText) {
		if (Metds.isUserValid(userName) && Metds.isPasswordValid(passText)) {
	
			
			userET = new UserET(userName, passText);

			Map<String, Object> hmap = new HashMap<String, Object>();
			hmap.put("op", "Login");
			hmap.put("obj", userET);

			client.handleMessageFromUI(hmap);
		} else {
			loginFrame.clearFields();
		}
	

	}
	
	public static void logout()
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "Logout");
		hmap.put("obj", userET);

		client.handleMessageFromUI(hmap);
	}

}