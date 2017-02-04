package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;

import com.alee.laf.button.WebButton;

import Entities.UserET;
import graphics.GUIimage;
import graphics.GUIimagejpg;
import javax.swing.JButton;
import javax.swing.UIManager;

/**
 * Class of gui extends JPanel
 * frame of the main menu of all the kind users
 * and the frame check the permission of the user 
 * and set the buttons by the user entity details
 */
public class ReaderUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public WebButton btnSearchReview;
	public JLabel lblUsername;
	public JLabel lblUsernameVar;
	public JLabel lblEmail;
	public JLabel lblTelephonVAR; 
	public WebButton btnLogout;
	public WebButton btnSearchBook;
	public JLabel profile;
	public WebButton btnEnablePublish;
	public JButton wbtnLibririan;
	public WebButton wbtnManagerMenu;
	public WebButton btnRenew;
	private JLabel label;
	private JLabel lblNewLabel_1;
	private JLabel label_1;
	
	/**
	 * Create the application.
	 * 
	 */
	public ReaderUI(UserET userET) {
		
		//*** DO NOT DELETE! ***//
		
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		profile = new JLabel("");
		profile.setBounds(424, 145, 59, 53);
		add(profile);
		profile.setBorder(new LineBorder(new Color(0, 0, 0)));
		

		
		profile.setIcon(new GUIimagejpg("/" +userET.getPhoto(),profile.getWidth(),profile.getHeight()).image);
		
		
		
		//*** DO NOT DELETE! - END ***//
		
		btnSearchBook = new WebButton("Search book");
		btnSearchBook.setBounds(36, 222, 146, 50);
		add(btnSearchBook);
		btnSearchBook.setIcon(new GUIimage("search",25,25).image);
		
		
		btnSearchReview = new WebButton("Search review");
		btnSearchReview.setBounds(36, 283, 146, 50);
		add(btnSearchReview);
		btnSearchReview.setIcon(new GUIimage("searchr",30,30).image);
		
		btnEnablePublish = new WebButton("Publish review");
		btnEnablePublish.setBounds(36, 344, 146, 50);
		add(btnEnablePublish);
		btnEnablePublish.setIcon(new GUIimage("publish",25,25).image);
		if(userET.getConfirm()==0) {
			btnEnablePublish.setText("<Html>Enable<br> payment</Html>");
			btnEnablePublish.setIcon(new GUIimage("dollar",30,33).image);
		}
		
		
		btnLogout = new WebButton("   Logout");
		btnLogout.setText("Logout");
		btnLogout.setBounds(36, 466, 146, 41);
		add(btnLogout);
		
		btnLogout.setIcon(new GUIimage("logout",20,25).image);
		
		btnRenew = new WebButton("<Html>Renew<br> subscription</Html>");
		btnRenew.setBounds(36, 405, 146, 50);
		add(btnRenew);
		btnRenew.setIcon(new GUIimage("renew",20,25).image);
		
		JLabel lblNewLabel = new JLabel("Welcome,");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("OpenSansHebrew-Bold", Font.BOLD, 20));
		lblNewLabel.setBounds(40, 143, 402, 24);
		add(lblNewLabel);
		
		if(userET.getPermission()==2||userET.getPermission()==3|| userET.getPermission()==4 ||userET.getPermission()==7|| userET.getPermission()==8 ||userET.getPermission()==9){
		wbtnLibririan = new WebButton("Libririan menu");
		wbtnLibririan.setBounds(499, 466, 146, 41);
		add(wbtnLibririan);
		wbtnLibririan.setIcon(new GUIimage("librarian",30,33).image);}
		
		if(userET.getPermission()==4 || userET.getPermission()==9){
		wbtnManagerMenu = new WebButton("Manager menu");
		wbtnManagerMenu.setBounds(270, 466, 146, 41);
		add(wbtnManagerMenu);
		wbtnManagerMenu.setIcon(new GUIimage("manager",20,25).image);}
		
		label_1 = new JLabel(userET.getFirstName()+" "+userET.getLastName());
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 32));
		label_1.setBounds(40, 168, 402, 30);
		add(label_1);
		
		lblUsernameVar = new JLabel(userET.getFirstName()+" "+userET.getLastName());
		lblUsernameVar.setBounds(525, 171, 198, 16);
		add(lblUsernameVar);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(493, 143, 20, 30);
		add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new GUIimage("userIcon",25,22).image);
		
		lblUsername = new JLabel("User:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(490, 171, 61, 16);
		add(lblUsername);
		

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(490, 185, 74, 16);
		add(lblEmail);
		
		lblTelephonVAR = new JLabel(userET.getEmail());
		lblTelephonVAR.setBounds(525, 185, 198, 16);
		add(lblTelephonVAR);
		
		label = new JLabel("");
		//label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(225, 255, 408, 187);
		add(label);
		label.setIcon(new GUIimagejpg("/reader1" ,label.getWidth(),label.getHeight()).image);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(36, 205, 604, 12);
		add(separator_1);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		

	}
}
