package iBookServer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.alee.laf.optionpane.WebOptionPane;

import CommonUI.CommonIF;
import ocsf.server.ConnectionToClient;
/*
import com.alee.laf.button.WebButton;
import com.alee.laf.optionpane.WebOptionPane;
*/

public class serverUI extends JFrame implements CommonIF {
	
	private JPanel contentPane;
	public IBookServer server;
	private JTextArea textArea;
	private JTextField textFieldPort;
	private JTextField textFieldSQLuser;
	private JTextField textFieldSQLpass;
	public JLabel label_2;
	public JLabel lblSQL;
	public JLabel lblIp;
	private JButton stopBtn;
	
	public serverUI(IBookServer server1){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		getContentPane().setBackground(SystemColor.menu);
		this.server=server1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 566);
		getContentPane().setLayout(null);
		this.setTitle("IBook Server Log");
		
		JLabel lblServerSide = new JLabel("Server side");
		lblServerSide.setBounds(148, 7, 131, 33);
		getContentPane().add(lblServerSide);
		lblServerSide.setFont(new Font("Tahoma", Font.PLAIN, 27));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(101, 64, 232, 5);
		getContentPane().add(separator);
		separator.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		JLabel lblListenToPort = new JLabel("listen to port:");
		lblListenToPort.setBounds(101, 86, 105, 14);
		getContentPane().add(lblListenToPort);
		
		textFieldPort = new JTextField();
		textFieldPort.setText("5555");
		textFieldPort.setBounds(216, 80, 117, 26);
		getContentPane().add(textFieldPort);
		textFieldPort.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("SQL Username:");
		lblNewLabel.setBounds(101, 117, 89, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldSQLuser = new JTextField();
		textFieldSQLuser.setText("root");
		textFieldSQLuser.setBounds(216, 111, 117, 26);
		getContentPane().add(textFieldSQLuser);
		textFieldSQLuser.setColumns(10);
		
		JLabel lblSqlPassword = new JLabel("SQL Password:");
		lblSqlPassword.setBounds(101, 148, 89, 14);
		getContentPane().add(lblSqlPassword);
		
		textFieldSQLpass = new JTextField();
		textFieldSQLpass.setText("1234");
		textFieldSQLpass.setBounds(216, 142, 117, 26);
		getContentPane().add(textFieldSQLpass);
		textFieldSQLpass.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(101, 190, 232, 14);
		getContentPane().add(separator_1);
		
		lblIp = new JLabel("New label");
		lblIp.setBounds(190, 39, 89, 14);
		getContentPane().add(lblIp);
		
		lblSQL = new JLabel("Offline");
		lblSQL.setHorizontalAlignment(SwingConstants.CENTER);
		lblSQL.setForeground(Color.RED);
		lblSQL.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSQL.setBounds(268, 224, 59, 14);
		getContentPane().add(lblSQL);
		
		JLabel label_1 = new JLabel("Server State: ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(134, 199, 68, 14);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("Offline");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(268, 199, 59, 14);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("MySQL State:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(134, 223, 66, 14);
		getContentPane().add(label_3);
		
		stopBtn = new JButton("STOP");
		stopBtn.setBackground(Color.WHITE);
		stopBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		stopBtn.setBounds(101, 262, 140, 55);
		getContentPane().add(stopBtn);
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(stopBtn.getText().equals("Start")){// if start:
					stopBtn.setText("Stop");
					server.StartServer(Integer.parseInt(textFieldPort.getText()));
					server.ConnectToSQL(textFieldSQLuser.getText(),textFieldSQLpass.getText());
				}else{// if stop:
					server.StopServer();
					if(server.sqlCon != null)
						server.sqlCon.closeSqlConnection();
					stopBtn.setText("Start");
				}
			}
		});
		
		JButton tglbtnRestart = new JButton("<html>Load<br>&nbsp;SQL</html>");
		tglbtnRestart.setBounds(247, 262, 80, 55);
		getContentPane().add(tglbtnRestart);
		tglbtnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selection = WebOptionPane.showConfirmDialog ( null, 
								"Are you sure you want to reset the Data Base?", 
								" Warning ! Warning ! Warning ! Warning ! Warning !", WebOptionPane.YES_NO_OPTION,
								WebOptionPane.WARNING_MESSAGE );
				if(selection == 0) {
					SET_DB_WITH_SQL_FILE.main(null);
				}
			}
		});
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 386, 383, 130);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 383, 130);
		panel.add(scrollPane);
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblLogScreen = new JLabel("Log screen");
		lblLogScreen.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblLogScreen.setBounds(157, 347, 120, 33);
		getContentPane().add(lblLogScreen);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(101, 328, 232, 8);
		getContentPane().add(separator_2);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    			server.sendToAllClients("Server Shutdown!!!");
		    			try {
							server.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        }
		    });
		
	}
	
	public void display(String message, ConnectionToClient client) {
		String currentText = textArea.getText();
		String newText = new Date() + " - " + client + " - " + message;
		String newTextToAppend = newText + "\n" + currentText;
		textArea.setText(newTextToAppend);		
	}
	
	@Override
	public void display(String message) {
		String currentText = textArea.getText();
		String newText = new Date() + " - " + message;
		String newTextToAppend = newText + "\n" + currentText;
		textArea.setText(newTextToAppend);		
	}
}
