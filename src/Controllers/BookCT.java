package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JTable;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit.CollapseAllCommentFoldsAction;

import Entities.BookET;
import Mains.IBookClient;
import Views.BookUI;
import Views.MainUI;
import Views.SearchBookUI;

public class BookCT implements Observer, ActionListener{
	
	public static IBookClient client;
	public static BookCT bookCT;
	public static SearchBookUI searchFrame;
	public ArrayList<BookET> books;
	
	public BookCT(SearchBookUI search){
		this.searchFrame=search;
		bookCT=this;
		searchFrame.btnSearch.addActionListener((ActionListener)this);
		client = IBookClient.getInstance();
		UserCT.userCT.changeObserver(this,UserCT.userCT);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == searchFrame.btnSearch){
			SearchBook();
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
				for(int i=0 ; i<searchFrame.model.getRowCount() ; i++) searchFrame.model.removeRow(i);
				System.out.println("now here");
				books=(ArrayList<BookET>)map.get("arr");
				//ArrayList<BookET> returnObj=(ArrayList<BookET>)map.get("arr");
				for(int i=0 ; i<books.size(); i++){
					searchFrame.model.addRow(new Object[] {
							books.get(i).getBID(),books.get(i).getBTitle(),
							books.get(i).getBAuthor(),books.get(i).getBGenre(),
							books.get(i).getBSubject(),books.get(i).getBLanguage(),
							books.get(i).getBNumOfPurchace()
					
				});
				}
			}
			}
		}
	}
	
	public void SearchBook(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		String selection;
	
		switch((String)searchFrame.comboBox.getSelectedItem()){
			case "Id": selection=new String("1"); break;
			case "Name": selection=new String("2"); break;
			case "Author": selection=new String("3"); break;
			case "Genre": selection=new String("7"); break;
			case "Subject": selection=new String("8"); break;
			case "Language": selection=new String("4"); break;
			default:selection=new String("1");
			//new String[] {"Language", "Subject", "Genre", "Author", "Name", "Id"}))
		}
		hmap.put("op", "SearchBook");
		hmap.put("text",searchFrame.GetText());
		hmap.put("cb",selection);
		client.handleMessageFromUI(hmap);
	}
	
	public void viewBook(int row){
		MainUI.MV.setView(new BookUI(books.get(row)));
	}

}
