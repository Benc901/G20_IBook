package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.UIManager.LookAndFeelInfo;

import Entities.ReaderET;
import Entities.UserET;
import Mains.IBookClient;
import Mains.Main;
import Views.EnablePaymentUI;
import Views.LibririanUI;
import Views.LoginUI;
import Views.MainUI;
import Views.ManagerUI;
import Views.PublishReviewUI;
import Views.ReaderUI;
import Views.SearchBookUI;
import Views.SearchReviewUI;






public class UserCT implements Observer, ActionListener {

	public static UserCT userCT;
	public static IBookClient client;
	private static LoginUI loginFrame;
	public static ReaderUI readerFrame;
	public static UserET userET;
	public static ReaderET readerET;
	private static SearchBookUI searchbookFrame;
	private static SearchReviewUI searchreviewFrame;
	private static PublishReviewUI publishreviewFrame;
	private static EnablePaymentUI enablepaymentFrame;
	private static LibririanUI libririanFrame;
	private static ManagerUI managerFrame;
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
			if(e.getSource()==readerFrame.btnEnablePublish){
				if(readerFrame.btnEnablePublish.getText()=="Publish review"){
					publishreviewFrame=new PublishReviewUI();
					publishreviewFrame.btnBack.addActionListener((ActionListener)this);
					MainUI.MV.setView(publishreviewFrame);
				}
				else if(readerFrame.btnEnablePublish.getText()=="Enable payment"){
					enablepaymentFrame=new EnablePaymentUI();
					enablepaymentFrame.btnSendApplication.addActionListener((ActionListener)this);
					enablepaymentFrame.btnBack.addActionListener((ActionListener)this);
					MainUI.MV.setView(enablepaymentFrame);
				}
			}
			if(e.getSource()==readerFrame.wbtnLibririan){
				libririanFrame=new LibririanUI();
				libririanFrame.btnBack.addActionListener((ActionListener)this);
				MainUI.MV.setView(libririanFrame);
			}
			 
			if(e.getSource()==readerFrame.wbtnManagerMenu){
				managerFrame=new ManagerUI();
				managerFrame.btnBack.addActionListener((ActionListener)this);
				MainUI.MV.setView(managerFrame);
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
		if(publishreviewFrame!=null){
			if(e.getSource()==publishreviewFrame.btnBack){
				//changeObserver(this,ReviewCT.reviewCT);
				MainUI.MV.setView(readerFrame);
			}
		}
		if(enablepaymentFrame!=null){
			if(e.getSource()==enablepaymentFrame.btnBack){
				//changeObserver(this,ReviewCT.reviewCT);
				MainUI.MV.setView(readerFrame);
			}
			if(e.getSource()==enablepaymentFrame.btnSendApplication){
				enablePayment();
				MainUI.MV.setView(readerFrame);
			}
		}
		if(libririanFrame!=null){
			if(e.getSource()==libririanFrame.btnBack){
				//changeObserver(this,ReviewCT.reviewCT);
				MainUI.MV.setView(readerFrame);
			}}
			if(managerFrame!=null){
				if(e.getSource()==managerFrame.btnBack){
					//changeObserver(this,ReviewCT.reviewCT);
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
							readerFrame.btnEnablePublish.addActionListener((ActionListener) this);
							if(userET.getPermission()==3|userET.getPermission()==4)
								readerFrame.wbtnLibririan.addActionListener((ActionListener) this);
							if(userET.getPermission()==4)
								readerFrame.wbtnManagerMenu.addActionListener((ActionListener) this);
							
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
			case "EnablePayment": { if((int)map.get("rdr")==1)
										JOptionPane.showMessageDialog(null,"Request successful");
									else JOptionPane.showMessageDialog(null,"Request failed","Request failed", JOptionPane.ERROR_MESSAGE);
			
				
								//	readerET=(ReaderET)map.get("rdr");
									break;}
				
			default : System.out.println("problem here");
				
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
	
	public void enablePayment(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		ArrayList<Object> obj=new ArrayList<Object>();
		int sub=enablepaymentFrame.cbPmethod.getSelectedIndex();
		int bookLeft=0;
		if(sub==1) bookLeft=10;
		if(sub==2) bookLeft=30;
		obj.add((int)sub);
		obj.add((String)enablepaymentFrame.tfCnumber.getText());
		obj.add((String)enablepaymentFrame.cbMvalid.getSelectedItem());
		obj.add((String)enablepaymentFrame.cbYvalid.getSelectedItem());
		obj.add((String)enablepaymentFrame.tfCvv.getText());
		obj.add((String)enablepaymentFrame.tfId.getText());
		obj.add((int)bookLeft);
		hmap.put("op", "EnablePayment");
		hmap.put("us", userET);
		hmap.put("obj", obj);
		client.handleMessageFromUI(hmap);
	}

}
