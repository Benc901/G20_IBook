package Views;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import Controllers.LibrarianCT;
import Controllers.ReviewCT;
import Entities.ReviewET;
import graphics.GUIimage;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditReviewUI extends JPanel{
	public JButton btnBack ;
	public JButton btnConfirm ;
	public JTextArea textArea ;
	public ReviewET review;
	public EditReviewUI(ReviewET review) {
		this.review=review;
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblEditReview = new JLabel("Edit Review");
		lblEditReview.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEditReview.setBounds(270, 149, 180, 30);
		add(lblEditReview);
		
		JLabel lblBookName = new JLabel("Book name :"+review.getBookName());
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookName.setBounds(41, 212, 600, 20);
		add(lblBookName);
		
		JLabel lblRate = new JLabel("Rate :"+review.getRate());
		lblRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRate.setBounds(41, 247, 80, 20);
		add(lblRate);
		
		JLabel lblReview = new JLabel("Review :");
		lblReview.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReview.setBounds(41, 278, 80, 20);
		add(lblReview);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainUI.MV.setView(LibrarianCT.librarianCT.reviewFrame);
			}
		});
		btnBack.setBounds(41, 462, 89, 30);
		add(btnBack);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibrarianCT.librarianCT.editFrame.review.setReview(textArea.getText());
				LibrarianCT.librarianCT.EditReview(LibrarianCT.librarianCT.editFrame.review);;
			}
		});
		btnConfirm.setBounds(551, 462, 89, 30);
		add(btnConfirm);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 324, 600, 120);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 600, 120);
		panel.add(scrollPane);
		
		textArea = new JTextArea(review.getReview());
		textArea.setLineWrap(true);
		textArea.setBounds(0, 0, 360, 100);
		scrollPane.setViewportView(textArea);
		
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
}
