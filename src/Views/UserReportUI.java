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

public class UserReportUI extends JPanel {

	 public JButton btnBack;
	 public JLabel lblUserID;
	 public JLabel lblUserReport;
	 public JTextField textField;
	 public JButton btnShowReport;
	 public JTable Orderstable;
	 public DefaultTableModel model;
	 public JScrollPane scrollPane;
	/**
	 * Create the panel.
	 */
	public UserReportUI() {
		setBackground(new Color(153, 204, 204));
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		lblUserReport = new JLabel("User Report");
		lblUserReport.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblUserReport.setBounds(276, 149, 234, 29);
		add(lblUserReport);
		

		
		lblUserID = new JLabel("User ID :");
		lblUserID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserID.setBounds(40, 220, 113, 30);
		add(lblUserID);
		
		textField = new JTextField();
		textField.setBounds(117, 220, 89, 30);
		add(textField);
		
		btnShowReport = new JButton("Show report");
		btnShowReport.setBounds(233, 220, 100, 30);
		add(btnShowReport);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(40, 486, 89, 30);
		add(btnBack);
		
		model = new DefaultTableModel();
		model.addColumn("Book ID");
		model.addColumn("Book name");
		model.addColumn("Purchase date");
		Orderstable = new JTable(model){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int arg0, int arg1) {return false;};
		};
		Orderstable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Orderstable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Orderstable.setFillsViewportHeight(true);
		for (int i=0;i<3;i++) Orderstable.getColumnModel().getColumn(i).setPreferredWidth(83);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 270, 608, 205);
		scrollPane.setRowHeaderView(Orderstable);
		scrollPane.setViewportView(Orderstable);
		add(scrollPane);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
		
	}
}
