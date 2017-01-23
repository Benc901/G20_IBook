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
import org.jfree.chart.plot.CategoryPlot;

import graphics.GUIimage;

public class BookPopularityUI extends JPanel {
	public JLabel lblBookReport;
	public JLabel lblBookId;
	public JButton btnByPurchases;
	public JButton btnBySearches;
	public JButton btnBack;
	public JTextField textField;
	public JFreeChart chart;
	public CategoryPlot p;
	public ChartPanel bar;
	public JPanel panel;
	/**
	 * Create the panel.
	 */
	
	public BookPopularityUI() {
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		lblBookReport = new JLabel("Book Report");
		lblBookReport.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblBookReport.setBounds(270, 159, 154, 30);
		add(lblBookReport);
		
		lblBookId = new JLabel("Book ID :");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookId.setBounds(45, 209, 70, 30);
		add(lblBookId);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(125, 211, 86, 30);
		add(textField);
		textField.setColumns(10);
		
		btnByPurchases = new JButton("By purchases");
		btnByPurchases.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnByPurchases.setBounds(240, 211, 120, 30);
		add(btnByPurchases);
		
		btnBySearches = new JButton("By searches");
		btnBySearches.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBySearches.setBounds(380, 211, 105, 30);
		add(btnBySearches);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(505, 211, 89, 30);
		add(btnBack);
		
		/*panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(12, 254, 646, 265);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.removeAll();*/
		
		/*
		DefaultCategoryDataset san = new DefaultCategoryDataset();
		san.setValue(8000, "ABC", "DEF");
		san.setValue(5400, "GHI", "JKL");
		san.setValue(2150, "MNO", "PQR");
		san.setValue(6700, "STU", "VWX");
		san.setValue(4200, "YZA", "BCD");
		san.setValue(8300, "EFG", "HIJ");
		*/
		
		/*JFreeChart chart = ChartFactory.createBarChart("Gidi The King","1","2",san,PlotOrientation.VERTICAL, false,true,false);
		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.black);*/
		
		/*ChartPanel bar = new ChartPanel(chart);
		panel.removeAll();
		panel.add(bar,BorderLayout.CENTER);
		panel.validate();*/
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
		
	}
	
	public void setExtraPanel(){
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(12, 254, 646, 265);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.removeAll();
		panel.add(bar,BorderLayout.CENTER);
		panel.validate();
	}
	
}
