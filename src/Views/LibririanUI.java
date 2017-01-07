package Views;

import graphics.GUIimage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.net.URISyntaxException;
import javax.swing.JSeparator;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;

import com.alee.laf.button.WebButton;

import Entities.UserET;

import java.awt.Font;

import javax.swing.JProgressBar;

public class LibririanUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private WebButton btnHeatingFuel;
	private JPanel panel;
	public JLabel lblUsername;
	public JLabel lblUsernameVar;
	public JLabel lblEmail;
	public JLabel lblTelephonVAR; 
	public JLabel lblPoints;
	public WebButton btnLogout;
	public JLabel profile;
	
	public JProgressBar Rank;
	
	/*
	 * Create the application.
	 * @throws URISyntaxException 
	 */
	public LibririanUI(UserET userET) {
		
		//*** DO NOT DELETE! ***//
		
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		//btnHeatingFuel.setIcon(new GUIimage("heating_fuel",20,25).image);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.window);
		panel.setBounds(282, 203, 335, 271);
		add(panel);
		panel.setLayout(null);
		
		profile = new JLabel("");
		profile.setBounds(127, 21, 123, 125);
		profile.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(profile);
		

		
		profile.setIcon(new GUIimage("profile_picture",profile.getWidth(),profile.getHeight()).image);
		
		lblUsername = new JLabel("Name:");
		lblUsername.setBounds(15, 162, 61, 16);
		panel.add(lblUsername);
		
		lblUsernameVar = new JLabel(userET.getFirstName()+" "+userET.getLastName());
		lblUsernameVar.setBounds(127, 162, 198, 16);
		panel.add(lblUsernameVar);
		

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(15, 190, 74, 16);
		panel.add(lblEmail);
		
		lblTelephonVAR = new JLabel(userET.getEmail());
		lblTelephonVAR.setBounds(127, 190, 198, 16);
		panel.add(lblTelephonVAR);
		
		lblPoints = new JLabel("Customer Rate:");
		lblPoints.setBounds(15, 218, 102, 16);
		panel.add(lblPoints);
		
		
		Rank = new JProgressBar();
		Rank.setUI(new BasicProgressBarUI() {
		      protected Color getSelectionBackground() { return Color.black; }
		      protected Color getSelectionForeground() { return Color.BLACK; }
		    });
		Rank.setMaximum(10);
		Rank.setBounds(127, 220, 146, 14);
		Rank.setStringPainted(true);
		Rank.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int i=Rank.getValue();
				if (0<i && i<4) Rank.setForeground(Color.RED);
				if (3<i && i<8) Rank.setForeground(Color.YELLOW);
				if (7<i && i<11) Rank.setForeground(Color.GREEN);
				Rank.setString("Rate "+i);
			}
		});
		panel.add(Rank);
		
		
		
		//*** DO NOT DELETE! - END ***//
		
		WebButton btnFueling = new WebButton("Search book");
		btnFueling.setBounds(58, 203, 146, 54);
		add(btnFueling);
		
		
		btnHeatingFuel = new WebButton("Search review");
		btnHeatingFuel.setBounds(58, 273, 146, 54);
		add(btnHeatingFuel);
		
		WebButton CheckOrderBtn = new WebButton("Publish review");
		CheckOrderBtn.setBounds(58, 343, 146, 54);
		add(CheckOrderBtn);
		if(userET.getPermission()==1) CheckOrderBtn.setText("Enable payment");
		
		
		btnLogout = new WebButton("   Logout");
		btnLogout.setBounds(58, 413, 146, 54);
		add(btnLogout);
		
		btnLogout.setIcon(new GUIimage("logout",20,25).image);
		
		JLabel lblNewLabel = new JLabel("Welcome "+userET.getFirstName()+" "+userET.getLastName());
		lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
		lblNewLabel.setBounds(282, 150, 335, 41);
		add(lblNewLabel);
		
		if(userET.getPermission()==3|| userET.getPermission()==4){
		WebButton wbtnLibririan = new WebButton("Publish review");
		wbtnLibririan.setText("Libririan menu");
		wbtnLibririan.setBounds(282, 490, 146, 30);
		add(wbtnLibririan);}
		
		if(userET.getPermission()==4){
		WebButton wbtnManagerMenu = new WebButton("Publish review");
		wbtnManagerMenu.setText("Manager menu");
		wbtnManagerMenu.setBounds(471, 490, 146, 30);
		add(wbtnManagerMenu);}

	}
}