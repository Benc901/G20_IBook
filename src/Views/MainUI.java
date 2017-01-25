package Views;

<<<<<<< HEAD
import graphics.GUIimage;
import javax.swing.UIManager.*;

import java.awt.Color;
=======
>>>>>>> refs/heads/Gidi_Final
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.alee.laf.progressbar.WebProgressBar;

<<<<<<< HEAD
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
=======
import graphics.GUIimage;
import graphics.SlideContainer;
>>>>>>> refs/heads/Gidi_Final

public class MainUI {

	public static MainUI MV;
	public JFrame frame;
	public JPanel panel;
	public JLabel Logo;
	public JSeparator separator;
	public SlideContainer slideContainer;
	public static JPanel loadingPanel;
	
	/**
	 * Create the application.
	 * @throws URISyntaxException 
	 */
	public MainUI() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 * @throws URISyntaxException 
	 */
	private void initialize() {
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
		this.MV=this;
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(10, 100, 677, 562);
		//frame.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int locationX = dim.width /2-(677/2); 
		int	locationY = dim.height/2-(562/2);
		
		
		frame.setTitle("Client - IBook");
		
		frame.setBounds(locationX,locationY ,677, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		slideContainer = new SlideContainer();
		//slideContainer.setBounds(0, 0, 677, 562);
		//slideContainer.setLayout(null);
		
		Logo = new JLabel("");
		Logo.setBounds(0, 0, 677, 123);
		Logo.setIcon(new GUIimage("MainLogo",Logo.getWidth(),Logo.getHeight()).image);
		
		separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		frame.getContentPane().add(separator);
		
		frame.getContentPane().add(Logo);
		frame.setVisible(true);
		
		
		// Simple indetrminate progress bar
		loadingPanel = new JPanel();
		loadingPanel.setBounds(0, 0, 677, 562);
		loadingPanel.setLayout(null);
		
        WebProgressBar progressBar3 = new WebProgressBar ();
        progressBar3.setBounds(135, 334, 409, 25);
        progressBar3.setIndeterminate ( true );
        progressBar3.setStringPainted ( true );
        progressBar3.setString ( "Please wait..." );
        
        loadingPanel.add(progressBar3);
        
        frame.getContentPane().add(loadingPanel);
		frame.getContentPane().add(slideContainer);
		
		
	//logout automatically when the window is closing
		/**
		frame.addWindowListener(new WindowAdapter() {
		
			@Override
			public void windowClosing(WindowEvent e) {

				frame.setVisible(false);
				
				try {
					LoginEntity entity = MainClass.masterControler.LoginCont.loginEntity;
					if (entity!=null && entity.isLoginOK() && !entity.isLogout()) 
						MainClass.gasclient.sendMessegeToServer(entity);
				} catch (Exception x) {}
				
				System.exit(0);

			}
				
		});**/
    
	}
	public void setView(JPanel panel) { //מתודת החלפת פנלים
		
		if (MV.loadingPanel.isVisible())
			MV.loadingPanel.setVisible(false);
		
		
		
		MV.frame.remove(MV.slideContainer);
	
		MV.slideContainer = new SlideContainer();
		MV.slideContainer.setBounds(0, 0, 677, 562);
		
		MV.frame.getContentPane().add(MV.slideContainer);
		
		MV.slideContainer.add(panel);
		
}



}






/*
 
 
 		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int locationX = dim.width /2-(677/2); 
		int	locationY = dim.height/2-(562/2);
		
		if(GasServer.ChangeTitleBool){
//			frame.setBounds(690, 100, 677, 562);
			frame.setBounds(locationX, locationY, 677, 562);
			frame.setTitle("Server - My Gas");
			GasServer.ChangeTitleBool = false;
		}
		else{ 
			frame.setTitle("Client - My Gas");
//			frame.setBounds(10, 100, 677, 562);
			frame.setBounds(locationX,locationY ,677, 562);
		}
 
 
 
 
 */
