package Fixtures;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import Views.AddBookUI;
import Views.LibririanUI;
import Views.LoginUI;
import Views.PublishReviewUI;
import Views.SearchReviewUI;
import iBookServer.IBookServer;
import iBookServer.mysqlConnection;
import iBookServer.serverUI;
import Controllers.LibrarianCT;
import Controllers.ReviewCT;
import Controllers.UserCT;
import Entities.BookET;
import Entities.FileEvent;
import Entities.GenreET;
import Entities.ReviewET;
import Entities.UserET;
import Mains.Main;
import fit.ActionFixture;

public class PublishReviewTest extends ActionFixture {
	
	final public static int DEFAULT_PORT = 5555;
	private LoginUI loginFrame;
	private static ReviewCT reviewCT ;
	private static PublishReviewUI publishFrameTest;
	private static UserCT userCoT;
	private ReviewET review;
	
	public PublishReviewTest()
	{
		String host="localhost";
		
		loginFrame = new LoginUI(host, DEFAULT_PORT);
		userCoT=UserCT.userCT;
		userCoT.GetLoginF().setUserName("shany");
		userCoT.GetLoginF().setPassword("1234");
		userCoT.GetLoginF().btnLogin.doClick();
		publishFrameTest = new PublishReviewUI();
		reviewCT = new ReviewCT(publishFrameTest);
		reviewCT.publishFrame = publishFrameTest;
		review = new ReviewET();
	}
	
	public void setBookId(int bookId){
		review.setBookId(bookId);;
	}
	public void setBookName(String bookName){
		review.setBookName(bookName);
	}
	public void setUserId(int userID){
		review.setUserId(userID);
	}
	public void setUserName(String userID){
		review.setUserName(userID);
	}
	public void setUserPhoto(String userID){
		review.setUserPhoto(userID);
	}
	public void setReview(String review1) {
		review.setReview(review1);
	}
	public void setRate(int rate) {
		review.setRate(rate);
	}

		

	public boolean checkPublishReviewTest()
	{	
		review.setBookId(1);
		review.setBookName("Robin hobb");
		review.setUserId(2);
		review.setUserName("Shany");
		review.setUserPhoto("null");
		review.setReview("Good Book !!!");
		review.setRate(4);
		
		reviewCT.PublishReview(review);
		
		
		if(reviewCT.res == 1)
			return true;
		else 
			return false;
	}
}
