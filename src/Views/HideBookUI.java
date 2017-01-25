package Views;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import graphics.GUIimage;

public class HideBookUI extends JPanel {
	public JTextField textField;
	public JLabel lblHideBook;
	public JLabel lblBookId;
	public JButton btnHide;
	public JButton btnUnhide;
	public JButton btnBack;
	/**
	 * Create the panel.
	 */
	public HideBookUI() {
		
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		lblHideBook = new JLabel("Hide book");
		lblHideBook.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblHideBook.setBounds(253, 149, 120, 30);
		add(lblHideBook);
		
		lblBookId = new JLabel("Book ID :");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookId.setBounds(70, 260, 107, 30);
		add(lblBookId);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(155, 260, 112, 30);
		add(textField);
		textField.setColumns(10);
		
		btnHide = new JButton("Hide");
		btnHide.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHide.setBounds(288, 260, 100, 30);
		add(btnHide);
		
		btnUnhide = new JButton("UnHide");
		btnUnhide.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUnhide.setBounds(413, 260, 100, 30);
		add(btnUnhide);
		
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
