package Views;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import Entities.BookET;
import graphics.GUIimage;
import graphics.GUIimagejpg;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Controllers.BookCT;
import Controllers.UserCT;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class BookUI extends JPanel {
	public JButton btnBack;
	public JButton btnGetbook;
	
	public BookUI(BookET book){
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblNewBookName = new JLabel(book.getBTitle());
		lblNewBookName.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewBookName.setBounds(297, 149, 439, 35);
		add(lblNewBookName);
		
		JLabel lblAuthor = new JLabel("Author: "+book.getBAuthor());
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuthor.setBounds(297, 214, 250, 20);
		add(lblAuthor);
		
		JLabel lblGenere = new JLabel("Genere: "+book.getBGenre());
		lblGenere.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenere.setBounds(297, 245, 250, 20);
		add(lblGenere);
		
		JLabel lblSubject = new JLabel("Subject: "+book.getBSubject());
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubject.setBounds(297, 276, 250, 20);
		add(lblSubject);
		
		JLabel lblLanguage = new JLabel("Language: "+book.getBLanguage());
		lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLanguage.setBounds(297, 307, 250, 20);
		add(lblLanguage);
		
		JLabel lblSummery = new JLabel("Summery: ");
		lblSummery.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSummery.setBounds(297, 338, 250, 20);
		add(lblSummery);
		
		JPanel panel = new JPanel();
		panel.setBounds(297, 369, 360, 100);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 360, 100);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea(book.getBSummary());
		textArea.setLineWrap(true);
		textArea.setBounds(0, 0, 360, 100);
		scrollPane.setViewportView(textArea);
		
		
		JLabel profile = new JLabel("");
		profile.setBounds(30, 149, 250, 320);
		profile.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(profile);
		profile.setIcon(new GUIimagejpg("/books/" +book.getBPhoto(),profile.getWidth(),profile.getHeight()).image);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(30, 480, 250, 35);
		add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnBack){// if start:
					if(BookCT.bookCT.flag==1) MainUI.MV.setView(BookCT.bookCT.searchadvFrame);
					else MainUI.MV.setView(BookCT.bookCT.searchFrame);
				}
			}
		});
		if(UserCT.userCT.userET.getPermission()!=1){
		btnGetbook = new JButton("Get this book");
		btnGetbook.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnGetbook.setBounds(297, 480, 360, 35);
		add(btnGetbook);
		}
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
}
