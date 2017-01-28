package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import graphics.GUIimage;

/**Class of gui extends JPanel. 
 * Build a GUI panel to the action AddUser for the library stuff.
 * 
 */
public class AddUserUI extends JPanel{
	
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
	
	/**The constrctor of the GUI Class AddUserUI
	 * Build a GUI panel to the action AddUser
	 * 
	 */
	public AddUserUI() {
		// TODO Auto-generated constructor stub
		setBackground(new Color(153, 204, 204));
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(40, 458, 104, 30);
		add(btnBack);
		
		 btnAddUser = new JButton("Add User");
		btnAddUser.setBounds(535, 458, 104, 30);
		add(btnAddUser);
		
		JLabel lblUserName = new JLabel("User Name: ");
		lblUserName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblUserName.setBounds(170, 235, 96, 28);
		add(lblUserName);
		
		JLabel lblUserpassword = new JLabel("User Password: ");
		lblUserpassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblUserpassword.setBounds(170, 265, 125, 28);
		add(lblUserpassword);
		
		JLabel lblFirstName = new JLabel("First Name: ");
		lblFirstName.setBounds(170, 295, 96, 28);
		lblFirstName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setBounds(170, 325, 96, 28);
		lblLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		add(lblLastName);
		
		JLabel lblEmail = new JLabel("eMail: ");
		lblEmail.setBounds(170, 355, 96, 28);
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		add(lblEmail);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(305, 235, 175, 28);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		txtUserpassword = new JTextField();
		txtUserpassword.setBounds(305, 265, 175, 28);
		add(txtUserpassword);
		txtUserpassword.setColumns(10);
		
		txtFirstname = new JTextField();
		txtFirstname.setBounds(305, 295, 175, 28);
		add(txtFirstname);
		txtFirstname.setColumns(10);
		
		txtLastname = new JTextField();
		txtLastname.setBounds(305, 325, 175, 28);
		add(txtLastname);
		txtLastname.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(305, 355, 175, 28);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblAddUserTo = new JLabel("Add User To The system");
		lblAddUserTo.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblAddUserTo.setBounds(180, 170, 250, 50);
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
