package Views;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.SwingConstants;

import Controllers.BookCT;
import Controllers.UserCT;

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

public class SearchBookUI extends JPanel {
	 public JButton btnBack;
	 public JButton btnSearch;
	 private JTextField textField;
	 public JTable Orderstable;
	 public DefaultTableModel model;
	
	 
	public SearchBookUI() {
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
		lblSearchBy.setBounds(16, 190, 113, 30);
		add(lblSearchBy);
		
		JLabel lblSearchText = new JLabel("Search text :");
		lblSearchText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchText.setBounds(378, 190, 113, 30);
		add(lblSearchText);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(16, 499, 89, 23);
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
		model.addColumn("Subject");
		model.addColumn("Language");
		model.addColumn("Num of");
		Orderstable = new JTable(model){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int arg0, int arg1) {return false;};
		};
		Orderstable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Orderstable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Orderstable.setFillsViewportHeight(true);
		for (int i=0;i<6;i++) Orderstable.getColumnModel().getColumn(i).setPreferredWidth(83);
		Orderstable.getColumnModel().getColumn(6).setPreferredWidth(112);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 270, 608, 205);
		scrollPane.setRowHeaderView(Orderstable);
		scrollPane.setViewportView(Orderstable);
		add(scrollPane);
		
		JCheckBox chckbxTitle = new JCheckBox("Title");
		chckbxTitle.setBounds(104, 197, 104, 18);
		add(chckbxTitle);
		
		JCheckBox chckbxLanguage = new JCheckBox("Language");
		chckbxLanguage.setBounds(104, 218, 104, 18);
		add(chckbxLanguage);
		
		JCheckBox chckbxSummery = new JCheckBox("Summery");
		chckbxSummery.setBounds(230, 197, 104, 18);
		add(chckbxSummery);
		
		JCheckBox chckbxGenre = new JCheckBox("Genre");
		chckbxGenre.setBounds(230, 218, 104, 18);
		add(chckbxGenre);
		
		Orderstable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = Orderstable.rowAtPoint(evt.getPoint());
		        BookCT.bookCT.viewBook(row);
		    }
		});
		
		
		//scrollPane.setBounds(37, 236, 608, 239);
		
		
	}
		
		
		

	
	public String GetText(){
		return textField.getText();
	}
	public String GetCB(){
		return comboBox.getActionCommand();
	}
}
