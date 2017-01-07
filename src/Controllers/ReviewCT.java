package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import Entities.BookET;
import Entities.ReviewET;
import Mains.IBookClient;
import Views.MainUI;
import Views.ReviewUI;
import Views.SearchReviewUI;

public class ReviewCT implements Observer, ActionListener {
	
	public static IBookClient client;
	public static ReviewCT reviewCT;
	public static SearchReviewUI searchFrame;
	public ArrayList<ReviewET> reviews;
	
	public ReviewCT(SearchReviewUI search){
		client = IBookClient.getInstance();
		this.reviewCT=this;
		this.searchFrame=search;
		searchFrame.btnSearch.addActionListener((ActionListener)this);
		UserCT.userCT.changeObserver(this,UserCT.userCT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchFrame.btnSearch){
			SearchReview();
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
			case "SearchReview":{
				for(int i=0 ; i<searchFrame.model.getRowCount() ; i++) searchFrame.model.removeRow(i);
				System.out.println("now here");
				reviews=(ArrayList<ReviewET>)map.get("arr");
				System.out.println("here: "+reviews.size());
				for(int i=0 ; i<reviews.size(); i++){
					searchFrame.model.addRow(new Object[] {
							reviews.get(i).getId(),reviews.get(i).getBookName(),
							reviews.get(i).getAuthor(),reviews.get(i).getUserName(),
							reviews.get(i).getRate()
					
				});
				}
			}
			}
		}
		
	}
	
	public void SearchReview(){
		System.out.println("SearchReview");
		Map<String, Object> hmap = new HashMap<String, Object>();
		String selection;
	
		switch((String)searchFrame.comboBox.getSelectedItem()){
			case "Title": selection=new String("3"); break;
			case "Author": selection=new String("4"); break;
			case "Keywords": selection=new String("8"); break;
			
			default:selection=new String("3");
			//new String[] {"Language", "Subject", "Genre", "Author", "Name", "Id"}))
		}
		hmap.put("op", "SearchReview");
		hmap.put("text",searchFrame.GetText());
		hmap.put("cb",selection);
		client.handleMessageFromUI(hmap);
	}
	
	public void viewReview(int row){
		MainUI.MV.setView(new ReviewUI(reviews.get(row)));
	}
}