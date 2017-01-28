package Views;

import java.awt.Color;
import java.awt.Font;

import graphics.GUIimage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**Class of gui extends JPanel - ControlstructureUI.
 * Build a GUI panel to choose several options: 
	 * PairBook , SettingDivision - for the library stuff.
 * 
 */
public class ControlstructureUI extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public JButton btnPBook;
	public JButton btnBack;
	public JButton btnSdivision;
	
	/**The constrctor of the GUI Class ControlstructureUI.   
	 * Build a GUI panel to choose several options: 
	 * PairBook , SettingDivision.
	 * 
	 */
	public ControlstructureUI()
	{
		setBackground(new Color(153, 204, 204));
		//*** DO NOT DELETE! ***//
		
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
				
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
				
				
				
		//*** DO NOT DELETE! - END ***//
				
		btnPBook = new JButton("Pairing Book");
		btnPBook.setBounds(177, 255, 146, 54);
		add(btnPBook);
		
		btnSdivision = new JButton("Setting Division");
		btnSdivision.setBounds(354, 255, 146, 54);
		add(btnSdivision);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(58, 483, 89, 30);
		add(btnBack);
				
		JLabel lblNewLabel = new JLabel("Control Structure");
		lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
		lblNewLabel.setBounds(244, 150, 335, 41);
		add(lblNewLabel);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
}
