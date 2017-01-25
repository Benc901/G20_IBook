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

public class RecoverPasswordUI extends JPanel{
	public JTextField textField;
	public JButton btnBack;
	public JButton btnSend;
	private JTextField textField_1;
	public RecoverPasswordUI rc;
	public RecoverPasswordUI(){	
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		this.rc=this;
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
		
		JButton btnGetFile = new JButton("get file");
		btnGetFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserCT.userCT.GetFile();
			}
		});
		btnGetFile.setBounds(290, 454, 89, 30);
		add(btnGetFile);
		
		
		
		
		JButton btnTest = new JButton("test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setSize(50, 30);
				chooser.setLocation(290, 420);
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("select folder");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				
				if(chooser.showSaveDialog(rc)== JFileChooser.APPROVE_OPTION){
					
					textField_1.setText(""+chooser.getSelectedFile());
				}
				
				
			}
		});
		btnTest.setBounds(290, 382, 89, 23);
		add(btnTest);
		
		textField_1 = new JTextField();
		textField_1.setBounds(290, 423, 156, 25);
		add(textField_1);
		textField_1.setColumns(10);
		

		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
}
