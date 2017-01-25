package Views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

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
	public JLabel lblFrom;
	public JTextField ddFrom;
	public JTextField MMFrom;
	public JTextField yyFrom;
	public JTextField ddTo;
	public JTextField MMTo;
	public JTextField yyTo;
	public JLabel labelSleh;
	public JLabel labelSleh1;
	public JLabel labelSleh2;
	public JLabel labelSleh3;
	public JLabel lblTo;
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
		lblUserReport.setBounds(276, 142, 234, 29);
		add(lblUserReport);
		

		
		lblUserID = new JLabel("User ID :");
		lblUserID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserID.setBounds(40, 180, 113, 30);
		add(lblUserID);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(117, 181, 89, 30);
		add(textField);
		
		btnShowReport = new JButton("Show report");
		btnShowReport.setBounds(233, 181, 100, 30);
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
		
		lblFrom = new JLabel("From :");
		lblFrom.setToolTipText("dd-MM-yyyy");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFrom.setBounds(42, 221, 46, 30);
		add(lblFrom);
		
		ddFrom = new JTextField();
		ddFrom.setToolTipText("dd");
		ddFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ddFrom.setBounds(86, 226, 30, 23);
		ddFrom.setColumns(2);
		add(ddFrom);

		
		labelSleh = new JLabel("/");
		labelSleh.setToolTipText(null);
		labelSleh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelSleh.setBounds(119, 221, 11, 30);
		add(labelSleh);
		
		MMFrom = new JTextField();
		MMFrom.setToolTipText("mm");
		MMFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MMFrom.setBounds(128, 226, 30, 23);
		MMFrom.setColumns(2);
		add(MMFrom);

		
		labelSleh1 = new JLabel("/");
		labelSleh1.setToolTipText(null);
		labelSleh1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelSleh1.setBounds(163, 221, 11, 30);
		add(labelSleh1);
		
		yyFrom = new JTextField();
		yyFrom.setToolTipText("yyyy");
		yyFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		yyFrom.setBounds(174, 226, 41, 23);
		yyFrom.setColumns(4);
		add(yyFrom);

		
		lblTo = new JLabel("To :");
		lblTo.setToolTipText("dd-MM-yyyy");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTo.setBounds(245, 221, 28, 30);
		add(lblTo);
		
		ddTo = new JTextField();
		ddTo.setToolTipText("dd");
		ddTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ddTo.setBounds(275, 226, 30, 23);
		ddTo.setColumns(2);
		add(ddTo);

		
		labelSleh2 = new JLabel("/");
		labelSleh2.setToolTipText(null);
		labelSleh2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelSleh2.setBounds(308, 221, 11, 30);
		add(labelSleh2);
		
		MMTo = new JTextField();
		MMTo.setToolTipText("mm");
		MMTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MMTo.setBounds(317, 226, 30, 23);
		MMTo.setColumns(2);
		add(MMTo);

		
		labelSleh3 = new JLabel("/");
		labelSleh3.setToolTipText(null);
		labelSleh3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelSleh3.setBounds(352, 221, 11, 30);
		add(labelSleh3);

		
		yyTo = new JTextField();
		yyTo.setToolTipText("yyyy");
		yyTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		yyTo.setBounds(363, 226, 41, 23);
		yyTo.setColumns(4);
		add(yyTo);

		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
		
	}
}
