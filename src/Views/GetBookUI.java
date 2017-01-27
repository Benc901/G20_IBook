package Views;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import graphics.GUIimage;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;



/**
 * Class of gui extends JPanel
 * frame of that user want to purchase book 
 * and he decide the format of the book
 * and decide the path where he want to save the book file in his computer
 */
public class GetBookUI extends JPanel{
	public JComboBox cbFormat; 
	public JButton btnBack;
	public JButton btnDownload;
	public JTextField path;
	public GetBookUI frame;
	
	public GetBookUI(){
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		this.frame=this;
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblGetBook = new JLabel("Get book");
		lblGetBook.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblGetBook.setBounds(287, 149, 100, 30);
		add(lblGetBook);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(57, 447, 89, 30);
		add(btnBack);
	    

		
		btnDownload = new JButton("Download");
		btnDownload.setBounds(287, 312, 89, 30);
		add(btnDownload);
		
		JLabel lblNewLabel = new JLabel("Format :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(249, 230, 60, 30);
		add(lblNewLabel);
		
		cbFormat = new JComboBox();
		cbFormat.setModel(new DefaultComboBoxModel(new String[] {"pdf", "fb2", "doc"}));
		cbFormat.setBounds(340, 232, 81, 30);
		add(cbFormat);
		
		path = new JTextField();
		path.setBounds(201, 271, 162, 30);
		add(path);
		path.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse"); // when user press he have to decide the path where he want to save the book file
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setSize(50, 30);
				chooser.setLocation(290, 420);
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("select folder");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				if(chooser.showSaveDialog(frame)== JFileChooser.APPROVE_OPTION){
				path.setText(""+chooser.getSelectedFile());}
			}
		});
		btnBrowse.setBounds(373, 271, 89, 30);
		add(btnBrowse);
		
		
		
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
}
