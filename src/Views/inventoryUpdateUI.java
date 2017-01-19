package Views;

import graphics.GUIimage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class inventoryUpdateUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	public JButton btnAddBook;
	public JButton btnRBook;
	public JButton btnUBook;
	
	public inventoryUpdateUI() 
	{
		setBackground(new Color(153, 204, 204));
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(40, 458, 89, 30);
		add(btnBack);
		
		btnAddBook = new JButton("Add Book");
		btnAddBook.setBounds(253, 200, 146, 54);
		add(btnAddBook);
		
		btnRBook = new JButton("Remove Book");
		btnRBook.setBounds(253, 351, 146, 54);
		add(btnRBook);
		
		btnUBook = new JButton("Update Book");
		btnUBook.setBounds(253, 277, 146, 54);
		add(btnUBook);
		
		JLabel lblAddUserTo = new JLabel("Inventory Update");
		lblAddUserTo.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblAddUserTo.setBounds(232, 149, 250, 50);
		add(lblAddUserTo);
		
		
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}

}
