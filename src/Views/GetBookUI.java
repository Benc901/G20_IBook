package Views;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import graphics.GUIimage;

public class GetBookUI extends JPanel{
	public JComboBox comboBox; 
	public JButton btnBack;
	public JButton btnDownload;
	
	public GetBookUI(){
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblGetBook = new JLabel("Get book");
		lblGetBook.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblGetBook.setBounds(287, 149, 100, 30);
		add(lblGetBook);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(57, 447, 89, 30);
		add(btnBack);
	    

		
		btnDownload = new JButton("Download");
		btnDownload.setBounds(287, 271, 89, 30);
		add(btnDownload);
		
		JLabel lblNewLabel = new JLabel("Format :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(249, 230, 60, 30);
		add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"PDF", "Fb2", "Doc"}));
		comboBox.setBounds(341, 237, 81, 20);
		add(comboBox);
		
		
		
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
}
