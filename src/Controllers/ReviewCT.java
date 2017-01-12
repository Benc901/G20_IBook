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
			if(searchFrame.GetText().equals(null) || searchFrame.GetText().equals(""))
				JOptionPane.showMessageDialog(null,"Please insert text");
			else 	SearchReview();
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
							reviews.get(i).getRate()
					
				});
				}
			}
			}
		}
		
	}
	
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
	
	public void viewReview(int row){
		MainUI.MV.setView(new ReviewUI(reviews.get(row)));
	}
}
