package Views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import Controllers.LibrarianCT;
import Controllers.UserCT;
import graphics.GUIimage;

public class LibririanUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnCpayment;
	public JButton btnBack;
	public JButton btnCstructure;
	public JButton btnIupdate;
	public JButton btnCreview;
	public JButton btnAdduser;
	
	/**The constrctor of the GUI Class LibririanUI
	 * Build a GUI panel to the Libririan window to choose between the actions of the librarian.
	 * create a Libririan controller to deal with all actions in the Librarian panel and his sub-panels.
	 *
	 * 
	 */
	public LibririanUI() {
		
		//*** DO NOT DELETE! ***//
		setBackground(new Color(153, 204, 204));
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		
		
		//*** DO NOT DELETE! - END ***//
		if(UserCT.userET.getPermission()!=2 && UserCT.userET.getPermission()!=7){
		btnAdduser = new JButton("Add user");
		btnAdduser.setBounds(196, 200, 146, 54);
		add(btnAdduser);}
		
		if(UserCT.userET.getPermission()!=2 && UserCT.userET.getPermission()!=7){
		btnCpayment = new JButton("<HTML><center>Payment<br> confirmation</HTML>");
		btnCpayment.setBounds(196, 270, 146, 54);
		add(btnCpayment);}
		
		btnCreview = new JButton("<HTML><center>Review<br> confirmation</HTML>");
		btnCreview.setBounds(280, 340, 146, 54);
		add(btnCreview);

		
		JLabel lblNewLabel = new JLabel("Libririan panel");
		lblNewLabel.setFont(new Font("Narkisim", Font.BOLD, 26));
		lblNewLabel.setBounds(273, 149, 335, 41);
		add(lblNewLabel);
		
		if(UserCT.userET.getPermission()!=2 && UserCT.userET.getPermission()!=7){
		btnIupdate = new JButton("Inventory update");
		btnIupdate.setBounds(365, 200, 146, 54);
		add(btnIupdate);}
		
		if(UserCT.userET.getPermission()!=2 && UserCT.userET.getPermission()!=7){
		btnCstructure = new JButton("Control structure");
		btnCstructure.setBounds(365, 270, 146, 54);
		add(btnCstructure);}
		
		
		btnBack = new JButton("Back");
		btnBack.setBounds(58, 483, 89, 30);
		add(btnBack);
		
		new LibrarianCT(this);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}
	
}
