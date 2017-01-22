package Views;

import graphics.GUIimage;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

import Entities.GenreET;

public class RemoveGenreUI extends JPanel {
	
	public JButton btnBack;
	public JButton btnRemove;
	private ArrayList<GenreET> GenresET;
	private JComboBox GenrescomboBox;
	private JLabel lblGenres;
	
	
	
	public JComboBox getComboBoxGenres() {
		return GenrescomboBox;
	}
	
	public RemoveGenreUI(ArrayList<GenreET> genresET) {
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
		
		
		
		JLabel lblNewLabel = new JLabel("Remove Genre");
		lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
		lblNewLabel.setBounds(246, 151, 335, 41);
		add(lblNewLabel);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(58, 483, 89, 30);
		add(btnBack);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(422, 269, 89, 30);
		add(btnRemove);
		
		GenrescomboBox = new JComboBox();
		GenrescomboBox.setBounds(246, 269, 164, 30);
		add(GenrescomboBox);
		
		BringGenreTCB();
		
		lblGenres = new JLabel("Genres:");
		lblGenres.setFont(new Font("Arial", Font.PLAIN, 14));
		lblGenres.setBounds(179, 276, 55, 17);
		add(lblGenres);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
	
	public void BringGenreTCB()
	{
		for(int i=0;i<GenresET.size();i++)
			GenrescomboBox.addItem(GenresET.get(i).getGenreTitle());
	}
	
}
