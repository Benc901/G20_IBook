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

/**
 * Class of gui extends JPanel
 * frame that show the details about the review that the user select
 */
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
		lblNewBookName.setBounds(250, 149, 439, 35);
		add(lblNewBookName);

		btnBack = new JButton("Back");
		btnBack.setBounds(28, 482, 75, 25);
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
		profile.setBounds(28, 149, 209, 227);
		profile.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(profile);
		profile.setIcon(new GUIimagejpg("/books/"+review.getBookphoto(),profile.getWidth(),profile.getHeight()).image);
		
		JLabel profile1 = new JLabel("");
		profile1.setBounds(250,210,75,78);
		profile1.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(profile1);
		profile1.setIcon(new GUIimagejpg("/"+review.getUserPhoto(),profile1.getWidth(),profile1.getHeight()).image);

		JLabel label = new JLabel("");
		label.setBounds(43, 202, 46, 14);
		add(label);
		
		JLabel Review = new JLabel("Review by :");
		Review.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Review.setBounds(335, 240, 150, 25);
		add(Review);
		
		JLabel Username = new JLabel(review.getUserName());
		Username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Username.setBounds(335, 260, 150, 25);
		add(Username);
		
		JLabel lblNewLabel = new JLabel("Author : "+review.getAuthor());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(250, 290, 300, 30);
		add(lblNewLabel);
		
		
		JLabel lblNewLabel_2 = new JLabel("Rate :"+review.getRate());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(250, 310, 46, 30);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("The review :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(250, 331, 100, 30);
		add(lblNewLabel_3);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(335, 210, 20, 30);
		add(label_1);
		label_1.setIcon(new GUIimage("userIcon",25,22).image);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
}
