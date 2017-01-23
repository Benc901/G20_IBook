package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Controllers.ReviewCT;
import Entities.ReviewET;
import graphics.GUIimage;
import graphics.GUIimagejpg;

public class ReviewUI extends JPanel {
	private JTextField textField;
	private JButton btnBack;

	
	public ReviewUI(ReviewET review){
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblNewBookName = new JLabel(review.getBookName());
		lblNewBookName.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewBookName.setBounds(250, 165, 439, 35);
		add(lblNewBookName);

		btnBack = new JButton("Back");
		btnBack.setBounds(31, 482, 75, 23);
		add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnBack.getText().equals("Back")){// if start:
					MainUI.MV.setView(ReviewCT.reviewCT.searchFrame);
				}
			}
		});
		JPanel panel = new JPanel();
		panel.setBounds(250, 375, 360, 100);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 360, 100);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea(review.getReview());
		textArea.setLineWrap(true);
		textArea.setBounds(0, 0, 360, 100);
		scrollPane.setViewportView(textArea);
		

		JLabel profile = new JLabel("");
		profile.setBounds(28, 149, 209, 251);
		profile.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(profile);
		profile.setIcon(new GUIimagejpg("/books/"+review.getBookphoto(),profile.getWidth(),profile.getHeight()).image);
		
		JLabel profile1 = new JLabel("");
		profile1.setBounds(509,226,75,78);
		profile1.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(profile1);
		profile1.setIcon(new GUIimagejpg("/"+review.getUserPhoto(),profile1.getWidth(),profile1.getHeight()).image);

		JLabel label = new JLabel("");
		label.setBounds(43, 202, 46, 14);
		add(label);
		
		JLabel Username = new JLabel("Review by :"+review.getUserName());
		Username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Username.setBounds(499, 185, 150, 30);
		add(Username);
		
		JLabel lblNewLabel = new JLabel("Author : "+review.getAuthor());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(248, 249, 300, 30);
		add(lblNewLabel);
		
		
		JLabel lblNewLabel_2 = new JLabel("Rate :"+review.getRate());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(250, 290, 46, 30);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("The review :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(250, 331, 100, 30);
		add(lblNewLabel_3);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
}
