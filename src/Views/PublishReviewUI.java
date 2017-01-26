package Views;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import Controllers.ReviewCT;
import graphics.GUIimage;

public class PublishReviewUI extends JPanel{
	public JButton btnBack ;
	public JButton btnPublish ;
	public JComboBox comboBox;
	public JComboBox comboBox_1 ;
	public JTextArea textArea ;
	public PublishReviewUI() {
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblPublishReview = new JLabel("Publish Review");
		lblPublishReview.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblPublishReview.setBounds(252, 149, 180, 30);
		add(lblPublishReview);
		
		comboBox = new JComboBox();
		comboBox.setBounds(131, 214, 163, 20);
		add(comboBox);
		
		JLabel lblBookName = new JLabel("Book name :");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookName.setBounds(41, 212, 80, 20);
		add(lblBookName);
		
		JLabel lblRate = new JLabel("Rate :");
		lblRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRate.setBounds(41, 247, 80, 20);
		add(lblRate);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox_1.setBounds(131, 249, 42, 20);
		add(comboBox_1);
		
		JLabel lblReview = new JLabel("Review :");
		lblReview.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReview.setBounds(41, 278, 80, 20);
		add(lblReview);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(41, 462, 89, 30);
		add(btnBack);
		
		btnPublish = new JButton("Publish");
		btnPublish.setBounds(551, 462, 89, 30);
		add(btnPublish);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 324, 600, 120);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 600, 120);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(0, 0, 360, 100);
		scrollPane.setViewportView(textArea);
		
		new ReviewCT(this);
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
}
