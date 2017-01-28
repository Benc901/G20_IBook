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
import javax.swing.JTextField;

import Entities.BookET;
import Entities.GenreET;

public class PairingBookUI extends JPanel{
	
	public JButton btnBack;
	public JButton btnPairBook;
	public JTextField txtIdBooks;
	private ArrayList<BookET> books;
	private JComboBox comboBoxGenre;
	private JComboBox comboBoxSubject;
	
	public JComboBox getComboGenre()
	{
		return comboBoxGenre;
	}
	public JComboBox getComboSubject()
	{
		return comboBoxSubject;
	}
	public JButton getBtnBack() {
		return btnBack;
	}

	public JButton getBtnPairBook() {
		return btnPairBook;
	}

	public String getBook() {
		return txtIdBooks.getText();
	}

	public ArrayList<BookET> getBooks() {
		return books;
	}

	public ArrayList<GenreET> getGenresET() {
		return GenresET;
	}

	public void setBtnBack(JButton btnBack) {
		this.btnBack = btnBack;
	}

	public void setBtnPairBook(JButton btnPairBook) {
		this.btnPairBook = btnPairBook;
	}

	public void setComboBoxBooks(JTextField comboBoxBooks) {
		this.txtIdBooks = comboBoxBooks;
	}

	public void setBooks(ArrayList<BookET> books) {
		this.books = books;
	}

	public void setGenresET(ArrayList<GenreET> genresET) {
		GenresET = genresET;
	}

	private ArrayList<GenreET> GenresET;
	private JLabel lblNewLabel_1;
	private JLabel lblGenre;
	private JLabel lblSubject;
	/**The constrctor of the GUI Class PairingBookUI
	 * Build a GUI panel to the action PairingBook
	 * 
	 * @param genresET - ArrayList of GenreET of all genres in database.
	 * @param booksET - ArrayList of BookET of all books in database
	 */
	public PairingBookUI(ArrayList<BookET> booksET,ArrayList<GenreET> genresET)
	{
		GenresET=genresET;
		books=booksET;
		//*** DO NOT DELETE! ***//
	
		setBackground(new Color(153, 204, 204));
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
				
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
				
				
				
		//*** DO NOT DELETE! - END ***//
		
		
		txtIdBooks = new JTextField();
		txtIdBooks.setBounds(210, 202, 217, 30);
		add(txtIdBooks);
		
		comboBoxGenre = new JComboBox();
		comboBoxGenre.setBounds(210, 266, 217, 30);
		add(comboBoxGenre);
		BringGenreTCB();
		comboBoxGenre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SetSubjects((String)comboBoxGenre.getSelectedItem());
			}
		});
		
		comboBoxSubject = new JComboBox();
		comboBoxSubject.setBounds(210, 330, 217, 30);
		add(comboBoxSubject);
		comboBoxSubject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnBack = new JButton("Back");
		btnBack.setBounds(61, 458, 89, 30);
		add(btnBack);
		
		btnPairBook = new JButton("Pair Book");
		btnPairBook.setBounds(514, 458, 89, 30);
		add(btnPairBook);
		
		JLabel lblNewLabel = new JLabel("Pairing Book");
		lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
		lblNewLabel.setBounds(247, 150, 335, 41);
		add(lblNewLabel);
		
		lblGenre = new JLabel("Genre:");
		lblGenre.setFont(new Font("Arial", Font.PLAIN, 14));
		lblGenre.setBounds(124, 273, 74, 17);
		add(lblGenre);
		
		lblNewLabel_1 = new JLabel("Book Id:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(124, 208, 74, 17);
		add(lblNewLabel_1);
		
		lblSubject = new JLabel("Subject:");
		lblSubject.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSubject.setBounds(124, 337, 74, 17);
		add(lblSubject);
		
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
		comboBoxGenre.addItem(GenresET.get(i).getGenreTitle());
	}
	/** Put in the subject combo box all the subject that bond to the genre that selected.
	 * @param Title - the genre title of the genre that selected in the genre combo box - String.
	 */
	public void SetSubjects(String Title)
	{
		comboBoxSubject.removeAllItems();;
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
			comboBoxSubject.addItem(g.getSubjectList().get(i).getSubjectTitle());
		}
	}
}
