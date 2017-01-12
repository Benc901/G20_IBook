package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import Entities.UserET;
import Mains.IBookClient;
import Views.AddUserUI;
import Views.LibririanUI;
import Views.MainUI;

public class LibrarianCT implements Observer, ActionListener{
	
	public static LibrarianCT librarianCT;
	public static IBookClient client;
	public static LibririanUI libririanFrame;
	public static AddUserUI adduserFrame;
	
	
	
	
	public static UserET userET;
	
	public LibrarianCT(LibririanUI frame) {
		// TODO Auto-generated constructor stub
		librarianCT=this;
		client = IBookClient.getInstance();
		this.libririanFrame=frame;
		libririanFrame.btnAdduser.addActionListener((ActionListener)this);
		UserCT.userCT.changeObserver(this,UserCT.userCT);
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==libririanFrame.btnAdduser){
			adduserFrame = new AddUserUI();
			adduserFrame.btnBack.addActionListener((ActionListener)this);
			adduserFrame.btnAddUser.addActionListener((ActionListener)this);
			MainUI.MV.setView(adduserFrame);
		}	
		if(adduserFrame!=null){
		if(e.getSource()==adduserFrame.btnBack){
			MainUI.MV.setView(libririanFrame);
		}
		}
		if(adduserFrame!=null)
		{
			if(e.getSource()==adduserFrame.btnAddUser)
			{
				AddUser(adduserFrame.GetUserName(), adduserFrame.GetUserPassword(), adduserFrame.GetFirstName(), adduserFrame.GetLastName(), adduserFrame.GetEmail());
			}
		}
	}

	@Override
	public void update(Observable o, Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof String)
			System.out.println(obj);
		else{
		Map<String, Object> map = (HashMap<String, Object>) obj;

		String op = (String) map.get("op");
		JOptionPane.showMessageDialog(null, "Insert user to DB", "Insert user to DB", JOptionPane.ERROR_MESSAGE);
		adduserFrame.clearFields();
		switch (op) 
		{
		case "AddUser": 
			if ((int)map.get("obj") == -1) 
			{
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
				adduserFrame.clearFields();	
			}
			else if((int)map.get("obj") == 0)
			{
				JOptionPane.showMessageDialog(null, "The user name already in DB", "The user name already in DB", JOptionPane.ERROR_MESSAGE);
				adduserFrame.clearFields();	
			}
			else if ((int)map.get("obj")==1) 
			{
				JOptionPane.showMessageDialog(null, "Insert user to DB", "Insert user to DB", JOptionPane.ERROR_MESSAGE);
				adduserFrame.clearFields();
			}
	}
	}
	}
	public void AddUser(String UserName,String UserPassword,String FirstN,String LastN,String eMail)
	{
		userET =new UserET(UserName,UserPassword);
		userET.setEmail(eMail);
		userET.setFirstName(FirstN);
		userET.setLastName(LastN);
		userET.setPermission(1);
		
		//JOptionPane.showMessageDialog(null, "The "+ userET.getEmail(), "The "+ userET.getEmail(), JOptionPane.ERROR_MESSAGE);
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "AddUser");
		hmap.put("obj", userET);
		
		client.handleMessageFromUI(hmap);
	}
	

}
