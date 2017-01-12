package Views;

import graphics.GUIimage;

import java.awt.Color;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import Controllers.LibrarianCT;
import javax.swing.JTextField;

public class AddUserUI extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	public JButton btnAddUser;
	public TextField UserId;
	public JLabel UserIdL;
	private JTextField txtUsername;
	private JTextField txtUserpassword;
	private JTextField txtFirstname;
	private JTextField txtLastname;
	private JTextField txtEmail;
	
	public AddUserUI() {
		// TODO Auto-generated constructor stub
		setBackground(new Color(153, 204, 204));
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(40, 458, 104, 58);
		add(btnBack);
		
		 btnAddUser = new JButton("Add User");
		btnAddUser.setBounds(535, 458, 104, 58);
		add(btnAddUser);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(192, 239, 74, 14);
		add(lblUserName);
		
		JLabel lblUserpassword = new JLabel("User Password");
		lblUserpassword.setBounds(192, 267, 74, 14);
		add(lblUserpassword);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(192, 295, 74, 14);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(192, 323, 74, 14);
		add(lblLastName);
		
		JLabel lblEmail = new JLabel("eMail");
		lblEmail.setBounds(192, 351, 46, 14);
		add(lblEmail);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(294, 236, 216, 20);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		txtUserpassword = new JTextField();
		txtUserpassword.setBounds(294, 264, 216, 20);
		add(txtUserpassword);
		txtUserpassword.setColumns(10);
		
		txtFirstname = new JTextField();
		txtFirstname.setBounds(294, 292, 216, 20);
		add(txtFirstname);
		txtFirstname.setColumns(10);
		
		txtLastname = new JTextField();
		txtLastname.setBounds(294, 320, 216, 20);
		add(txtLastname);
		txtLastname.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(294, 348, 216, 20);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblAddUserTo = new JLabel("Add User To The system");
		lblAddUserTo.setBounds(294, 170, 177, 36);
		add(lblAddUserTo);
		
		
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
	public void clearFields()
	{
		txtUsername.setText("");
		txtUserpassword.setText("");
		txtFirstname.setText("");
		txtLastname.setText("");
		txtEmail.setText("");
	}
	public String GetUserName()
	{
		return txtUsername.getText();
	}
	public String GetUserPassword()
	{
		return txtUserpassword.getText();
	}
	public String GetFirstName()
	{
		return txtFirstname.getText();
	}
	public String GetLastName()
	{
		return txtLastname.getText();
	}
	public String GetEmail()
	{
		return txtEmail.getText();
	}

}
