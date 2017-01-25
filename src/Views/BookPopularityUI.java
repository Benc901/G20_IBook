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
	public JLabel lblBookRating;
	public JLabel lblBookId;
	public JButton btnTotalRank;
	public JButton btnGenreRank;
	public JButton btnBack;
	public JTextField textField;

	/**
	 * Create the panel.
	 */
	
	public BookPopularityUI() {
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		lblBookRating = new JLabel("Book Rating");
		lblBookRating.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblBookRating.setBounds(270, 159, 154, 30);
		add(lblBookRating);
		
		lblBookId = new JLabel("Book ID :");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookId.setBounds(55, 209, 70, 30);
		add(lblBookId);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(135, 211, 86, 30);
		textField.setColumns(10);
		add(textField);
		
		
		btnTotalRank = new JButton("Overall ranking");
		btnTotalRank.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTotalRank.setBounds(250, 211, 130, 30);
		add(btnTotalRank);
		
		btnGenreRank = new JButton("Rating by genre");
		btnGenreRank.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenreRank.setBounds(410, 211, 130, 30);
		add(btnGenreRank);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(45, 472, 89, 30);
		add(btnBack);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
		
	}
	
}
