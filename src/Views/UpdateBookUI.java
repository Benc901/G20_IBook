package Views;

import graphics.GUIimage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UpdateBookUI extends JPanel {
	
	
	public JButton btnBack;
	public JButton btnChoose;
	public JButton btnUpdate;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtLan;
	private JTextField txtContent;
	private JTextField txtBid;
	private JTextArea txtASummary;
	private JTextField txtKwords;
	
	public UpdateBookUI()
	{
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(40, 458, 89, 25);
		add(btnBack);
		
		
		
		JLabel lblAddUserTo = new JLabel("Update Book In DB");
		lblAddUserTo.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblAddUserTo.setBounds(238, 165, 250, 50);
		add(lblAddUserTo);
		
		JLabel lblBook = new JLabel("Book id : ");
		lblBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBook.setBounds(199, 226, 70, 17);
		add(lblBook);
		
		btnChoose = new JButton("Choose");
		btnChoose.setBounds(449, 226, 89, 23);
		add(btnChoose);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(114, 256, 155, 25);
		add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(114, 284, 155, 25);
		add(txtAuthor);
		
		txtLan = new JTextField();
		txtLan.setColumns(10);
		txtLan.setBounds(114, 312, 155, 25);
		add(txtLan);
		
		JPanel panel = new JPanel();
		panel.setBounds(340, 257, 290, 100);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 290, 100);
		panel.add(scrollPane);
		
		txtASummary = new JTextArea();
		txtASummary.setLineWrap(true);
		txtASummary.setBounds(0, 0, 290, 100);
		scrollPane.setViewportView(txtASummary);
		
		txtContent = new JTextField();
		txtContent.setColumns(10);
		txtContent.setBounds(114, 340, 155, 25);
		add(txtContent);
		
		JLabel lblContent = new JLabel("Content :");
		lblContent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContent.setBounds(40, 345, 70, 14);
		add(lblContent);
		
		JLabel label = new JLabel("Title :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(40, 256, 50, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Author :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(40, 284, 60, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("Language :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(40, 312, 70, 14);
		add(label_2);
		
		JLabel lblSummary = new JLabel("Summary :");
		lblSummary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSummary.setBounds(278, 261, 70, 14);
		add(lblSummary);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(514, 458, 89, 30);
		add(btnUpdate);
		
		txtBid = new JTextField();
		txtBid.setBounds(285, 226, 86, 25);
		add(txtBid);
		txtBid.setColumns(10);
		
		JLabel lblKeyWords = new JLabel("Key words :");
		lblKeyWords.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKeyWords.setBounds(40, 371, 70, 14);
		add(lblKeyWords);
		
		txtKwords = new JTextField();
		txtKwords.setColumns(10);
		txtKwords.setBounds(114, 368, 155, 25);
		add(txtKwords);
		
		
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		clearFields();
	}
	public void clearFields()
	{
		txtTitle.setText("");
		txtAuthor.setText("");
		txtContent.setText("");
		txtLan.setText("");
		txtASummary.setText("");
		txtKwords.setText("");
		txtBid.setText("");
	}
	public JTextField getTxtTitle() {
		return txtTitle;
	}
	public JTextField getTxtAuthor() {
		return txtAuthor;
	}
	public JTextField getTxtLan() {
		return txtLan;
	}
	public JTextField getTxtContent() {
		return txtContent;
	}
	public int getTxtBid() {
		return Integer.parseInt(txtBid.getText());
	}
	public JTextField getTxtKwords() {
		return txtKwords;
	}
	public JTextArea getTxtASummary() {
		return txtASummary;
	}
}
