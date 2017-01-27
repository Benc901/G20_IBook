package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import Entities.ReviewET;
import Mains.IBookClient;
import Views.MainUI;
import Views.PublishReviewUI;
import Views.ReviewUI;
import Views.SearchReviewUI;

/**
 * Class that control all the actions about the review entity
 * for example- search review, publish review.
 *
 */
public class ReviewCT implements Observer, ActionListener {
	
	public static IBookClient client;
	public static ReviewCT reviewCT;
	public static SearchReviewUI searchFrame;
	public static PublishReviewUI publishFrame;
	public static HashMap booklist;
	public ArrayList<ReviewET> reviews;
	
	/**
	 * Constructor
	 *  Build a controller that initialize
	 * Add ActionListener to every button in every panels of the search review
	 * Change the observer from the user controller to the review controller
	 * Get the connection to the server 
	 * @param search -get the JPanel of search review
	 */
	public ReviewCT(SearchReviewUI search){
		client = IBookClient.getInstance();
		this.reviewCT=this;
		this.searchFrame=search;
		searchFrame.btnSearch.addActionListener((ActionListener)this);
		UserCT.userCT.changeObserver(this,UserCT.userCT);
	}
	
	/**
	 * Constructor
	 * Build a controller that initialize
	 * Add ActionListener to every button in every panels of the publish review
	 * Change the observer from the user controller to the review controller
	 * Get the connection to the server 
	 * @param publish -get the JPanel of publish review
	 */
	public ReviewCT(PublishReviewUI publish){
		client = IBookClient.getInstance();
		this.reviewCT=this;
		this.publishFrame=publish;
		publishFrame.btnPublish.addActionListener((ActionListener)this);
		UserCT.userCT.changeObserver(this,UserCT.userCT);
	}
	/* 
	 * Function the recognize events from all the review actions UI
	 * do the action that the event needs and send to the relevant function
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(searchFrame!=null){
		if (e.getSource() == searchFrame.btnSearch){//check if the fields are empty and if not call to search review function
			if(searchFrame.GetText().equals(null) || searchFrame.GetText().equals(""))
				JOptionPane.showMessageDialog(null,"Please insert text");
			else 	SearchReview();
		}}
		if(publishFrame!=null){
		if (e.getSource() == publishFrame.btnPublish){//call to publish review after user fill the fields
			PublishReview();

		}}
		
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
			case "SearchReview":{//set the result of the search review on the frame table
				if (searchFrame.model.getRowCount() > 0) {
                    for (int i = searchFrame.model.getRowCount() - 1; i > -1; i--) {
                    	searchFrame.model.removeRow(i);
                    }
                }
				reviews=(ArrayList<ReviewET>)map.get("arr");
				for(int i=0 ; i<reviews.size(); i++){
					searchFrame.model.addRow(new Object[] {
							reviews.get(i).getId(),reviews.get(i).getBookName(),
							reviews.get(i).getAuthor(),reviews.get(i).getUserName(),
							reviews.get(i).getRate()		});
					}
								break;}
			
			case "PublishReview":{//show message if the publish review application is success or failed
				if((int)map.get("obj")==0)JOptionPane.showMessageDialog(null,"Faild publish review");
				if((int)map.get("obj")==1)JOptionPane.showMessageDialog(null,"successfully publish review application");
				UserCT.userCT.changeObserver(UserCT.userCT, this);
				MainUI.MV.setView(UserCT.readerFrame);
					break;}
			}
		}
		
	}
	
	/**
	 * Function prepares the data and sent to the server
	 * about to search review with details from frame text field
	 * set the details about the selected field that user want to search by him
	 */
	public void SearchReview(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		String selection;
	
		switch((String)searchFrame.comboBox.getSelectedItem()){
			case "Title": selection=new String("3"); break;
			case "Author": selection=new String("4"); break;
			case "Keywords": selection=new String("8"); break;
			
			default:selection=new String("3");
		
		}
		hmap.put("op", "SearchReview");
		hmap.put("text",searchFrame.GetText());
		hmap.put("cb",selection);
		client.handleMessageFromUI(hmap);
	}
	
	/**
	 * Function prepares the data and sent to the server
	 * about to show review details
	 * set frame of the selected book
	 * @param row - the line that user pressed of the review list
	 */
	public void viewReview(int row){
		MainUI.MV.setView(new ReviewUI(reviews.get(row)));
	}
	
	/**
	 * Function prepares the data and sent to the server
	 * about to publish review with details from frame text fields
	 * set the details of the review that user enter
	 */
	public void PublishReview(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		ArrayList <String>book= (ArrayList)booklist.get("String");
		ArrayList <Integer>bookid= (ArrayList)booklist.get("int");
		int index=book.indexOf(publishFrame.comboBox.getSelectedItem());
		int id=bookid.get(index);
		ReviewET review=new ReviewET(id,(String)publishFrame.comboBox.getSelectedItem(),UserCT.userCT.userET.getId(),UserCT.userCT.userET.getUserName(),UserCT.userCT.userET.getPhoto(),publishFrame.textArea.getText(),Integer.parseInt((String)publishFrame.comboBox_1.getSelectedItem()));
		hmap.put("op", "PublishReview");
		hmap.put("obj",review);
		client.handleMessageFromUI(hmap);
	}
}
