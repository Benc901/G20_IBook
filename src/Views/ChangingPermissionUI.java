package Views;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import graphics.GUIimage;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

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
		lblUserId.setBounds(49, 233, 80, 30);
		add(lblUserId);
		
		textField = new JTextField();
		textField.setBounds(117, 240, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setBounds(330, 240, 39, 20);
		add(comboBox);
		
		lblNewPermission = new JLabel("New Permission :");
		lblNewPermission.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewPermission.setBounds(213, 233, 107, 30);
		add(lblNewPermission);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(49, 483, 89, 23);
		add(btnBack);
		
		btnChange = new JButton("Change");
		btnChange.setBounds(379, 239, 89, 23);
		add(btnChange);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
}
