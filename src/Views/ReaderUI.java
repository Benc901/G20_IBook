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
	public WebButton wbtnLibririan;
	public WebButton wbtnManagerMenu;
	public WebButton btnRenew;
	private JLabel label;
	
	/**
	 * Create the application.
	 * @throws URISyntaxException 
	 */
	public ReaderUI(UserET userET) {
		
		//*** DO NOT DELETE! ***//
		
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		profile = new JLabel("");
		profile.setBounds(226, 431, 80, 76);
		add(profile);
		profile.setBorder(new LineBorder(new Color(0, 0, 0)));
		

		
		profile.setIcon(new GUIimagejpg("/" +userET.getPhoto(),profile.getWidth(),profile.getHeight()).image);
		
		
		
		//*** DO NOT DELETE! - END ***//
		
		btnSearchBook = new WebButton("Search book");
		btnSearchBook.setBounds(36, 222, 146, 50);
		add(btnSearchBook);
		
		
		btnSearchReview = new WebButton("Search review");
		btnSearchReview.setBounds(36, 283, 146, 50);
		add(btnSearchReview);
		
		btnEnablePublish = new WebButton("Publish review");
		btnEnablePublish.setBounds(36, 344, 146, 50);
		add(btnEnablePublish);
		if(userET.getConfirm()==0) btnEnablePublish.setText("Enable payment");
		
		
		btnLogout = new WebButton("   Logout");
		btnLogout.setText("Logout");
		btnLogout.setBounds(36, 466, 146, 41);
		add(btnLogout);
		
		btnLogout.setIcon(new GUIimage("logout",20,25).image);
		
		btnRenew = new WebButton("Renew subscription");
		btnRenew.setBounds(36, 405, 146, 50);
		add(btnRenew);
		
		JLabel lblNewLabel = new JLabel("Welcome  "+userET.getFirstName()+" "+userET.getLastName());
		lblNewLabel.setForeground(UIManager.getColor("textHighlight"));
		lblNewLabel.setFont(new Font("OpenSansHebrew-Regular", Font.BOLD, 26));
		lblNewLabel.setBounds(178, 149, 335, 41);
		add(lblNewLabel);
		
		if(userET.getPermission()==3|| userET.getPermission()==4 || userET.getPermission()==8 ||userET.getPermission()==9){
		wbtnLibririan = new WebButton("Libririan menu");
		wbtnLibririan.setBounds(494, 472, 146, 35);
		add(wbtnLibririan);}
		
		if(userET.getPermission()==4 || userET.getPermission()==9){
		wbtnManagerMenu = new WebButton("Manager menu");
		wbtnManagerMenu.setBounds(494, 431, 146, 35);
		add(wbtnManagerMenu);}
		
		lblUsername = new JLabel("User:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(316, 477, 61, 16);
		add(lblUsername);
		
		lblUsernameVar = new JLabel(userET.getFirstName()+" "+userET.getLastName());
		lblUsernameVar.setBounds(351, 477, 198, 16);
		add(lblUsernameVar);
		

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(316, 491, 74, 16);
		add(lblEmail);
		
		lblTelephonVAR = new JLabel(userET.getEmail());
		lblTelephonVAR.setBounds(351, 491, 198, 16);
		add(lblTelephonVAR);
		
		label = new JLabel("");
		//label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(232, 222, 408, 172);
		add(label);
		label.setIcon(new GUIimagejpg("/reader" ,label.getWidth(),label.getHeight()).image);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);

	}
}
