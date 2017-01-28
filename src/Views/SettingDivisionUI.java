package Views;

import graphics.GUIimage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class SettingDivisionUI extends JPanel{
	
	public JButton btnAddGenre;
	public JButton btnBack;
	public JButton btnAddSubject;
	public JButton btnRGenre;
	public JButton btnRSubject;
	
	/**The constrctor of the GUI Class SettingDivisionUI.   
	 * Build a GUI panel to choose several options: 
	 * AddGenre , AddSubject , RemoveGenre , RemoveSubject.
	 * 
	 */
	public SettingDivisionUI()
	{
		//*** DO NOT DELETE! ***//
		setBackground(new Color(153, 204, 204));
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
				
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
				
				
				
		//*** DO NOT DELETE! - END ***//
		
		btnAddGenre = new JButton("Add Genre");
		btnAddGenre.setBounds(166, 200, 146, 54);
		add(btnAddGenre);
		
		
		btnRGenre = new JButton("Remove Genre");
		btnRGenre.setBounds(166, 270, 146, 54);
		add(btnRGenre);
		
		btnAddSubject = new JButton("Add Subject");
		btnAddSubject.setBounds(347, 200, 146, 54);
		add(btnAddSubject);
		
		btnRSubject = new JButton("Remove Subject");
		btnRSubject.setBounds(347, 270, 146, 54);
		add(btnRSubject);
		
		JLabel lblNewLabel = new JLabel("Setting Division");
		lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
		lblNewLabel.setBounds(249, 151, 335, 41);
		add(lblNewLabel);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(58, 483, 89, 30);
		add(btnBack);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
}
