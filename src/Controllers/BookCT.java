package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.JTextField;


//import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit.CollapseAllCommentFoldsAction;

import Entities.BookET;
import Entities.FileEvent;
import Mains.IBookClient;
import Views.BookUI;
import Views.GetBookUI;
import Views.MainUI;
import Views.SearchAdvUI;
import Views.SearchBookUI;
import Views.AddBookUI;
import Views.*;

/**
 * Class that control all the actions about the book entity
 * for example- search book, advanced search book,view book details and purchase books
 *
 */
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
	public ArrayList<String> download;
	
	
	/**
	 * Constructor
	 *  Build a controller that initialize
	 * Add ActionListener to every button in every panels of the search frame
	 * Change the observer from the user controller to the book controller
	 * Get the connection to the server 
	 * @param search - get the JPanel of search book
	 */
	public BookCT(SearchBookUI search){
		this.searchFrame=search;
		bookCT=this;
		searchFrame.btnSearch.addActionListener((ActionListener)this);
		searchFrame.btnAdvancedSearch.addActionListener((ActionListener)this);
		client = IBookClient.getInstance();
		UserCT.userCT.changeObserver(this,UserCT.userCT);
	}
	/* 
	 * Function the recognize events from all the books actions UI
	 * do the action that the event needs and send to the relevant function
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == searchFrame.btnSearch){//check if the fields are empty and if not call to Searchbook function
			if(searchFrame.textField.getText().equals(null)|| searchFrame.textField.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Please insert text");
			}else SearchBook();
			
		}
		if(e.getSource()==searchFrame.btnAdvancedSearch){//Create the advanced search frame
			searchadvFrame= new SearchAdvUI();
			searchadvFrame.btnBack.addActionListener((ActionListener)this);
			searchadvFrame.btnSearch.addActionListener((ActionListener)this);
			MainUI.MV.setView(searchadvFrame);
		}	
		if(bookUI!=null && UserCT.userCT.userET.getConfirm()==1){//check if the user is kind of Ibook reader
			if(e.getSource()==bookUI.btnGetbook){//Create the get book frame
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
			if(e.getSource()==getbookUI.btnDownload){//Call too get book function
				GetBook();
				}
		}
		if(searchadvFrame!=null){
			if(e.getSource()==searchadvFrame.btnBack){//return to the search book frame
				MainUI.MV.setView(searchFrame);
			}
			if(e.getSource()==searchadvFrame.btnSearch){//call to advanced search function
				
				 SearchAdv();
			}
		}
		
	}
	/* function that get the result from the database and recognize the result kind
	 * and set the details depending on the case
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object obj) {
		
		if (obj instanceof String)
			System.out.println(obj);

		else {
			Map<String, Object> map = (HashMap<String, Object>) obj;

			String op = (String) map.get("op");
			
			// what operation was made in the server and how to respond.
			switch (op) {
				case "SearchBook":{//set the result of the search book on the frame table
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
				case "GetBook":{//show message if the user can get the book and if its possible call to download function
								if((int)map.get("obj")==1)JOptionPane.showMessageDialog(null,"successful");
								else if((int)map.get("obj")==2)JOptionPane.showMessageDialog(null,"Failed,Please renew your subscription");
								else if((int)map.get("obj")==3)JOptionPane.showMessageDialog(null,"successful,Your invoice in your box");
								else if((int)map.get("obj")==4)JOptionPane.showMessageDialog(null,"Failed,You got this book before");
								else JOptionPane.showMessageDialog(null,"Failed");
								Download();
								
								break;}
				case "SearchAdv":{//set the result of the search book on the frame table
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
				case "Download":{/*get the book file from the database and set it on the user selected path (download)
								 *transfer the book file from byte array to selected format
								 */
					try{
						System.out.println("update begin");
							FileEvent fileEvent=(FileEvent)map.get("obj");
							if (fileEvent.getStatus().equalsIgnoreCase("Error")) {
								System.out.println("Error occurred ..So exiting");
								break;
								}
							System.out.println("before dst");
							String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
							if (!new File(fileEvent.getDestinationDirectory()).exists()) {
							new File(fileEvent.getDestinationDirectory()).mkdirs();
							}
							System.out.println("after dst");
							File dstFile=new File(outputFile);
							FileOutputStream fileOutputStream= new FileOutputStream(dstFile);
							fileOutputStream.write(fileEvent.getFileData());
							fileOutputStream.flush();
							fileOutputStream.close();
							System.out.println("Output file : " + outputFile + " is successfully saved ");
					}catch (IOException e) {
						e.printStackTrace();
					} 
					UserCT.userCT.changeObserver(UserCT.userCT,this);
					MainUI.MV.setView(UserCT.readerFrame);
					break;
				}	
				
			
			}
			
		}
	}
	
	/**
	 * Function prepares the data and sent to the server
	 * about to search book with details from frame text field
	 */
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
	/**
	 * Function prepares the data and sent to the server
	 * about to search book advanced with details from frame text fields
	 */
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
	/**
	 * Function prepares the data and sent to the server
	 * about to view book frame with book details
	 * check if the user have permission to get the book or only view
	 * @param row - the row that user press with book details
	 * @param flag - check what is the kind of the search regular or advanced
	 */
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
	
	/**
	 * Function prepares the data and sent to the server
	 * about to get specific book that the user want to purchase
	 * set array list with details to the server to check before
	 * the user get the book file
	 */
	public void GetBook(){
		
		download=new ArrayList<String>();
		download.add(""+bookET.getBID());
		download.add(bookET.getBTitle());
		download.add((String)(getbookUI.cbFormat.getSelectedItem()));
		download.add(getbookUI.path.getText()+"\\");
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "GetBook");
		hmap.put("user", UserCT.userCT.userET);
		hmap.put("book", bookET);
		client.handleMessageFromUI(hmap);
	}
	/**
	 * Function prepares the data and sent to the server
	 * about to download the file in the format that the user select
	 * set the path for the file on the client pc in FileEvent class perform
	 */
	public void Download(){

		FileEvent fileEvent=new FileEvent(); 
		fileEvent.setFilename(download.get(0)+"."+download.get(2));
		fileEvent.setBookid(Integer.parseInt(download.get(0)));
		fileEvent.setDestinationDirectory(""+download.get(3));


		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "Download");
		hmap.put("obj", (Object)fileEvent);
		client.handleMessageFromUI(hmap);
	}

}
