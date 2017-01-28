package Views;

import graphics.GUIimage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import Entities.GenreET;

public class RemoveSubjectUI extends JPanel{
	
	public JButton btnBack;
	public JButton btnRemove;
	private ArrayList<GenreET> GenresET;
	private JComboBox GenrescomboBox;
	private JComboBox SubjectcomboBox;
	private JLabel lblGenres;
	private JLabel lblSubjects;
	
	public JComboBox getComboBoxSubject() {
		return SubjectcomboBox;
	}
	
	public JComboBox getComboBoxGenres() {
		return GenrescomboBox;
	}
	/**The constrctor of the GUI Class RemoveSubjectUI
	 * Build a GUI panel to the action RemoveSubject
	 * 
	 * @param genresET - ArrayList of GenreET of all genres in database.
	 */
	public RemoveSubjectUI(ArrayList<GenreET> genresET) {
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
		

		JLabel lblNewLabel = new JLabel("Remove Subject");
		lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
		lblNewLabel.setBounds(246, 151, 335, 41);
		add(lblNewLabel);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(58, 483, 89, 30);
		add(btnBack);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(436, 312, 89, 30);
		add(btnRemove);
		
		GenrescomboBox = new JComboBox();
		GenrescomboBox.setBounds(246, 269, 178, 30);
		add(GenrescomboBox);
		
		BringGenreTCB();
		GenrescomboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SetSubjects((String)GenrescomboBox.getSelectedItem());
			}
		});
		
		SubjectcomboBox = new JComboBox();
		SubjectcomboBox.setBounds(246, 312, 178, 30);
		add(SubjectcomboBox);
		
		lblGenres = new JLabel("Genres:");
		lblGenres.setFont(new Font("Arial", Font.PLAIN, 14));
		lblGenres.setBounds(171, 276, 55, 17);
		add(lblGenres);
		
		lblSubjects = new JLabel("Subjects:");
		lblSubjects.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSubjects.setBounds(171, 319, 66, 17);
		add(lblSubjects);
		
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
			GenrescomboBox.addItem(GenresET.get(i).getGenreTitle());
	}
	/** Put in the subject combo box all the subject that bond to the genre that selected.
	 * @param Title - the genre title of the genre that selected in the genre combo box - String.
	 */
	public void SetSubjects(String Title)
	{
		SubjectcomboBox.removeAllItems();;
		GenreET g=new GenreET();
		for(int i=0;i<GenresET.size();i++)
		{
			if(Title.equals(GenresET.get(i).getGenreTitle())){
					g.setGid(GenresET.get(i).getGid());
					g.setGenreTitle(Title);
					g.setSubjectList(GenresET.get(i).getSubjectList());
			}
		}
		for(int i=0;i<g.getSubjectList().size();i++)
		{
			SubjectcomboBox.addItem(g.getSubjectList().get(i).getSubjectTitle());
		}
	}

}
