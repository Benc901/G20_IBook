package Views;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.SwingConstants;

import Controllers.BookCT;
import Controllers.UserCT;
import graphics.GUIimage;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchAdvUI extends JPanel {
	 public JButton btnBack;
	 public JButton btnSearch;
	 public JTable Orderstable;
	 public DefaultTableModel model;
	 public JTextField textField_Title;
	 public JTextField textField_Author;
	 public JTextField textField_Language;
	 public JTextField textField_Summery;
	 public JTextField textField_Genere;
	 public JTextField textField_Keywords;
	
	 
	public SearchAdvUI() {
		setBackground(new Color(153, 204, 204));
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblSearchBook = new JLabel("Search Book");
		lblSearchBook.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSearchBook.setBounds(276, 149, 234, 29);
		add(lblSearchBook);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainUI.MV.setView(BookCT.searchFrame);
			}
		});
		btnBack.setBounds(40, 486, 89, 30);
		add(btnBack);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(556, 193, 89, 30);
		add(btnSearch);
		//new String[] {"Language", "Subject", "Genre", "Author", "Name", "Id"}))
		model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Name");
		model.addColumn("Author");
		model.addColumn("Genre");
		model.addColumn("Language");
		model.addColumn("Num of");
		Orderstable = new JTable(model){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int arg0, int arg1) {return false;};
		};
		Orderstable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Orderstable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Orderstable.setFillsViewportHeight(true);
		for (int i=0;i<5;i++) Orderstable.getColumnModel().getColumn(i).setPreferredWidth(83);
		Orderstable.getColumnModel().getColumn(5).setPreferredWidth(112);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTitle.setBounds(40, 176, 120, 20);
		add(lblTitle);
		
		textField_Title = new JTextField();
		textField_Title.setBounds(40, 196, 120, 25);
		add(textField_Title);
		textField_Title.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAuthor.setBounds(40, 218, 120, 20);
		add(lblAuthor);
		
		textField_Author = new JTextField();
		textField_Author.setColumns(10);
		textField_Author.setBounds(40, 238, 120, 25);
		add(textField_Author);
		
		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLanguage.setBounds(220, 176, 120, 20);
		add(lblLanguage);
		
		textField_Language = new JTextField();
		textField_Language.setColumns(10);
		textField_Language.setBounds(220, 196, 120, 25);
		add(textField_Language);
		
		JLabel lblSummery = new JLabel("Summery:");
		lblSummery.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSummery.setBounds(220, 218, 120, 20);
		add(lblSummery);
		
		textField_Summery = new JTextField();
		textField_Summery.setColumns(10);
		textField_Summery.setBounds(220, 238, 120, 25);
		add(textField_Summery);
		
		JLabel lblGenere = new JLabel("Genere:");
		lblGenere.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGenere.setBounds(399, 176, 120, 20);
		add(lblGenere);
		
		textField_Genere = new JTextField();
		textField_Genere.setColumns(10);
		textField_Genere.setBounds(399, 196, 120, 25);
		add(textField_Genere);
		
		JLabel lblKeywords = new JLabel("Keywords:");
		lblKeywords.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKeywords.setBounds(399, 218, 120, 20);
		add(lblKeywords);
		
		textField_Keywords = new JTextField();
		textField_Keywords.setColumns(10);
		textField_Keywords.setBounds(399, 238, 120, 25);
		add(textField_Keywords);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 270, 608, 205);
		scrollPane.setRowHeaderView(Orderstable);
		scrollPane.setViewportView(Orderstable);
		add(scrollPane);
		
		Orderstable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = Orderstable.rowAtPoint(evt.getPoint());
		        if(row!=-1)BookCT.bookCT.viewBook(row,1);
		        
		    }
		});
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
		
		
		//scrollPane.setBounds(37, 236, 608, 239);
		
		
	}
		
		
		

	
	public String GetText(){
		return textField_Title.getText();
	}
}
