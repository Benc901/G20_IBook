package Views;

import graphics.GUIimage;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Entities.GenreET;

public class AddSubjectUI extends JPanel{
	public JButton btnBack;
	private JTextField txtSubjectName;
	public JButton btnAdd;
	private JLabel lblGenres;
	private JLabel lblSubjectName;
	private JComboBox comboBoxGenres;
	private ArrayList<GenreET> GenresET;
	
	public JComboBox getComboBoxGenres() {
		return comboBoxGenres;
	}
	
	public String getSubjectTitle()
	{
		return this.txtSubjectName.getText();
	}
	
	
	/**The constrctor of the GUI Class AddSubjectUI
	 * Build a GUI panel to the action AddSubject
	 * 
	 * @param genresET - ArrayList of GenreET of all genres in database.
	 */
	public AddSubjectUI(ArrayList<GenreET> genresET) {
		// TODO Auto-generated constructor stub
		GenresET=genresET;
		//*** DO NOT DELETE! ***//
				setBackground(new Color(153, 204, 204));
				this.setBounds(0, 0, 677, 562);
				this.setLayout(null);
						
				JSeparator separator = new JSeparator();
				separator.setBounds(0, 126, 677, 12);
				add(separator);
							
				//*** DO NOT DELETE! - END ***//
				
				
				
				JLabel lblNewLabel = new JLabel("Add Subject");
				lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
				lblNewLabel.setBounds(264, 152, 335, 41);
				add(lblNewLabel);
				
				txtSubjectName = new JTextField();
				txtSubjectName.setBounds(241, 270, 174, 30);
				add(txtSubjectName);
				txtSubjectName.setColumns(10);
				
				btnBack = new JButton("Back");
				btnBack.setBounds(58, 483, 89, 30);
				add(btnBack);
				
				btnAdd = new JButton("Add");
				btnAdd.setBounds(437, 269, 89, 30);
				add(btnAdd);
				
				comboBoxGenres = new JComboBox();
				comboBoxGenres.setBounds(241, 220, 174, 30);
				add(comboBoxGenres);
				
				BringGenreTCB();
				
				lblGenres = new JLabel("Genres:");
				lblGenres.setFont(new Font("Arial", Font.PLAIN, 14));
				lblGenres.setBounds(144, 227, 55, 17);
				add(lblGenres);
				
				lblSubjectName = new JLabel("Subject Name:");
				lblSubjectName.setFont(new Font("Arial", Font.PLAIN, 14));
				lblSubjectName.setBounds(144, 276, 91, 17);
				add(lblSubjectName);
				
				JLabel lblBackground = new JLabel("New label");
				lblBackground.setBounds(0, 0, 671, 533);
				lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
				add(lblBackground);
	}
	/**
	 * Put in the genre combo box all the genres that in database(from the genres ArrayList)
	 */
	public void BringGenreTCB()
	{
		for(int i=0;i<GenresET.size();i++)
		comboBoxGenres.addItem(GenresET.get(i).getGenreTitle());
	}
	public void clearFields()
	{
		this.txtSubjectName.setText("");
	}
}
