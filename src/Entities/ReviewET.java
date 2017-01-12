package Entities;

import java.io.Serializable;

public class ReviewET implements Serializable {
	private int id;
	private int bookId;
	private String bookName;
	private String author;
	private int userId;
	private String userName;
	private String userPhoto;
	private String bookphoto;
	private String review;
	private int rate;
	private int confirm;
	private int workerId;
	
	

	
	
	public ReviewET(int id, int bookId, String bookName,String author, int userId, String userName, String userPhoto,String bookphoto,String review,
			int rate, int confirm, int workerId) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.bookName = bookName;
		this.author=author;
		this.userId = userId;
		this.userName = userName;
		this.userPhoto = userPhoto;
		this.bookphoto=bookphoto;
		this.review = review;
		this.rate = rate;
		this.confirm = confirm;
		this.workerId = workerId;
	}

	public ReviewET(){
		
	}

	public ReviewET(int bookId,String bookName,int userId,String userName,String userPhoto,String review,int rate){
		this.bookId = bookId;
		this.bookName = bookName;
		this.userId = userId;
		this.userName = userName;
		this.userPhoto = userPhoto;
		this.review = review;
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	
	public String getBookphoto() {
		return bookphoto;
	}

	public void setBookphoto(String bookphoto) {
		this.bookphoto = bookphoto;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}

	public int getWorkerId() {
		return workerId;
	}

	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}



	
}
