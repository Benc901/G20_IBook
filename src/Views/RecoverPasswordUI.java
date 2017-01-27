package Views;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import graphics.GUIimage;
import javax.swing.JTextField;

import Controllers.UserCT;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

/**
 * Class of gui extends JPanel
 * frame that user should insert his user name to recover his password by the mail
 */
public class RecoverPasswordUI extends JPanel{
	public JTextField textField;
	public JButton btnBack;
	public JButton btnSend;

	public RecoverPasswordUI(){	
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblRecover = new JLabel("Recover Password");
		lblRecover.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblRecover.setBounds(240, 149, 300, 30);
		add(lblRecover);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(341, 253, 95, 30);
		add(textField);
		
		JLabel label = new JLabel("User name :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(240, 253, 80, 30);
		add(label);
		
		btnSend = new JButton("Send ");
		btnSend.setBounds(290, 323, 89, 30);
		add(btnSend);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(62, 450, 89, 30);
		add(btnBack);

		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
}
