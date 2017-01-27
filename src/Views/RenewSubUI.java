package Views;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import graphics.GUIimage;

/**
 * Class of gui extends JPanel
 * frame that user can choose the subscription he want to renew
 */
public class RenewSubUI extends JPanel{
	public JButton btnBack;
	public JButton btnRenew;
	public JComboBox cbPmethod;
	
	public RenewSubUI() {
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblRenew = new JLabel("Renew Subscription");
		lblRenew.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblRenew.setBounds(219, 150, 237, 30);
		add(lblRenew);
		
		JLabel lblPaymentMethod = new JLabel("Payment method :");
		lblPaymentMethod.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPaymentMethod.setBounds(174, 191, 140, 30);
		add(lblPaymentMethod);
		
		cbPmethod = new JComboBox();
		cbPmethod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbPmethod.setModel(new DefaultComboBoxModel(new String[] {"Pay per book", "5 book subscription", "20 book subscription"}));
		cbPmethod.setBounds(364, 191, 150, 30);
		add(cbPmethod);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(26, 485, 89, 30);
		add(btnBack);
		
		btnRenew = new JButton("Renew");
		btnRenew.setBounds(266, 257, 150, 40);
		add(btnRenew);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}

}
