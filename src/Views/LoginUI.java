package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.window.WebPopOver;

import Controllers.UserCT;
import graphics.GUIimage;
import graphics.InfiniteProgressPanel;


public class LoginUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUserName;
	private JPasswordField pwdPassword;
	public JLabel lblError;	
	public JTextField textFieldServerIP;
	public JButton btnLogin;
	public JButton btnConnect;
	public JLabel status;
	public InfiniteProgressPanel loadingAnimation;
	public InfiniteProgressPanel smallLoadingAnimation;
	public WebPopOver popover;
	private JTextField textFieldPort;
	public JButton btnForgot;

	
	public LoginUI(String host,int port) {
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblLogIn.setBounds(225, 171, 145, 37);
		this.add(lblLogIn);
		
		JLabel lockIcon = new JLabel("");
		lockIcon.setBounds(177, 171, 36, 37);
		lockIcon.setIcon(new GUIimage("Lock",lockIcon.getWidth(),lockIcon.getHeight()).image);
		
		this.add(lockIcon);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblUsername.setBounds(197, 235, 96, 28);
		this.add(lblUsername);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setText("");
		textFieldUserName.setBounds(298, 236, 165, 28);
		this.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		//select all when clicked.
		textFieldUserName.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {} // must implement interface
			public void focusGained(FocusEvent e) {textFieldUserName.selectAll();}
		});
		textFieldUserName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pwdPassword.grabFocus();
			}
		});
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblPassword.setBounds(197, 275, 96, 28);
		add(lblPassword);
		/*
		status = new JLabel("");
		status.setHorizontalAlignment(SwingConstants.CENTER);
		status.setBounds(468, 338, 20, 20);
		status.setIcon(new GUIimage("disconnect",status.getWidth(),status.getHeight()).image);
		status.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		add(status);
		
		loadingAnimation = new InfiniteProgressPanel(1.5);
		loadingAnimation.setBounds(218, 424, 163, 53);
		add(loadingAnimation);
		loadingAnimation.stop();
		
		smallLoadingAnimation = new InfiniteProgressPanel(1.0);
		smallLoadingAnimation.setBounds(status.getBounds());
		add(smallLoadingAnimation);
		smallLoadingAnimation.stop();
		*/
		btnLogin = new JButton("Login");
		btnLogin.setBounds(173, 424, 163, 53);
		btnLogin.setEnabled(true);
		this.add(btnLogin);
		
		

		pwdPassword = new JPasswordField();
		pwdPassword.setText("");
		pwdPassword.setBounds(298, 275, 165, 28);
		add(pwdPassword);
		pwdPassword.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {}// must implement interface
			public void focusGained(FocusEvent e) {pwdPassword.selectAll();}});
		pwdPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnLogin.isEnabled()) btnLogin.doClick();			
			}
		});

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(174, 211, 310, 12);
		separator_1.setForeground(Color.LIGHT_GRAY);
		this.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(174, 317, 310, 12);
		separator_2.setForeground(Color.LIGHT_GRAY);
		this.add(separator_2);
		
		lblError = new JLabel("Error");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		
		popover = new WebPopOver();
		popover.setCloseOnFocusLoss ( true );
		popover.setMargin ( 10 );
		popover.getContentPane().setLayout ( new VerticalFlowLayout () );
		popover.getContentPane().add (lblError);
		
		
		
		JLabel lblServerIP = new JLabel("Server IP:");
		lblServerIP.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblServerIP.setBounds(197, 333, 96, 28);
		add(lblServerIP);
		
		textFieldServerIP = new JTextField();
		textFieldServerIP.setText(host);
		textFieldServerIP.setColumns(10);
		textFieldServerIP.setBounds(298, 333, 165, 28);
		add(textFieldServerIP);
		
		JLabel lblServerPort = new JLabel("Server port:");
		lblServerPort.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblServerPort.setBounds(197, 372, 96, 28);
		add(lblServerPort);
		
		textFieldPort = new JTextField();
		textFieldPort.setText("5555");
		textFieldPort.setColumns(10);
		textFieldPort.setBounds(298, 372, 165, 28);
		add(textFieldPort);
		
		
		
		//select all when clicked.
		textFieldServerIP.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {}
			public void focusGained(FocusEvent e) {textFieldServerIP.selectAll();}
		});
		textFieldServerIP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MainClass.gasclient.ConnectToServer();
			}
		});
		
		
		
		btnForgot = new JButton("Forget password");
		btnForgot.setEnabled(true);
		btnForgot.setBounds(346, 424, 163, 53);
		add(btnForgot);
		new UserCT(this,port);
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}

	public void clearFields() {
		textFieldUserName.setText("");
		pwdPassword.setText("");
		
	}
	
	public String getIP(){
		return textFieldServerIP.getText();
	}
	
	public String getUsername(){
		return textFieldUserName.getText();
	}
	
	public String getPassword(){
		return pwdPassword.getText();
	}
}

