package Views;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import graphics.GUIimage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class EnablePaymentUI extends JPanel{
	public JButton btnBack;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Pay per book", "5 book subscription", "20 book subscription"}));
		comboBox.setBounds(364, 191, 150, 30);
		add(comboBox);
		
		JLabel lblCreditCardNumber = new JLabel("Credit card number :");
		lblCreditCardNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCreditCardNumber.setBounds(174, 231, 150, 30);
		add(lblCreditCardNumber);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(364, 231, 150, 30);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblValid = new JLabel("Valid date :");
		lblValid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValid.setBounds(176, 273, 100, 30);
		add(lblValid);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox_1.setBounds(364, 273, 59, 30);
		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"}));
		comboBox_2.setBounds(433, 273, 81, 30);
		add(comboBox_2);
		
		JLabel lblCvv = new JLabel("Cvv number :");
		lblCvv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCvv.setBounds(176, 314, 120, 30);
		add(lblCvv);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setBounds(364, 314, 59, 30);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblId = new JLabel("Id number :");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(176, 355, 100, 30);
		add(lblId);
		
		textField_2 = new JTextField();
		textField_2.setBounds(364, 355, 150, 30);
		add(textField_2);
		textField_2.setColumns(10);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(26, 485, 89, 30);
		add(btnBack);
		
		JButton btnSendApplication = new JButton("Send application");
		btnSendApplication.setBounds(273, 396, 150, 40);
		add(btnSendApplication);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}

}
