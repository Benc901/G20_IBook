package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import Entities.BookET;
import Entities.GenreET;
import Entities.UserET;
import Mains.IBookClient;
import Views.AddBookUI;
import Views.AddUserUI;
import Views.LibririanUI;
import Views.MainUI;
import Views.RemoveBookUI;
import Views.UpdateBookUI;
import Views.inventoryUpdateUI;

public class LibrarianCT implements Observer, ActionListener{
	
	public static LibrarianCT librarianCT;
	public static IBookClient client;
	public static LibririanUI libririanFrame;
	public static AddUserUI adduserFrame;
	public static inventoryUpdateUI IUpdateFrame;
	public static AddBookUI AddBFrame;
	public static RemoveBookUI RemoveBFrame;
	public static UpdateBookUI UpdateBFrame;
	public static UserET userET;
	public static BookET bookET;
	public static ArrayList<GenreET> genresET;
	
	public LibrarianCT(LibririanUI frame) {
		// TODO Auto-generated constructor stub
		librarianCT=this;
		client = IBookClient.getInstance();
		this.libririanFrame=frame;
		BringGandS();
		libririanFrame.btnAdduser.addActionListener((ActionListener)this);
		libririanFrame.btnIupdate.addActionListener((ActionListener)this);
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
		if(e.getSource()==libririanFrame.btnIupdate){
			IUpdateFrame= new inventoryUpdateUI();
			IUpdateFrame.btnBack.addActionListener((ActionListener)this);
			IUpdateFrame.btnAddBook.addActionListener((ActionListener)this);
			IUpdateFrame.btnRBook.addActionListener((ActionListener)this);
			IUpdateFrame.btnUBook.addActionListener((ActionListener)this);
			MainUI.MV.setView(IUpdateFrame);
		}
		if(IUpdateFrame!=null)
		{
			if(e.getSource()==IUpdateFrame.btnBack)
				MainUI.MV.setView(libririanFrame);
		}
		if(IUpdateFrame!=null)
		{
			if(e.getSource()==IUpdateFrame.btnUBook)
			{
				UpdateBFrame=new UpdateBookUI();
				UpdateBFrame.btnBack.addActionListener((ActionListener)this);
				UpdateBFrame.btnChoose.addActionListener((ActionListener)this);
				UpdateBFrame.btnUpdate.addActionListener((ActionListener)this);
				MainUI.MV.setView(UpdateBFrame);
			}
			else if(e.getSource()==IUpdateFrame.btnRBook)
			{
				RemoveBFrame=new RemoveBookUI();
				RemoveBFrame.btnBack.addActionListener((ActionListener)this);
				
				MainUI.MV.setView(RemoveBFrame);
			}
			else if(e.getSource()==IUpdateFrame.btnAddBook)
			{
				AddBFrame=new AddBookUI(genresET);
				AddBFrame.btnBack.addActionListener((ActionListener)this);
				MainUI.MV.setView(AddBFrame);
			}
		}
		if(UpdateBFrame!=null)
		{
			if(e.getSource()==UpdateBFrame.btnBack)
				MainUI.MV.setView(IUpdateFrame);
			if(e.getSource()==UpdateBFrame.btnChoose)
				BringBook(UpdateBFrame.getTxtBid());
			if(e.getSource()==UpdateBFrame.btnUpdate)
				UpdateBook();
		}
		if(RemoveBFrame!=null)
		{
			if(e.getSource()==RemoveBFrame.btnBack)
				MainUI.MV.setView(IUpdateFrame);
		}
		if(AddBFrame!=null)
		{
			if(e.getSource()==AddBFrame.btnBack)
				MainUI.MV.setView(IUpdateFrame);
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
		int massage;
		if (obj instanceof String)
			System.out.println(obj);
		else{
		Map<String, Object> map = (HashMap<String, Object>) obj;

		String op = (String) map.get("op");
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
				JOptionPane.showMessageDialog(null, "The user name already in DB", "The user name already in DB", JOptionPane.INFORMATION_MESSAGE);
				adduserFrame.clearFields();	
			}
			else if ((int)map.get("obj")==1) 
			{
				JOptionPane.showMessageDialog(null, "Insert user to DB", "Insert user to DB", JOptionPane.INFORMATION_MESSAGE);
				adduserFrame.clearFields();
			}
			break;
		case "BringBook":
			if (map.get("obj") instanceof Integer)
			{
				massage=(int)map.get("obj");
				if(massage==0){
					JOptionPane.showMessageDialog(null, "The book id not exist in DB", "The book id not exist in DB", JOptionPane.INFORMATION_MESSAGE);
					UpdateBFrame.clearFields();
				}
				if(massage==-1)
				{
					JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
					UpdateBFrame.clearFields();
				}	
			}
			else {
				bookET = (BookET)map.get("obj");
				UpdateBFrame.getTxtTitle().setText(bookET.getBTitle());
				UpdateBFrame.getTxtAuthor().setText(bookET.getBAuthor());
				UpdateBFrame.getTxtLan().setText(bookET.getBLanguage());
				UpdateBFrame.getTxtContent().setText(bookET.getBContent());
				UpdateBFrame.getTxtKwords().setText(bookET.getbKeywords());
				UpdateBFrame.getTxtASummary().setText(bookET.getBSummary());
			}
			break;
		case "UpdateBook":
			if ((boolean)map.get("obj") == false) 
			{
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
				UpdateBFrame.clearFields();	
			}
			else if((boolean)map.get("obj") == true)
			{
				JOptionPane.showMessageDialog(null, "The book is update in DB", "The book is update in DB", JOptionPane.INFORMATION_MESSAGE);
				UpdateBFrame.clearFields();	
			}
			break;
		case "BringGandS":
			if(map.get("obj") instanceof Integer){
				JOptionPane.showMessageDialog(null, "Fail to connect the DB", "Fail to connect the DB", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				genresET=(ArrayList<GenreET>)map.get("obj");
				System.out.println(genresET.size());
			}
			break;
		}
		
	}
	}
	public void BringBook(int Bid)
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "BringBook");
		hmap.put("obj", Bid);
		
		client.handleMessageFromUI(hmap);
	}
	public void AddUser(String UserName,String UserPassword,String FirstN,String LastN,String eMail)
	{
		userET =new UserET(UserName,UserPassword);
		userET.setEmail(eMail);
		userET.setFirstName(FirstN);
		userET.setLastName(LastN);
		userET.setPermission(1);
		
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "AddUser");
		hmap.put("obj", userET);
		
		client.handleMessageFromUI(hmap);
	}
	public void UpdateBook()
	{
		bookET.setBTitle(UpdateBFrame.getTxtTitle().getText());
		bookET.setBAuthor(UpdateBFrame.getTxtAuthor().getText());
		bookET.setBLanguage(UpdateBFrame.getTxtLan().getText());
		bookET.setBContent(UpdateBFrame.getTxtContent().getText());
		bookET.setbKeywords(UpdateBFrame.getTxtKwords().getText());
		bookET.setBSummary(UpdateBFrame.getTxtASummary().getText());
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "UpdateBook");
		hmap.put("obj", bookET);
		
		client.handleMessageFromUI(hmap);
	}
	public void BringGandS()
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "BringGandS");
		
		client.handleMessageFromUI(hmap);
	}
	public void BringBooks()
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "BringBooks");
		hmap.put("obj", "");
		
		client.handleMessageFromUI(hmap);
	}
	public void DeleteBook(int Bid)
	{
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "DeleteBook");
		hmap.put("obj", Bid);
		
		client.handleMessageFromUI(hmap);
	}
	public void AddBook()
	{
		BookET NewBookET = new BookET(0, AddBFrame.getTxtTitle().getText(), AddBFrame.getTxtAuthor().getText(), AddBFrame.getTxtLan().getText(), AddBFrame.getTxtASummary().getText(), AddBFrame.getTxtContent().getText(), AddBFrame.getTxtKwords().getText(), (String)AddBFrame.getComboBoxGenres().getSelectedItem(),(String)AddBFrame.getComboBoxSubject().getSelectedItem(), "BookProfile", 0, 0, 0, 0, 0);
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "AddBook");
		hmap.put("obj", NewBookET);
		
		client.handleMessageFromUI(hmap);
	}

}
