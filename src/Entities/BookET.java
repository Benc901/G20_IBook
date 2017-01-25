package Entities;

import java.io.Serializable;
import java.sql.Date;

public class BookET implements Serializable {
		private int bID;
		private String bTitle;
		private String bAuthor;
		private String bLanguage;
		private String bSummary;
		private String bContent;
		private String bKeywords;
		private String bGenre;
		private String bSubject;
		private String bPhoto;
		private Date bPurchaseDate;
		private int bNumOfSearch;
		private int bNumOfPurchace;
		private int bHidden;
		private int bTotalRank;
		private int bGenreRank;
<<<<<<< HEAD
		private int price;
=======

>>>>>>> refs/heads/Gidi_Final

		
		public BookET(){
			
		}
		public BookET(int bID, String bTitle, String bAuthor, String bLanguage, String bSummary, String bContent,
				String bKeywords,String bGenre, String bSubject, String bPhoto, int bNumOfSearch, int bNumOfPurchace,
				int bHidden, int bTotalRank, int bGenreRank,int price) {
			super();
			this.bID = bID;
			this.bTitle = bTitle;
			this.bAuthor = bAuthor;
			this.bLanguage = bLanguage;
			this.bSummary = bSummary;
			this.bContent = bContent;
			this.bKeywords=bKeywords;
			this.bGenre = bGenre;
			this.bSubject = bSubject;
			this.bPhoto = bPhoto;
			this.bNumOfSearch = bNumOfSearch;
			this.bNumOfPurchace = bNumOfPurchace;
			this.bHidden = bHidden;
			this.bTotalRank = bTotalRank;
			this.bGenreRank = bGenreRank;
<<<<<<< HEAD
			this.price=price;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
=======
		}
		public BookET(int bID, String bTitle, Date date) {
			this.bID = bID;
			this.bTitle = bTitle;
			this.bPurchaseDate = date;
>>>>>>> refs/heads/Gidi_Final
		}
		public int getBID() {
			return this.bID;
		}

		public void setBID(int bID) {
			this.bID = bID;
		}

		public String getBTitle() {
			return this.bTitle;
		}

		public void setBTitle(String bTitle) {
			this.bTitle = bTitle;
		}

		public String getBAuthor() {
			return this.bAuthor;
		}

		public void setBAuthor(String bAuthor) {
			this.bAuthor = bAuthor;
		}

		public String getbKeywords() {
			return bKeywords;
		}
		public void setbKeywords(String bKeywords) {
			this.bKeywords = bKeywords;
		}
		public String getBLanguage() {
			return this.bLanguage;
		}

		public void setBLanguage(String bLanguage) {
			this.bLanguage = bLanguage;
		}

		public String getBSummary() {
			return this.bSummary;
		}

		public void setBSummary(String bSummary) {
			this.bSummary = bSummary;
		}

		public String getBContent() {
			return this.bContent;
		}

		public void setBContent(String bContent) {
			this.bContent = bContent;
		}

		public String getBGenre() {
			return this.bGenre;
		}

		public void setBGenre(String bGenre) {
			this.bGenre = bGenre;
		}

		public String getBSubject() {
			return this.bSubject;
		}

		public void setBSubject(String bSubject) {
			this.bSubject = bSubject;
		}

		public String getBPhoto() {
			return this.bPhoto;
		}

		public void setBPhoto(String bPhoto) {
			this.bPhoto = bPhoto;
		}

	

		public int getBNumOfSearch() {
			return this.bNumOfSearch;
		}

		public void setBNumOfSearch(int bNumOfSearch) {
			this.bNumOfSearch = bNumOfSearch;
		}

		public int getBNumOfPurchace() {
			return this.bNumOfPurchace;
		}

		public void setBNumOfPurchace(int bNumOfPurchace) {
			this.bNumOfPurchace = bNumOfPurchace;
		}

		public int isBHidden() {
			return this.bHidden;
		}

		public void setBHidden(int bHidden) {
			this.bHidden = bHidden;
		}

		public int getBTotalRank() {
			return this.bTotalRank;
		}

		public void setBTotalRank(int bTotalRank) {
			this.bTotalRank = bTotalRank;
		}

		public int getBGenreRank() {
			return this.bGenreRank;
		}

		public void setBGenreRank(int bGenreRank) {
			this.bGenreRank = bGenreRank;
		}
		public Date getbPurchaseDate() {
			return bPurchaseDate;
		}
		public void setbPurchaseDate(Date bPurchaseDate) {
			this.bPurchaseDate = bPurchaseDate;
		}

}
