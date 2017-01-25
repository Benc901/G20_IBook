package Views;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import graphics.GUIimage;

public class AccountFreezUI extends JPanel {
	public JTextField textField;
	public JLabel lblFreeze;
	public JLabel lbUserId;
	public JButton btnFreeze;
	public JButton btnUnFreeze;
	public JButton btnBack;
	/**
	 * Create the panel.
	 */
	public AccountFreezUI() {
		
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		lblFreeze = new JLabel("Freeze User");
		lblFreeze.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblFreeze.setBounds(253, 149, 151, 30);
		add(lblFreeze);
		
		lbUserId = new JLabel("User ID :");
		lbUserId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbUserId.setBounds(82, 260, 107, 30);
		add(lbUserId);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(155, 260, 112, 30);
		add(textField);
		textField.setColumns(10);
		
		btnFreeze = new JButton("Freeze User");
		btnFreeze.setBounds(288, 260, 100, 30);
		add(btnFreeze);
		
		btnUnFreeze = new JButton("UnFreeze User");
		btnUnFreeze.setBounds(413, 260, 120, 30);
		add(btnUnFreeze);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(31, 489, 89, 30);
		add(btnBack);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
}

