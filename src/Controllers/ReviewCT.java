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

public class ReviewCT implements Observer, ActionListener {
	
	public static IBookClient client;
	public static ReviewCT reviewCT;
	public static SearchReviewUI searchFrame;
	public static PublishReviewUI publishFrame;
	public static HashMap booklist;
	public ArrayList<ReviewET> reviews;
	
	public ReviewCT(SearchReviewUI search){
		client = IBookClient.getInstance();
		this.reviewCT=this;
		this.searchFrame=search;
		searchFrame.btnSearch.addActionListener((ActionListener)this);
		UserCT.userCT.changeObserver(this,UserCT.userCT);
	}
	
	public ReviewCT(PublishReviewUI publish){
		client = IBookClient.getInstance();
		this.reviewCT=this;
		this.publishFrame=publish;
		publishFrame.btnPublish.addActionListener((ActionListener)this);
		UserCT.userCT.changeObserver(this,UserCT.userCT);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(searchFrame!=null){
		if (e.getSource() == searchFrame.btnSearch){
			if(searchFrame.GetText().equals(null) || searchFrame.GetText().equals(""))
				JOptionPane.showMessageDialog(null,"Please insert text");
			else 	SearchReview();
		}}
		if(publishFrame!=null){
		if (e.getSource() == publishFrame.btnPublish){
			PublishReview();

		}}
		
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
							reviews.get(i).getRate()		});
					}
								break;}
			
			case "PublishReview":{
				if((int)map.get("obj")==0)JOptionPane.showMessageDialog(null,"Faild publish review");
				if((int)map.get("obj")==1)JOptionPane.showMessageDialog(null,"successfully publish review application");
				UserCT.userCT.changeObserver(UserCT.userCT, this);
				MainUI.MV.setView(UserCT.readerFrame);
					break;}
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
