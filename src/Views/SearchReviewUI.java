package Views;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Controllers.ReviewCT;
import graphics.GUIimage;

public class SearchReviewUI extends JPanel {
	
	 public JButton btnBack;
	 public JButton btnSearch;
	 private JTextField textField;
	 public JComboBox comboBox ;
	 public JTable Orderstable;
	 public DefaultTableModel model;
	 
	 public SearchReviewUI(){
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblSearchReview = new JLabel("Search Review");
		lblSearchReview.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSearchReview.setBounds(260, 150, 234, 29);
		add(lblSearchReview);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Title", "Author", "Keywords"}));
		comboBox.setBounds(106, 190, 172, 30);
		add(comboBox);
		
		JLabel lblSearchBy = new JLabel("Search By :");
		lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchBy.setBounds(16, 190, 113, 30);
		add(lblSearchBy);
		
		JLabel lblSearchText = new JLabel("Search text :");
		lblSearchText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchText.setBounds(301, 190, 113, 30);
		add(lblSearchText);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(16, 497, 89, 23);
		add(btnBack);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(578, 190, 77, 30);
		add(btnSearch);
		
		
		
		textField = new JTextField();
		textField.setBounds(396, 190, 172, 30);
		add(textField);
		
		model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Title");
		model.addColumn("Author");
		model.addColumn("Reviewer");
		model.addColumn("Rate");
		Orderstable = new JTable(model){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int arg0, int arg1) {return false;};
		};
		Orderstable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Orderstable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Orderstable.setFillsViewportHeight(true);
		for (int i=0;i<4;i++) Orderstable.getColumnModel().getColumn(i).setPreferredWidth(83);
		Orderstable.getColumnModel().getColumn(4).setPreferredWidth(112);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 236, 608, 239);
		scrollPane.setRowHeaderView(Orderstable);
		scrollPane.setViewportView(Orderstable);
		add(scrollPane);
		
		Orderstable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = Orderstable.rowAtPoint(evt.getPoint());
		        if(row!=-1) ReviewCT.reviewCT.viewReview(row);
		       
		    }
		});
		
		new ReviewCT(this);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
	public String GetText(){
			return textField.getText();
		}
}
