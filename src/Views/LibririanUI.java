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

import Controllers.LibrarianCT;
import Entities.UserET;

import java.awt.Font;

import javax.swing.JProgressBar;

public class LibririanUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnCpayment;
	public JButton btnBack;
	public JButton btnCstructure;
	public JButton btnIupdate;
	public JButton btnCreview;
	public JButton btnAdduser;
	/*
	 * Create the application.
	 * @throws URISyntaxException 
	 */
	public LibririanUI() {
		
		//*** DO NOT DELETE! ***//
		setBackground(new Color(153, 204, 204));
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		
		
		//*** DO NOT DELETE! - END ***//
		
		btnAdduser = new JButton("Add user");
		btnAdduser.setBounds(196, 200, 146, 54);
		add(btnAdduser);
		
		
		btnCpayment = new JButton("Payment confirmation");
		btnCpayment.setBounds(196, 270, 146, 54);
		add(btnCpayment);
		
		btnCreview = new JButton("Review confirmation");
		btnCreview.setBounds(196, 340, 146, 54);
		add(btnCreview);

		
		JLabel lblNewLabel = new JLabel("Libririan panel");
		lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
		lblNewLabel.setBounds(273, 149, 335, 41);
		add(lblNewLabel);
		
		btnIupdate = new JButton("Inventory update");
		btnIupdate.setBounds(365, 200, 146, 54);
		add(btnIupdate);
		
		btnCstructure = new JButton("Control structure");
		btnCstructure.setBounds(365, 270, 146, 54);
		add(btnCstructure);
		
		
		btnBack = new JButton("Back");
		btnBack.setBounds(58, 483, 89, 30);
		add(btnBack);
		
		new LibrarianCT(this);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
	
}
