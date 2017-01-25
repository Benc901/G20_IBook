package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
<<<<<<< HEAD
import javax.swing.JTable;
import javax.swing.JTextField;
=======
>>>>>>> refs/heads/Gidi_Final

//import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit.CollapseAllCommentFoldsAction;

import Entities.BookET;
import Mains.IBookClient;
import Views.BookUI;
import Views.GetBookUI;
import Views.MainUI;
import Views.SearchAdvUI;
import Views.SearchBookUI;
import Views.AddBookUI;
import Views.*;

public class BookCT implements Observer, ActionListener{
	
	public static IBookClient client;
	public static BookCT bookCT;
	public static SearchBookUI searchFrame;
	public static SearchAdvUI searchadvFrame;
	public static BookUI bookUI;
	public static GetBookUI getbookUI;
	public ArrayList<BookET> books;
	public static BookET bookET;
	public static int flag;
	
	
	public BookCT(SearchBookUI search){
		this.searchFrame=search;
		bookCT=this;
		searchFrame.btnSearch.addActionListener((ActionListener)this);
		searchFrame.btnAdvancedSearch.addActionListener((ActionListener)this);
		client = IBookClient.getInstance();
		UserCT.userCT.changeObserver(this,UserCT.userCT);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == searchFrame.btnSearch){
			if(searchFrame.textField.getText().equals(null)|| searchFrame.textField.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Please insert text");
			}else SearchBook();
			
		}
		if(e.getSource()==searchFrame.btnAdvancedSearch){
			searchadvFrame= new SearchAdvUI();
			searchadvFrame.btnBack.addActionListener((ActionListener)this);
			searchadvFrame.btnSearch.addActionListener((ActionListener)this);
			MainUI.MV.setView(searchadvFrame);
		}	
		if(bookUI!=null && UserCT.userCT.userET.getConfirm()==1){
			if(e.getSource()==bookUI.btnGetbook){
				getbookUI=new GetBookUI();
				getbookUI.btnBack.addActionListener((ActionListener)this);
				getbookUI.btnDownload.addActionListener((ActionListener)this);
				MainUI.MV.setView(getbookUI);
				}
		}
		if(getbookUI!=null){
			if(e.getSource()==getbookUI.btnBack){
				MainUI.MV.setView(bookUI);
				}
			if(e.getSource()==getbookUI.btnDownload){
				download();
				}
		}
		if(searchadvFrame!=null){
			if(e.getSource()==searchadvFrame.btnBack){
				MainUI.MV.setView(searchFrame);
			}
			if(e.getSource()==searchadvFrame.btnSearch){
				
				 SearchAdv();
			}
		}
		
	}

	@Override
	public void update(Observable arg0, Object obj) {
		
		if (obj instanceof String)
			System.out.println(obj);

		else {
			Map<String, Object> map = (HashMap<String, Object>) obj;

			String op = (String) map.get("op");
			
			// what operation was made in the server and how to respond.
			switch (op) {
				case "SearchBook":{
					if (searchFrame.model.getRowCount() > 0) {
	                    for (int i = searchFrame.model.getRowCount() - 1; i > -1; i--) {
	                    	searchFrame.model.removeRow(i);
	                    }
	                }
	
					books=(ArrayList<BookET>)map.get("arr");
					for(int i=0 ; i<books.size(); i++){
						searchFrame.model.addRow(new Object[] {
								books.get(i).getBID(),books.get(i).getBTitle(),
								books.get(i).getBAuthor(),books.get(i).getBGenre(),
								books.get(i).getBLanguage(),books.get(i).getBNumOfPurchace()});
						}
					}break;
				case "GetBook":{if((int)map.get("obj")==1)JOptionPane.showMessageDialog(null,"successful");
								else if((int)map.get("obj")==2)JOptionPane.showMessageDialog(null,"Failed,Please renew your subscription");
								else if((int)map.get("obj")==3)JOptionPane.showMessageDialog(null,"successful,Your invoice in your box");
								else if((int)map.get("obj")==4)JOptionPane.showMessageDialog(null,"Failed,You got this book before");
								else JOptionPane.showMessageDialog(null,"Failed");
								UserCT.userCT.changeObserver(UserCT.userCT,this);
								MainUI.MV.setView(UserCT.readerFrame);
								break;}
				case "SearchAdv":{
					if (searchadvFrame.model.getRowCount() > 0) {
	                    for (int i = searchadvFrame.model.getRowCount() - 1; i > -1; i--) {
	                    	searchadvFrame.model.removeRow(i);
	                    }
	                }
	
					books=(ArrayList<BookET>)map.get("arr");
					for(int i=0 ; i<books.size(); i++){
						searchadvFrame.model.addRow(new Object[] {
								books.get(i).getBID(),books.get(i).getBTitle(),
								books.get(i).getBAuthor(),books.get(i).getBGenre(),
								books.get(i).getBLanguage(),books.get(i).getBNumOfPurchace()});
						}
					}break;
				case "ViewBook":break;
					
				
			
			}
			
		}
	}
	
	public void SearchBook(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		
		ArrayList <Integer> selected=new ArrayList<Integer>();
		if(searchFrame.chckbxTitle.isSelected())selected.add(2);
		if(searchFrame.chckbxAuthor.isSelected())selected.add(3);
		if(searchFrame.chckbxSummery.isSelected())selected.add(5);
		if(searchFrame.chckbxGenre.isSelected())selected.add(20);
		if(searchFrame.checkBoxLanguage.isSelected())selected.add(4);
		if(searchFrame.chckbxKeywords.isSelected())selected.add(7);
		if(selected.size()==0){
			JOptionPane.showMessageDialog(null,"Please select option");
		}else{	
			hmap.put("op", "SearchBook");
			hmap.put("text",searchFrame.GetText());
			hmap.put("cb",selected);
			client.handleMessageFromUI(hmap);
		}
	}
	public void SearchAdv(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		ArrayList<String> tf=new ArrayList<String>();
		
		tf.add(searchadvFrame.textField_Title.getText());
		tf.add(searchadvFrame.textField_Author.getText());
		tf.add(searchadvFrame.textField_Language.getText());
		tf.add(searchadvFrame.textField_Summery.getText());
		tf.add(searchadvFrame.textField_Genere.getText());
		tf.add(searchadvFrame.textField_Keywords.getText());
		
		hmap.put("op","SearchAdv");
		hmap.put("obj",tf);
		client.handleMessageFromUI(hmap);
	}
	public void viewBook(int row,int flag){
		this.flag=flag;
		bookET=books.get(row);
		bookUI=new BookUI(bookET);
		if(bookUI!=null && UserCT.userCT.userET.getConfirm()==1 && UserCT.userCT.userET.getPermission()<5){
			bookUI.btnGetbook.addActionListener((ActionListener)this);}
		MainUI.MV.setView(bookUI);
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op","ViewBook");
		hmap.put("obj",bookET.getBID());
		client.handleMessageFromUI(hmap);
		
	}
	
	public void download(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "GetBook");
		hmap.put("user", UserCT.userCT.userET);
		hmap.put("book", bookET);
		client.handleMessageFromUI(hmap);
	}

}
