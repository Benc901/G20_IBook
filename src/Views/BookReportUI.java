package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;

import graphics.GUIimage;

public class BookReportUI extends JPanel {
	public JLabel lblBookReport;
	public JLabel lblBookId;
	public JButton btnByPurchases;
	public JButton btnBySearches;
	public JButton btnBack;
	public JTextField textField;
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
	public JPanel panel;
	public JFreeChart chart;
	public CategoryPlot p;
	public ChartPanel bar;
	public CategoryAxis domainAxis;
	
	
	/**
	 * Create the panel.
	 */
	
	public BookReportUI() {
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		lblBookReport = new JLabel("Book Report");
		lblBookReport.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblBookReport.setBounds(270, 140, 154, 30);
		add(lblBookReport);
		
		lblBookId = new JLabel("Book ID :");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookId.setBounds(45, 185, 70, 30);
		add(lblBookId);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(125, 187, 86, 30);
		textField.setColumns(10);
		add(textField);

		
		btnByPurchases = new JButton("By purchases");
		btnByPurchases.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnByPurchases.setBounds(240, 187, 120, 30);
		add(btnByPurchases);
		
		btnBySearches = new JButton("By searches");
		btnBySearches.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBySearches.setBounds(380, 187, 105, 30);
		add(btnBySearches);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(505, 205, 89, 30);
		add(btnBack);
		
		lblFrom = new JLabel("From :");
		lblFrom.setToolTipText("dd-MM-yyyy");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFrom.setBounds(45, 215, 46, 30);
		add(lblFrom);
		
		ddFrom = new JTextField();
		ddFrom.setToolTipText("dd");
		ddFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ddFrom.setBounds(89, 221, 30, 23);
		ddFrom.setColumns(2);
		add(ddFrom);

		
		labelSleh = new JLabel("/");
		labelSleh.setToolTipText(null);
		labelSleh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelSleh.setBounds(122, 215, 11, 30);
		add(labelSleh);
		
		MMFrom = new JTextField();
		MMFrom.setToolTipText("mm");
		MMFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MMFrom.setBounds(131, 221, 30, 23);
		MMFrom.setColumns(2);
		add(MMFrom);

		
		labelSleh1 = new JLabel("/");
		labelSleh1.setToolTipText(null);
		labelSleh1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelSleh1.setBounds(166, 215, 11, 30);
		add(labelSleh1);
		
		yyFrom = new JTextField();
		yyFrom.setToolTipText("yyyy");
		yyFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		yyFrom.setBounds(177, 221, 41, 23);
		yyFrom.setColumns(4);
		add(yyFrom);

		
		lblTo = new JLabel("To :");
		lblTo.setToolTipText("dd-MM-yyyy");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTo.setBounds(245, 215, 28, 30);
		add(lblTo);
		
		ddTo = new JTextField();
		ddTo.setToolTipText("dd");
		ddTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ddTo.setBounds(275, 221, 30, 23);
		ddTo.setColumns(2);
		add(ddTo);

		
		labelSleh2 = new JLabel("/");
		labelSleh2.setToolTipText(null);
		labelSleh2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelSleh2.setBounds(308, 215, 11, 30);
		add(labelSleh2);
		
		MMTo = new JTextField();
		MMTo.setToolTipText("mm");
		MMTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MMTo.setBounds(317, 221, 30, 23);
		MMTo.setColumns(2);
		add(MMTo);

		
		labelSleh3 = new JLabel("/");
		labelSleh3.setToolTipText(null);
		labelSleh3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelSleh3.setBounds(352, 215, 11, 30);
		add(labelSleh3);

		
		yyTo = new JTextField();
		yyTo.setToolTipText("yyyy");
		yyTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		yyTo.setBounds(363, 221, 41, 23);
		yyTo.setColumns(4);
		add(yyTo);

		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(12, 254, 646, 265);
		add(panel);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setToolTipText("");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
	
	public void setExtraPanel(){
		panel.setLayout(new BorderLayout(0, 0));
		panel.removeAll();
		panel.add(bar,BorderLayout.CENTER);
		panel.validate();
	}
}
