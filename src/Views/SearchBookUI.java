package Views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Controllers.BookCT;
import graphics.GUIimage;

public class SearchBookUI extends JPanel {
	 public JButton btnBack;
	 public JButton btnSearch;
	 public JButton btnAdvancedSearch;
	 public JTextField textField;
	 public JTable Orderstable;
	 public DefaultTableModel model;
	 public JCheckBox chckbxTitle;
	 public JCheckBox chckbxAuthor;
	 public JCheckBox chckbxSummery;
	 public JCheckBox chckbxGenre;
	 public JCheckBox checkBoxLanguage;
	 public JCheckBox chckbxKeywords;
	
	 
	public SearchBookUI() {
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
		
		JLabel lblSearchBy = new JLabel("Search By :");
		lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchBy.setBounds(40, 190, 113, 30);
		add(lblSearchBy);
		
		JLabel lblSearchText = new JLabel("Search text :");
		lblSearchText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchText.setBounds(378, 190, 113, 30);
		add(lblSearchText);
		
		btnAdvancedSearch = new JButton("Advanced Search");
		btnAdvancedSearch.setBounds(495, 486, 150, 30);
		add(btnAdvancedSearch);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(40, 486, 89, 30);
		add(btnBack);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(556, 228, 89, 30);
		add(btnSearch);
		new BookCT(this);
		
		
		textField = new JTextField();
		textField.setBounds(473, 190, 172, 30);
		add(textField);
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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 270, 608, 205);
		scrollPane.setRowHeaderView(Orderstable);
		scrollPane.setViewportView(Orderstable);
		add(scrollPane);
		
		chckbxTitle = new JCheckBox("Title");
		chckbxTitle.setBounds(128, 197, 104, 18);
		add(chckbxTitle);
		 
		chckbxAuthor = new JCheckBox("Author");
		chckbxAuthor.setBounds(128, 218, 104, 18);
		add(chckbxAuthor);
		
		chckbxSummery = new JCheckBox("Summery");
		chckbxSummery.setBounds(254, 197, 104, 18);
		add(chckbxSummery);
		
		chckbxGenre = new JCheckBox("Genre");
		chckbxGenre.setBounds(254, 218, 104, 18);
		add(chckbxGenre);
		
		checkBoxLanguage = new JCheckBox("Language");
		checkBoxLanguage.setBounds(128, 240, 104, 18);
		add(checkBoxLanguage);
		
		chckbxKeywords = new JCheckBox("Keywords");
		chckbxKeywords.setBounds(254, 240, 104, 18);
		add(chckbxKeywords);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
		Orderstable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = Orderstable.rowAtPoint(evt.getPoint());
		        if(row!=-1)BookCT.bookCT.viewBook(row,0);
		        
		    }
		});
		
		
		
		//scrollPane.setBounds(37, 236, 608, 239);
		
		
	}
		
		
		

	
	public String GetText(){
		return textField.getText();
	}
}
