package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import Entities.ReaderET;
import Entities.UserET;
import Entities.FileEvent;
import Mains.IBookClient;
import Views.EnablePaymentUI;
import Views.LibririanUI;
import Views.LoginUI;
import Views.MainUI;
import Views.ManagerUI;
import Views.PublishReviewUI;
import Views.ReaderUI;
import Views.RecoverPasswordUI;
import Views.RenewSubUI;
import Views.SearchAdvUI;
import Views.SearchBookUI;
import Views.SearchReviewUI;
import Controllers.SendEmail;





/**
 * Class that control all the basics actions of any kind of users.
 *
 */
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
	private static RecoverPasswordUI recoverpasswordFrame;
	private static ManagerUI managerFrame;
	public static int port;
	public  static String host;
	public static Object current;
	public static RenewSubUI renewFrame;
	
	
	/**
	 * Constructor
	 *  Build a controller that initialize
	 * Add ActionListener to every button in every panels of the login frame
	 * Get the connection to the server 
	 * @param frame - Get the JPanel of login UI
	 * @param port - Get the port for the server connection
	 */
	public UserCT(LoginUI frame, int port){
		userCT=this;
		this.port = port;
		loginFrame = frame;
		loginFrame.btnLogin.addActionListener((ActionListener) this);
		loginFrame.btnForgot.addActionListener((ActionListener) this);
		
	}
	/**
	 * Function that change the observer to other controller
	 * @param a - The controller that will get the observer
	 * @param b - The controller that will loose the observer
	 */
	public void changeObserver(Observer a,Observer b){
		client.addObserver(a);
		client.deleteObserver(b);
	}
	/* 
	 * Function the recognize events from all the User UI
	 * do the action that the event needs and send to the relevant function
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginFrame.btnLogin){ //try to login to the system with the user details
			host=loginFrame.getIP();
			client = IBookClient.getInstance(host, port);
			client.addObserver(this);
			login(loginFrame.getUsername(), loginFrame.getPassword());
		}
		if (e.getSource() == loginFrame.btnForgot){//create recover password frame
			host=loginFrame.getIP();
			client = IBookClient.getInstance(host, port);
			client.addObserver(this);
			recoverpasswordFrame=new RecoverPasswordUI();
			recoverpasswordFrame.btnSend.addActionListener((ActionListener)this);
			recoverpasswordFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(recoverpasswordFrame);
		}
		if (recoverpasswordFrame!=null){
			if(e.getSource()==recoverpasswordFrame.btnBack)
				MainUI.MV.setView(loginFrame);
			if(e.getSource()==recoverpasswordFrame.btnSend)//try to recover password and send to user's email
				recover();
		}
		if(readerFrame!=null){
			if(e.getSource()==readerFrame.btnLogout){//try to logout from the system
			logout();
			}
			if(e.getSource()==readerFrame.btnSearchBook){//create the search book frame
			searchbookFrame=new SearchBookUI();
			searchbookFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(searchbookFrame);
			}
			if(e.getSource()==readerFrame.btnSearchReview){//create the search review frame
			searchreviewFrame=new SearchReviewUI();
			searchreviewFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(searchreviewFrame);
			}
			if(e.getSource()==readerFrame.btnEnablePublish){
				if(readerFrame.btnEnablePublish.getText()=="Publish review"){ //create the publish review frame
					BookList();
				}
				else if(readerFrame.btnEnablePublish.getText()=="Enable payment"){//create the enable payment frame
					CheckApplication();
				}
			}
			if(e.getSource()==readerFrame.wbtnLibririan){//create the librarian frame
				libririanFrame=new LibririanUI();
				libririanFrame.btnBack.addActionListener((ActionListener)this);
				MainUI.MV.setView(libririanFrame);
			}
			 
			if(e.getSource()==readerFrame.wbtnManagerMenu){//create the manager frame
				managerFrame=new ManagerUI();
				managerFrame.btnBack.addActionListener((ActionListener)this);
				MainUI.MV.setView(managerFrame);
			}
			if(e.getSource()==readerFrame.btnRenew){//create the renew subscription frame
				if(userET.getConfirm()==0) JOptionPane.showMessageDialog(null,"Please Enable payment first");
				else{
					renewFrame=new RenewSubUI();
					renewFrame.btnBack.addActionListener((ActionListener)this);
					renewFrame.btnRenew.addActionListener((ActionListener)this);
					MainUI.MV.setView(renewFrame);
				}
			}
			
		}
		if(searchbookFrame!=null){
			if(e.getSource()==searchbookFrame.btnBack){//back to the reader frame
				changeObserver(this,BookCT.bookCT);
				MainUI.MV.setView(readerFrame);
			}
			
		}
		if(searchreviewFrame!=null){
			if(e.getSource()==searchreviewFrame.btnBack){//back to the reader frame
				changeObserver(this,ReviewCT.reviewCT);
				MainUI.MV.setView(readerFrame);
			}
		}
		if(publishreviewFrame!=null){
			if(e.getSource()==publishreviewFrame.btnBack){//back to the reader frame
				changeObserver(this,ReviewCT.reviewCT);
				MainUI.MV.setView(readerFrame);
			}
		}
		if(enablepaymentFrame!=null){
			if(e.getSource()==enablepaymentFrame.btnBack){//back to the reader frame
				//changeObserver(this,ReviewCT.reviewCT);
				MainUI.MV.setView(readerFrame);
			}
			if(e.getSource()==enablepaymentFrame.btnSendApplication){//back to the reader frame
				enablePayment();
				MainUI.MV.setView(readerFrame);
			}
		}
		if(libririanFrame!=null){
			if(e.getSource()==libririanFrame.btnBack){//back to the reader frame
				changeObserver(this,LibrarianCT.librarianCT);
				MainUI.MV.setView(readerFrame);
			}}
		if(managerFrame!=null){
				if(e.getSource()==managerFrame.btnBack){//back to the reader frame
					changeObserver(this,ManagerCT.managerCT);
					MainUI.MV.setView(readerFrame);
				}
		}
		if(renewFrame!=null){
			if(e.getSource()==renewFrame.btnBack){//back to the reader frame
				MainUI.MV.setView(readerFrame);
			}
			if(e.getSource()==renewFrame.btnRenew){//Send to renew subscription application
				RenewSub();
			}
		}
	}

	/* function that get the result from the database and recognize the result kind
	 * and set the details depending on the case
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object obj) {
		if (obj instanceof String)
			System.out.println(obj);

		else {
			Map<String, Object> map = (HashMap<String, Object>) obj;

			String op = (String) map.get("op");
			
			// what operation was made in the server and how to respond.
			switch (op) {
			case "Login":/*check the result from the database if the user name and password correct
						 *check if the user exist in the system
						 *check if the user still logged in from other pc
						 *if successful login set the reader frame by the correct permission
						 */	
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

							readerFrame=new ReaderUI(userET);
							readerFrame.btnLogout.addActionListener((ActionListener) this);
							readerFrame.btnSearchBook.addActionListener((ActionListener) this);
							readerFrame.btnSearchReview.addActionListener((ActionListener) this);
							readerFrame.btnEnablePublish.addActionListener((ActionListener) this);
							readerFrame.btnRenew.addActionListener((ActionListener) this);
							if(userET.getPermission()==3|| userET.getPermission()==4 || userET.getPermission()==8 ||userET.getPermission()==9)
								readerFrame.wbtnLibririan.addActionListener((ActionListener) this);
							if(userET.getPermission()==4 || userET.getPermission()==9)
								readerFrame.wbtnManagerMenu.addActionListener((ActionListener) this);
							MainUI.MV.setView(readerFrame);
							if(userET.getPermission()>5)
							JOptionPane.showMessageDialog(null,
									"The manager freeze your account,\n you cant get books,\n please contact with lib stuff",
									"Freeze", JOptionPane.ERROR_MESSAGE);
							if(userET.getPermission()==3|| userET.getPermission()==4 || userET.getPermission()==8 ||userET.getPermission()==9)
								CheckNotif();
				}
				break;
				
			case "RecoverPassword":{/*show the result from the database
									*check if the user name exist
									*if exist send mail with the user password
									*/
				if(map.get("obj") instanceof Integer)JOptionPane.showMessageDialog(null,"UserName is not exist");
				else{
					SendEmail.sendFromGMail("ibookserverg20@gmail.com","g20ibookserver",((UserET)map.get("obj")).getEmail(),"RecoverPassword",((UserET)map.get("obj")).getPassWord());
					JOptionPane.showMessageDialog(null,"Your password sent to : "+((UserET)map.get("obj")).getEmail());
					MainUI.MV.setView(loginFrame);
				}
				
				break;
			}

			case "Logout":/*show the message to the user that he log out from the system 
						   *set the login frame
						   */
				if (map.get("obj").equals("User logged out of the system"))
					JOptionPane.showMessageDialog(null,
							"User is logged out from the system",
							"User Is LoggedOut", JOptionPane.WARNING_MESSAGE);
				MainUI.MV.setView(loginFrame);
				loginFrame.clearFields();
				break;
			case "EnablePayment": {//show the user if the sent application success
			 						
									if((int)map.get("rdr")==1)
										JOptionPane.showMessageDialog(null,"Request successful");
									else JOptionPane.showMessageDialog(null,"Request failed","Request failed", JOptionPane.ERROR_MESSAGE);

									break;}
			case "BookList":{//get the book list of the user books purchased
								  if(((ArrayList)((HashMap) map.get("obj")).get("int")).size()==0)
														JOptionPane.showMessageDialog(null,"The book List is Empty");
				
								  else{
									  	Object[]al=((ArrayList)((HashMap) map.get("obj")).get("String")).toArray();
									  	String[]booklist=Arrays.copyOf(al,al.length,String[].class);
									    publishreviewFrame=new PublishReviewUI();
									    ReviewCT.reviewCT.booklist=((HashMap) map.get("obj"));
										publishreviewFrame.btnBack.addActionListener((ActionListener)this);
										publishreviewFrame.comboBox.setModel(new DefaultComboBoxModel(booklist));
										MainUI.MV.setView(publishreviewFrame);
								  }
								  
								  break;}
			case "CheckApplication": { //show the message of the status of payment application
									   if((int)map.get("obj")==2)
											JOptionPane.showMessageDialog(null,"Your application denied,please contact with libririan stuff");
									   else if((int)map.get("obj")==0)
											JOptionPane.showMessageDialog(null,"Your application still in process");
									   else {
										   	enablepaymentFrame=new EnablePaymentUI();
											enablepaymentFrame.btnSendApplication.addActionListener((ActionListener)this);
											enablepaymentFrame.btnBack.addActionListener((ActionListener)this);
											MainUI.MV.setView(enablepaymentFrame);
									   }
									   break;}	
			case "RenewSub": {//show the message if the renew subscription application success
									if((int)map.get("obj")==0) JOptionPane.showMessageDialog(null,"Failed,You still have books in your subscription");
									else JOptionPane.showMessageDialog(null,"Success,You got a new subscription");
									MainUI.MV.setView(readerFrame);
									break;
								}
			case "CheckNotif":{//show the message if the library worker have notifications about review confirmation application
									if((int)map.get("obj")!=0) JOptionPane.showMessageDialog(null,"You have "+(int)map.get("obj")+" reviews to check");
									break;
								}
			
			default : System.out.println("Wrong CT or add break;");
				
		}
		}
		
	}
	
	/**
	 * Function prepares the data and sent to the server
	 * about the user login action
	 * check if the fields are in the correct pattern we request
	 * @param userName - the user name that user enter
	 * @param passText - the password that user enter
	 */
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
	
	/**
	 * Function prepares the data and sent to the server
	 * about to recover password in case that user forgot his password
	 * check if the fields are empty and if not send the fields to the server
	 */
	public void recover(){
		Map<String, Object> hmap = new HashMap<String, Object>();
			if(recoverpasswordFrame.textField.getText()==""||recoverpasswordFrame.textField.getText()==null)
			JOptionPane.showMessageDialog(null,"Please fill the field");
			else{
				hmap.put("op", "RecoverPassword");
				hmap.put("obj", new UserET(recoverpasswordFrame.textField.getText(),""));
				
				client.handleMessageFromUI(hmap);
			}
		
	}
	
	/**
	 * Function prepares the data and sent to the server
	 * about that user want to logout from the system
	 */
	public static void logout()
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "Logout");
		hmap.put("obj", userET);

		client.handleMessageFromUI(hmap);
	}
	
	/**
	 * Function prepares the data and sent to the server
	 * about that user send enable payment application to the library stuff
	 * set array list with the data of the payment applications
	 */
	public void enablePayment(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		ArrayList<Object> obj=new ArrayList<Object>();
		int sub=enablepaymentFrame.cbPmethod.getSelectedIndex();
		int bookLeft=0;
		if(sub==1) bookLeft=5;
		if(sub==2) bookLeft=20;
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
	
	/**
	 * Function prepares the data and sent to the server
	 * about all the books that user purchased
	 */
	public void BookList(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "BookList");
		hmap.put("us", userET.getId());
		System.out.println("booklist-UserCT");
		client.handleMessageFromUI(hmap);
	}
	
	/**
	 * Function prepares the data and sent to the server
	 * about to check what is the status with enable payment application
	 */
	public void CheckApplication(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "CheckApplication");
		hmap.put("us", userET.getId());
		client.handleMessageFromUI(hmap);
	}
	/**
	 * Function prepares the data and sent to the server
	 * about to send application to renew subscription
	 * set array list with the data of the renew applications
	 */
	public void RenewSub(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		ArrayList<Object> obj=new ArrayList<Object>();
		int sub=renewFrame.cbPmethod.getSelectedIndex();
		int bookLeft=0;
		if(sub==1) bookLeft=5;
		if(sub==2) bookLeft=20;
		obj.add((int)userET.getId());
		obj.add((int)sub);
		obj.add((int)bookLeft);
		hmap.put("op", "RenewSub");
		hmap.put("obj", obj);
		client.handleMessageFromUI(hmap);
	}
	/**
	 * Function prepares the data and sent to the server
	 * about to check if the library stuff have notifications about review confirmation
	 */
	public void CheckNotif(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "CheckNotif");
		client.handleMessageFromUI(hmap);
	}

	}