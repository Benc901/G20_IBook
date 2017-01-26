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

public class EnablePaymentUI extends JPanel{
	public JButton btnBack;
	public JTextField tfCnumber;
	public JTextField tfCvv;
	public JTextField tfId;
	public JButton btnSendApplication;
	public JComboBox cbYvalid;
	public JComboBox cbMvalid;
	public JComboBox cbPmethod;
	
	public EnablePaymentUI() {
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblEnablePayment = new JLabel("Enable Payment");
		lblEnablePayment.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnablePayment.setBounds(249, 149, 200, 30);
		add(lblEnablePayment);
		
		JLabel lblPaymentMethod = new JLabel("Payment method :");
		lblPaymentMethod.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPaymentMethod.setBounds(174, 191, 140, 30);
		add(lblPaymentMethod);
		
		cbPmethod = new JComboBox();
		cbPmethod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbPmethod.setModel(new DefaultComboBoxModel(new String[] {"Pay per book", "5 book subscription", "20 book subscription"}));
		cbPmethod.setBounds(364, 191, 150, 30);
		add(cbPmethod);
		
		JLabel lblCreditCardNumber = new JLabel("Credit card number :");
		lblCreditCardNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCreditCardNumber.setBounds(174, 231, 150, 30);
		add(lblCreditCardNumber);
		
		tfCnumber = new JTextField();
		tfCnumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCnumber.setBounds(364, 231, 150, 30);
		add(tfCnumber);
		tfCnumber.setColumns(10);
		
		JLabel lblValid = new JLabel("Valid date :");
		lblValid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValid.setBounds(176, 273, 100, 30);
		add(lblValid);
		
		cbMvalid = new JComboBox();
		cbMvalid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbMvalid.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cbMvalid.setBounds(364, 273, 59, 30);
		add(cbMvalid);
		
		cbYvalid = new JComboBox();
		cbYvalid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbYvalid.setModel(new DefaultComboBoxModel(new String[] {"2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"}));
		cbYvalid.setBounds(433, 273, 81, 30);
		add(cbYvalid);
		
		JLabel lblCvv = new JLabel("Cvv number :");
		lblCvv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCvv.setBounds(176, 314, 120, 30);
		add(lblCvv);
		
		tfCvv = new JTextField();
		tfCvv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCvv.setBounds(364, 314, 59, 30);
		add(tfCvv);
		tfCvv.setColumns(10);
		
		JLabel lblId = new JLabel("Id number :");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(176, 355, 100, 30);
		add(lblId);
		
		tfId = new JTextField();
		tfId.setBounds(364, 355, 150, 30);
		add(tfId);
		tfId.setColumns(10);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(26, 485, 89, 30);
		add(btnBack);
		
		btnSendApplication = new JButton("Send application");
		btnSendApplication.setBounds(273, 396, 150, 40);
		add(btnSendApplication);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}

}
