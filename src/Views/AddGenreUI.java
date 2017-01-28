package Views;

import graphics.GUIimage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class AddGenreUI extends JPanel{
	
	public JButton btnBack;
	private JTextField txtGenreName;
	public JButton btnAdd;
	
	public String getGenreTitle()
	{
		return this.txtGenreName.getText();
	}
	/**The constrctor of the GUI Class AddGenreUI
	 * Build a GUI panel to the action AddGenre
	 * 
	 */
	public AddGenreUI() {
		// TODO Auto-generated constructor stub
		//*** DO NOT DELETE! ***//
				setBackground(new Color(153, 204, 204));
				this.setBounds(0, 0, 677, 562);
				this.setLayout(null);
						
				JSeparator separator = new JSeparator();
				separator.setBounds(0, 126, 677, 12);
				add(separator);
							
				//*** DO NOT DELETE! - END ***//
				
				
				
				JLabel lblNewLabel = new JLabel("Add Genre");
				lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
				lblNewLabel.setBounds(264, 152, 335, 41);
				add(lblNewLabel);
				
				txtGenreName = new JTextField();
				txtGenreName.setBounds(191, 270, 174, 30);
				add(txtGenreName);
				txtGenreName.setColumns(10);
				
				btnBack = new JButton("Back");
				btnBack.setBounds(58, 483, 89, 30);
				add(btnBack);
				
				btnAdd = new JButton("Add");
				btnAdd.setBounds(377, 269, 89, 30);
				add(btnAdd);
				
				JLabel lblBackground = new JLabel("New label");
				lblBackground.setBounds(0, 0, 671, 533);
				lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
				add(lblBackground);
	}
	public void clearFields()
	{
		this.txtGenreName.setText("");
	}
}
