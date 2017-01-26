package Views;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.alee.laf.button.WebButton;

import Controllers.ManagerCT;
import graphics.GUIimage;

public class ManagerUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public WebButton btnBreport;
	public JButton btnBack;
	public WebButton btnCpermission;
	public WebButton btnThidebook;
	public WebButton btnPbook;
	public WebButton btnUreport;
	public WebButton btnAFreezing;
	/*
	 * Create the application.
	 * @throws URISyntaxException 
	 */
	public ManagerUI() {
		
		//*** DO NOT DELETE! ***//
		
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		
		
		//*** DO NOT DELETE! - END ***//
		
		btnUreport = new WebButton("User report");
		btnUreport.setText("User report");
		btnUreport.setBounds(196, 200, 146, 54);
		add(btnUreport);
		
		
		btnBreport = new WebButton("Book report");
		btnBreport.setBounds(196, 270, 146, 54);
		add(btnBreport);
		
		btnPbook = new WebButton("Book Popularity");
		btnPbook.setBounds(196, 340, 146, 54);
		add(btnPbook);

		
		JLabel lblNewLabel = new JLabel("Manager panel");
		lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
		lblNewLabel.setBounds(273, 149, 335, 41);
		add(lblNewLabel);
		
		btnThidebook = new WebButton("Temporary hide book");

		btnThidebook.setBounds(365, 200, 146, 54);
		add(btnThidebook);
		
		btnCpermission = new WebButton("Changing permission");
		btnCpermission.setBounds(365, 270, 146, 54);
		add(btnCpermission);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(58, 483, 89, 30);
		add(btnBack);
		
		btnAFreezing = new WebButton("Account freezing");
		btnAFreezing.setBounds(365, 340, 146, 54);
		add(btnAFreezing);
		
		new ManagerCT(this); 
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
}
