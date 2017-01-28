package Views;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import graphics.GUIimage;

public class ChangingPermissionUI extends JPanel {
	public JLabel lblChangingPermission;
	public JLabel lblUserId;
	public JTextField textField;
	public JComboBox comboBox;
	public JLabel lblNewPermission;
	public JButton btnBack;
	public JButton btnChange;
	
	/**
	 * Create the panel.
	 */
	public ChangingPermissionUI() {
		
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		lblChangingPermission = new JLabel("Changing Permission");
		lblChangingPermission.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblChangingPermission.setBounds(213, 149, 250, 30);
		add(lblChangingPermission);
		
		lblUserId = new JLabel("User ID :");
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserId.setBounds(52, 233, 71, 30);
		add(lblUserId);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(133, 234, 86, 30);
		add(textField);
		textField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBox.setBounds(362, 234, 44, 30);
		add(comboBox);
		
		lblNewPermission = new JLabel("New Permission :");
		lblNewPermission.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewPermission.setBounds(240, 233, 135, 30);
		add(lblNewPermission);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(31, 489, 89, 30);
		add(btnBack);
		
		btnChange = new JButton("Change");
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChange.setBounds(440, 234, 89, 30);
		add(btnChange);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
}
