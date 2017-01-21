package Views;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import Entities.ReaderET;
import graphics.GUIimage;
import javax.swing.JButton;

public class ReviewConfirmationUI extends JPanel  {
	 public JTable Orderstable;
	 public DefaultTableModel model;
	 public JButton btnConfirm;
	 public JButton btnEdit;
	 public JButton btnBack;
	 public int row;
	 public JButton btnReject;
	 
	public ReviewConfirmationUI(){
		row=-1;
		setBackground(new Color(153, 204, 204));
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		JLabel lblSearchBook = new JLabel("Review Confirmation");
		lblSearchBook.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSearchBook.setBounds(203, 149, 270, 29);
		add(lblSearchBook);
		
		
		model = new DefaultTableModel();
		model.addColumn("Book name");
		model.addColumn("User name");
		model.addColumn("Rate");
		model.addColumn("Review");

		Orderstable = new JTable(model){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int arg0, int arg1) {return false;};
		};
		Orderstable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Orderstable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Orderstable.setFillsViewportHeight(true);
		for (int i=0;i<3;i++) Orderstable.getColumnModel().getColumn(i).setPreferredWidth(83);
		Orderstable.getColumnModel().getColumn(3).setPreferredWidth(112);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 189, 608, 255);
		scrollPane.setRowHeaderView(Orderstable);
		scrollPane.setViewportView(Orderstable);
		add(scrollPane);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(518, 455, 127, 30);
		add(btnConfirm);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(379, 455, 127, 30);
		add(btnEdit);
		
		btnReject = new JButton("Reject");
		btnReject.setBounds(246, 455, 127, 30);
		add(btnReject);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(37, 455, 97, 30);
		add(btnBack);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
		Orderstable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        row = Orderstable.rowAtPoint(evt.getPoint());
		        System.out.println(row);
  
		    }
		});
	}
	public void setrow(int i){
		this.row=i;
	}

}
