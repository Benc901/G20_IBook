package Views;

import graphics.GUIimage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class RemoveBookUI extends JPanel{
	
	public JButton btnBack;
	public JButton btnRBook;
	
	public RemoveBookUI() {
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
		
		
		
		JLabel lblAddUserTo = new JLabel("Remove Book From DB");
		lblAddUserTo.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblAddUserTo.setBounds(180, 170, 250, 50);
		add(lblAddUserTo);
		
		
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
}
