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
		lblPaymentMethod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaymentMethod.setBounds(72, 190, 120, 30);
		add(lblPaymentMethod);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Pay per book", "5 book subscription", "20 book subscription"}));
		comboBox.setBounds(218, 197, 130, 20);
		add(comboBox);
		
		JLabel lblCreditCardNumber = new JLabel("Credit card number :");
		lblCreditCardNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCreditCardNumber.setBounds(72, 231, 150, 30);
		add(lblCreditCardNumber);
		
		textField = new JTextField();
		textField.setBounds(218, 238, 107, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblValid = new JLabel("Valid date :");
		lblValid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValid.setBounds(72, 272, 80, 30);
		add(lblValid);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox_1.setBounds(151, 279, 41, 20);
		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"}));
		comboBox_2.setBounds(202, 279, 59, 20);
		add(comboBox_2);
		
		JLabel lblCvv = new JLabel("Cvv :");
		lblCvv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCvv.setBounds(72, 313, 40, 30);
		add(lblCvv);
		
		textField_1 = new JTextField();
		textField_1.setBounds(122, 320, 33, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblId = new JLabel("Id :");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(72, 354, 40, 30);
		add(lblId);
		
		textField_2 = new JTextField();
		textField_2.setBounds(106, 361, 71, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(72, 487, 89, 30);
		add(btnBack);
		
		JButton btnSendApplication = new JButton("Send application");
		btnSendApplication.setBounds(72, 413, 130, 30);
		add(btnSendApplication);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
		
	}

}
