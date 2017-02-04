package iBookServer;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Blob;

import Entities.BookET;
import Entities.FileEvent;
import Entities.GenreET;
import Entities.ReaderET;
import Entities.ReviewET;
import Entities.SubjectET;
import Entities.UserET;
import ocsf.server.ConnectionToClient;

/**
 *class of the connection to the sql data base
 *
 */
/**
 * @author 1
 *
 */
/**
 * @author 1
 *
 */
public class mysqlConnection {
	Connection con;
	private serverUI window;

	final private String url = "jdbc:mysql://localhost:3306/test";
	final private String username;
	final private String password;
	
	

	/**
	 * @param window
	 * @param un
	 * @param pw
	 */
	public mysqlConnection(serverUI window,String un,String pw) {
		username=new String(un);
		password=new String(pw);
		boolean catched=false;
		this.window = window;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();//Register for the jdbc driver.
		} catch (Exception ex) {/* handle the error */
		}

		try {
			con = DriverManager.getConnection(url, username, password);
			display("SQL Server connecton created.");
		} catch (SQLException ex) {/* handle any errors */
			display("SQLException: " + ex.getMessage());
			catched=true;
		}
		if(!catched){//set on the server frame the status of the sql connection 
			
			  window.lblSQL.setText("Online");
			  window.lblSQL.setForeground(Color.GREEN);
		}else{
			window.lblSQL.setText("Offline");
			  window.lblSQL.setForeground(Color.RED);
		}
	}
	
/**Function that get the user entitiy with user name and password
 * and check if the user exist and check if the user still connect
 * and return the correct message
 * if the user exist and not online , we set his status to online 
 * and build all the other fields of the user return the full user entity
 * @param obj - user entity with user name and password field only
 * @return User entity or number that mean specific messaage tothe client about failed login
 */
public Object login(Object obj) {
		
		UserET returnObj = (UserET) obj;
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM user WHERE userName = ? AND passWord = ?");
			pStmt.setString(1, returnObj.getUserName());
			pStmt.setString(2, returnObj.getPassWord());
			ResultSet rs = pStmt.executeQuery();

			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No user
										// found).
				display("no user in the ResultSet of login.");
				return "3";

			} else {
				pStmt = con
						.prepareStatement("SELECT * FROM user WHERE userName = ? AND passWord = ? AND status = 0");
				pStmt.setString(1, returnObj.getUserName());
				pStmt.setString(2, returnObj.getPassWord());
				rs = pStmt.executeQuery();

				if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
											// user found).
					display("user is already logged in the system.");
					return "1";
				}

				else if (rs.next()) { // Build the entity and return it to the
							returnObj.SetFromSQL(rs.getInt(1),rs.getInt(4),rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10));
										// server and then to the client.
					rs.close();
					
					PreparedStatement rStmt = con
							.prepareStatement("UPDATE user SET status = 1 WHERE userName = ?");
					rStmt.setString(1, returnObj.getUserName());
					rStmt.executeUpdate();

					return returnObj;
				}
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
		
	}// End login	

/**Recover Password by user name
 * Function that get user entity with only user name field and check if the user name exist
 * if he exist we set his email and password to the user entity instance
 * and send him the password to the mail
 * @param obj - user entity with username field inside
 * @return user entity or number that mean message to the client about wrong user names
 */
public Object RecoverPassword(Object obj) {

	UserET returnObj=(UserET)obj;
	try {
		PreparedStatement pStmt = con
				.prepareStatement("SELECT * FROM user WHERE userName=?");
		pStmt.setString(1, returnObj.getUserName());
		ResultSet rs = pStmt.executeQuery();
		if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
			// user found).
			display("Failed recover");
			return 0;
		}
		else{
			rs.next();
			returnObj.setEmail(rs.getString(8));
			returnObj.setPassWord(rs.getString(3));
		}
		rs.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return returnObj;
}
	
/**Function that logout the user from the system and set him offline status
 * @param obj - User entity
 * @return - message if the logout success
 */
public Object logout(Object obj) {
	UserET returnObj = (UserET) obj;
	try {
		PreparedStatement rStmt = con.prepareStatement("UPDATE user SET status = 0 WHERE userName = ?");
		rStmt.setString(1, returnObj.getUserName());
		rStmt.executeUpdate();
		rStmt.close();
		display(returnObj.getUserName() + " logged out from the system.");
		return "User logged out of the system";
	} catch (SQLException e) {
		e.printStackTrace();

	}
	return null;
	
}//End logout

	/**Function that search in the database books by text and columns
	 * @param text - the text that client insert for search
	 * @param cb - the column that the client search in
	 * @return ArrayList of books entities ,result by the search
	 */
	public Object SearchBook(String text,ArrayList<Integer> cb){
		ArrayList<BookET> returnObj=new ArrayList<BookET>();
		int dup=0;
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM test.books b INNER JOIN test.pairing p ON p.book_id= b.id INNER JOIN test.genere g ON p.genere_id= g.id");
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
				// user found).
				display("No Results");
				return "1";
			}
			else{
				while(rs.next())
					if(rs.getInt(13)==0 && dup!=rs.getInt(1)){
					for(int i=0;i<cb.size();i++){
						if((rs.getString(cb.get(i)).toLowerCase()).contains(text.toLowerCase())){
							i=cb.size()-1;
							dup=rs.getInt(1);
							returnObj.add(
								new BookET(rs.getInt(1),
										rs.getString(2),rs.getString(3),
										rs.getString(4),rs.getString(5),
										rs.getString(6),rs.getString(7),
										rs.getString(8),rs.getString(9),
										rs.getString(10),rs.getInt(11),
										rs.getInt(12),rs.getInt(13),
										rs.getInt(14),rs.getInt(15),rs.getInt(16)
								));
							returnObj.get(returnObj.size()-1).setBGenre(BuildStructure(rs.getInt(1)));
						}
				}}
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	/**Function that search in the database reviews by text and columns
	 * @param text - the text that client insert for search
	 * @param cb - the column that the client search in
	 * @return ArrayList of reviews entities ,result by the search
	 */
	public Object SearchReview(String text,String cb){
		ArrayList<ReviewET> returnObj=new ArrayList<ReviewET>();
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM review");
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
				// user found).
				display("No Results");
				return "1";
			}
			else{
				while(rs.next()){
					if(rs.getInt(11)==1){
					if((rs.getString(Integer.parseInt(cb)).toLowerCase()).contains(text.toLowerCase())){
						returnObj.add(
								new ReviewET(rs.getInt(1),
										rs.getInt(2),rs.getString(3),
										rs.getString(4),rs.getInt(5),
										rs.getString(6),rs.getString(7),
										rs.getString(8),rs.getString(9),rs.getInt(10),
										rs.getInt(11)	,rs.getInt(12)
								));
				}}}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnObj;
	}	
	

	
	/**Function that insert to the data base payment application
	 * @param details - array list with data about the payment application
	 * @param user - user entity with the details about the user who send the application
	 * @return number that mean if the insert application success
	 */
	public int EnablePayment(ArrayList<Object> details,UserET user){
	
		try {
			Statement  rStmt = con.createStatement();
			
			rStmt.executeUpdate("INSERT INTO reader VALUES ("+user.getId()+","
															 +details.get(0)+","
															+"\'"+details.get(1)+"\'"+","
															+"\'"+details.get(2)+"\'"+","
															+"\'"+details.get(3)+"\'"+","
															+"\'"+details.get(4)+"\'"+","
															+"\'"+details.get(5)+"\'"+","
															+0+","+details.get(6)+")");
			
			display(" User sent enable payment");
			/*
			PreparedStatement pStmt = con
					.prepareStatement("UPDATE user SET permission = 2 WHERE userName = ?");
			pStmt.setString(1, user.getUserName());
			pStmt.executeUpdate();
			*/
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
			
		}
		/*
		ReaderET reader=new ReaderET(user,(int)details.get(0),(String)details.get(1),(String)details.get(2),
				(String)details.get(3),(String)details.get(4),(String)details.get(5),(int)0,(int)details.get(6));
		return reader;
		*/
		return 1;
	}
	
	/**Function that set in the data base that the user purchase the book
	 * @param user - the user who purchased the book
	 * @param book - the book that the user purchased
	 * @return number that mean if the purchase success and if not, the number means why its failed
	 */
	public int GetBook(UserET user,BookET book){
		try {
			PreparedStatement iStmt = con
					.prepareStatement("SELECT * FROM reader_book WHERE id = ? AND bookId=?");
			iStmt.setInt(1, user.getId());
			iStmt.setInt(2, book.getBID());
			ResultSet rs = iStmt.executeQuery();
			if (rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
				return 4;
			}
			
			iStmt = con
					.prepareStatement("SELECT * FROM reader WHERE id = ?");
			iStmt.setInt(1, user.getId());
			rs = iStmt.executeQuery();
			rs.next();
			if(rs.getInt(2)==1 && rs.getInt(9)==0) return 2;
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String dates=new String(dateFormat.format(date));
			Statement  rStmt = con.createStatement();
			rStmt.executeUpdate("INSERT INTO reader_book VALUES ("+user.getId()+","
															 +book.getBID()+","
															+"\'"+book.getBTitle()+"\',"+0
															+",\'"+dates+"\')");
			display(" Book added to User");
			
			PreparedStatement pStmt = con
					.prepareStatement("UPDATE books SET numOfPurchace=numOfPurchace+1 WHERE id = ?");
			pStmt.setInt(1, book.getBID());
			pStmt.executeUpdate();
			if(rs.getInt(2)==0 && rs.getInt(9)==0) return 3;
			pStmt = con
					.prepareStatement("UPDATE reader SET book_left=book_left-1 WHERE id = ?");
			pStmt.setInt(1, user.getId());
			pStmt.executeUpdate();
			
			
			/*
			PreparedStatement pStmt = con
					.prepareStatement("UPDATE user SET permission = 2 WHERE userName = ?");
			pStmt.setString(1, user.getUserName());
			pStmt.executeUpdate();
			*/
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
			
		}
		
		return 1;
	}
	
	/**Function that check on the data base which books user purchase and still not publish review about them
	 * @param id - the id of the user
	 * @return - Array list with books 
	 */
	public Object BookList(int id){
		ArrayList<Integer> booklist1=new ArrayList <Integer>();
		ArrayList<String> booklist2=new ArrayList <String>();
		Map<String,Object> booklist=new HashMap<String,Object>();

		try {
			
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM reader_book WHERE id = ? ");
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				if(rs.getInt(4)==0){
				booklist1.add(rs.getInt(2));
				booklist2.add(rs.getString(3));
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
			
		}
		
		booklist.put("int", booklist1);
		booklist.put("String", booklist2);
		return booklist;

	}
	
	/**Function that check the status of the payment application
	 * @param id - the id of the user
	 * @return the status of the application
	 */
	public int CheckApplication(int id){
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM reader WHERE id = ? ");
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { return 3;}
			else {
				if(rs.next()) return rs.getInt(8);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;			
		}
		return 1;
	}
	
	/**Function that insert into the database review details that user publish
	 * @param review - review 
	 * @return number that mean if the publish review success
	 */
	public int PublishReview(ReviewET review){
		ReviewET reviewET=review;
		try {
			int id=0;
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM review ");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				id=rs.getInt(1);
			}
			pStmt = con
					.prepareStatement("SELECT * FROM books WHERE id = ? ");
			pStmt.setInt(1, review.getBookId());
			rs = pStmt.executeQuery();
			if(rs.next()){
				reviewET.setAuthor(rs.getString(3));
				reviewET.setBookphoto(rs.getString(10));
			}
			Statement  rStmt = con.createStatement();
			rStmt.executeUpdate("INSERT INTO review VALUES ("+(id+1)+","
															 +reviewET.getBookId()+","
															+"\'"+reviewET.getBookName()+"\'"+","
															+"\'"+reviewET.getAuthor()+"\'"+","
															+reviewET.getUserId()+","
															+"\'"+reviewET.getUserName()+"\'"+","
															+"\'"+reviewET.getUserPhoto()+"\'"+","
															+"\'"+reviewET.getBookphoto()+"\'"+","
															+"\'"+reviewET.getReview()+"\'"+","
															+reviewET.getRate()+","
															+0+","+0+")");
			
			display(" User sent new review");
			PreparedStatement qStmt = con
					.prepareStatement("UPDATE reader_book SET review = 1 WHERE id = ? AND bookId=? ");
			qStmt.setInt(1, reviewET.getUserId());
			qStmt.setInt(2, reviewET.getBookId());
			qStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();	
			return 0;
		}
		return 1;
		
	}
	
	/**Function that hides any book from the readers without having to remove the book from the database.
	 * @param bookId - the id of the book to hide\UnHide
	 * @param choice - In order to differentiate between Hide &amp; UnHide the book from the users. (0 - UnHidden, 1 - Hide)
	 * @return int - reflects what happened in the func, Hidden(1), UnHidden(4), Already hidden(2), Already UnHidden (3), book isn't exists (0)
	 */
	public int HideBook(String bookId, int choice){
		
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM books WHERE id = ? ");
			pStmt.setString(1, bookId);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { return 0;}
			else {
				rs.next();
				if(choice == 1){
					if(rs.getInt(13)==1)return 2;
					else{
						PreparedStatement qStmt = con
								.prepareStatement("UPDATE books SET hidden = 1 WHERE id = ?");
						qStmt.setString(1, bookId);
						qStmt.executeUpdate();
						return 1;
					}
				}
				else if(choice==0){
					if(rs.getInt(13)==0)return 3;
					else{
						PreparedStatement qStmt = con
								.prepareStatement("UPDATE books SET hidden = 0 WHERE id = ?");
						qStmt.setString(1, bookId);
						qStmt.executeUpdate();
						return 4;
					}
				}
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;			
		}
		return 0;
	}

	/**Function that freeze user to connect to the system. (Because there are only five types of system privileges, if the privilege over 5 the user is freezed)
	 * @param userId - the id of the user to freeze\UnFreeze
	 * @param choice - In order to differentiate between freeze &amp; UnFreeze the user from the system. (0 - UnFreeze, 1 - freeze)
	 * @return int - reflects what happened in the func, freezed(1), UnFreezed(4), Already freezed(2), Already UnFreezed (3), user isn't exists (0)
	 */
	public int FreezeUser(String userId,int choice){
		
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM user WHERE id = ? ");
			pStmt.setString(1, userId);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { return 0;}
			else {
				rs.next();
				if(choice==1){
					if(rs.getInt(4)>5)return 2;
					else{
				
						PreparedStatement qStmt = con
							.prepareStatement("UPDATE user SET permission = permission+5 WHERE id = ?");
						qStmt.setString(1, userId);
						qStmt.executeUpdate();
						return 1;
					}
				}else if(choice==0){
					if(rs.getInt(4)<5)return 3;
						else{
							PreparedStatement qStmt = con
								.prepareStatement("UPDATE user SET permission = permission-5 WHERE id = ?");
							qStmt.setString(1, userId);
							qStmt.executeUpdate();
							return 4;
						}
				}

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;			
		}
		return 0;
	}
	
	/**Function that Change Permission for users in the system.
	 * @param userId - the id of the user to Change Permission.
	 * @param newPer - the new permission that gave to the user. 
	 * @return int - reflects what happened in the func,( 1 - the user permission changed Successfully, 0 - the user isn't exists in the DB.
	 */
	
	public int ChangePermission(String userId, int newPer){
		
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM user WHERE id = ? ");
			pStmt.setString(1, userId);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { return 0;}
			else{
				PreparedStatement qStmt = con
					.prepareStatement("UPDATE user SET permission = ? WHERE id = ?");
					qStmt.setInt(1, newPer);
					qStmt.setString(2, userId);
					qStmt.executeUpdate();
					return 1;
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;			
		}
	}
	/**Function that search in the database books by text and columns
	 * advanced search check if the text inside the requested columns together
	 * @param tf -the column that the client search in and the text
	 * @return ArrayList of books entities ,result by the search
	 */
	public Object SearchAdv(ArrayList<String> tf){
		ArrayList<BookET> returnObj=new ArrayList<BookET>();
		int dup=0;
		try{
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM test.books b INNER JOIN test.pairing p ON p.book_id= b.id INNER JOIN test.genere g ON p.genere_id= g.id");
			
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) return 1;
			else{
				while(rs.next()){
					if(rs.getInt(13)==0 &&rs.getInt(1)!=dup){
						if( (rs.getString(2).toLowerCase()).contains(tf.get(0))
							&&(rs.getString(3).toLowerCase()).contains(tf.get(1))
							&&(rs.getString(4).toLowerCase()).contains(tf.get(2))
							&&(rs.getString(5).toLowerCase()).contains(tf.get(3))
							&&(rs.getString(20).toLowerCase()).contains(tf.get(4))
							&&(rs.getString(7).toLowerCase()).contains(tf.get(5))){
							dup=rs.getInt(1);
							returnObj.add(
								new BookET(rs.getInt(1),
										rs.getString(2),rs.getString(3),
										rs.getString(4),rs.getString(5),
										rs.getString(6),rs.getString(7),
										rs.getString(8),rs.getString(9),
										rs.getString(10),rs.getInt(11),
										rs.getInt(12),rs.getInt(13),
										rs.getInt(14),rs.getInt(15),rs.getInt(16)
								));
							returnObj.get(returnObj.size()-1).setBGenre(BuildStructure(rs.getInt(1)));
						}
				}}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();	
			return 0;
		}
				
		return returnObj;
	}
	
	/** Function that check on database and return the genres of requested book
	 * @param bookId - the book id that we want his genres
	 * @return String with genres
	 */
	public String BuildStructure(int bookId){
		
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT g.title FROM test.pairing p INNER JOIN test.genere g ON p.genere_id=g.id Where p.book_id=?");
			pStmt.setInt(1, bookId);
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			String genere=new String(rs.getString(1));
			while(rs.next()){
				if(!genere.contains(rs.getString(1)))
						genere=(genere+", "+rs.getString(1));
			}
			return genere;
			} catch (SQLException e) {
				e.printStackTrace();	
				return null;
			}
	}

	/**Function that returns user bought books report in a desirable period
	 * @param userId - the id of the requested user books report.
	 * @param fromDate - From which date
	 * @param toDate - Until what date
	 * @return ArrayList BookET - return the requested books for the user in the desirable period to view of management. (return null if the user does not purchased books yet).
	 */
	
	public ArrayList<BookET> UserReport(String userId, String fromDate, String toDate){
		ArrayList<BookET> returnObj = new ArrayList<BookET>();
		try {
			PreparedStatement pStmt = con
				.prepareStatement("SELECT * FROM test.reader_book WHERE date BETWEEN ? AND ? AND  id = ? order by date");
			pStmt.setString(1, fromDate);
			pStmt.setString(2, toDate);
			pStmt.setString(3, userId);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
			// user found).
				display("The user does not purchased books yet");
				return null;
			}
			else{
				while(rs.next())
						returnObj.add(new BookET(rs.getInt(1),
									rs.getString(3),rs.getDate(5)));
			}
			
			rs.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	/**Function that returns books purchased\searched report in a desirable period.
	 * @param bId - the id of the requested book for report. 
	 * @param choice - report by purchased (0), report by searched (1).
	 * @param fromDate - From which date
	 * @param toDate - Until what date
	 * @return HashMap that contain ( 1 - DefaultCategoryDataset object that contain the data set for the bar chart displaying in the management panel.
	 * 2 - the choice by purchased (0) or by searched (1) to update the panel right.)
	 */
	
	
	public Object BookReport(int bId, int choice, String fromDate, String toDate){
		
		Map<String,Object> returnObj = new HashMap<String,Object>();
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		int count = 0;
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
		String myTemp;
		
		try {
			if(choice == 0){ 		//By purchased
				PreparedStatement pStmt = con
						.prepareStatement("SELECT * FROM test.reader_book WHERE date BETWEEN ? AND ? AND bookId = ? order by date ");
				pStmt.setString(1, fromDate);
				pStmt.setString(2, toDate);
				pStmt.setInt(3, bId);
				ResultSet rs = pStmt.executeQuery();
			
				if (!rs.isBeforeFirst())
					return 0;    	// The book has not yet been purchased
				else{
					rs.next();
					myTemp = tempDate.format(rs.getDate(5));
					count++;
					while(rs.next()){
						if(tempDate.format(rs.getDate(5)).equals(myTemp))
							count++;
						else{
							dataSet.setValue(count, "Amount of purchases", myTemp);
							count = 1; 
							myTemp = tempDate.format(rs.getDate(5));
						}
					}
					dataSet.setValue(count, "Amount of purchases", myTemp);
				}
			}
			else if(choice == 1){ // By searched		
				PreparedStatement pStmt = con
						.prepareStatement("SELECT * FROM test.search_book WHERE date BETWEEN ? AND ? AND book_id = ? order by date ");
				pStmt.setString(1, fromDate);
				pStmt.setString(2, toDate);
				pStmt.setInt(3, bId);
				ResultSet rs = pStmt.executeQuery();
				if (!rs.isBeforeFirst())
					return 1;    	 // The book has not yet been searched
				else{
					rs.next();
					myTemp = tempDate.format(rs.getDate(2));
					count++;
					while(rs.next()){
						if(tempDate.format(rs.getDate(2)).equals(myTemp))
							count++;
						else{
							dataSet.setValue(count, "Amount of searches", myTemp);
							count = 1; 
							myTemp = tempDate.format(rs.getDate(2));
						}
					}
					dataSet.setValue(count, "Amount of searches", myTemp);
				}
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			return 2; // book isn't exists	
		}
		
		returnObj.put("int", choice);
		returnObj.put("data", dataSet);
		return returnObj;
	}
	
	/**Auxiliary function that returns the genres related to a specific book to display to the manager in the panel for choice
	 * @param bId - the id of the requested book to get related genres list. 
	 * @return HashMap that contain ( 1 - genereIdArr ArrayList, ArrayList that contain the genre's id's respectively to the genre's name's in the array of the name.
	 * 2 - genereNameArray string array that contain the genre's names's respectively to the genre's id's in the ArrayList of the id's.
	 */
	
	public Map<String, Object> updateGeneresInComboBox(int bId){
		
		Map<String,Object> returnObj = new HashMap<String,Object>();
		String[] genereNameArray = null;
		int numOfRows;
		int numOfGenere;
		
		try{
			PreparedStatement pStmt = con
					.prepareStatement("SELECT genere_id FROM test.pairing WHERE book_id = ? ");
			pStmt.setInt(1, bId);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (The book does not exist in the system).
					display("The book does not exist in the system");
					return null;
			}
			else{
				ArrayList<Integer> genereIdArray = new ArrayList<Integer>();
				rs.last();
				numOfRows = rs.getRow();
				rs.beforeFirst();
				rs.next();
				for(int i = 0 ; i < numOfRows ; i++){
					if(genereIdArray.contains(rs.getInt(1)))
						rs.next();
					else{
						genereIdArray.add(rs.getInt(1));
						rs.next();
					}
				}
				genereNameArray = new String[numOfGenere = genereIdArray.size()];
				for(int i = 0 ; i < numOfGenere ; i++){
					pStmt = con
							.prepareStatement("SELECT title FROM test.genere where id = ?");
					pStmt.setInt(1, genereIdArray.get(i));
					rs = pStmt.executeQuery();
					rs.next();
					genereNameArray[i] = rs.getString(1);
				}
				returnObj.put("genereIdArr", genereIdArray);
				returnObj.put("genereNameArr", genereNameArray);
				return returnObj;
					
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**Function that returns book total rank in the library.
	 * @param bId - the id of the requested book for report. 
	 * @return BookET that contain the book details, for displaying in the manager panel.
	 */
	
	public BookET BookRank(int bId){
		
		BookET returnObj = null;
		int count = 1;
		
		try {/*choice = 0 - Total Rank, 1 - Genre Rank */
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM test.books order by numOfPurchace DESC ");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				if(rs.getInt(1) == bId){
					returnObj = new BookET(rs.getInt(1),
							rs.getString(2),rs.getString(3),
							rs.getString(4),rs.getString(5),
							rs.getString(6),rs.getString(7),
							rs.getString(8),rs.getString(9),
							rs.getString(10),rs.getInt(11),
							rs.getInt(12),rs.getInt(13),
							count,0,rs.getInt(16));
					return returnObj;
				}
				else
					count++;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return returnObj; // book isn't exists	
		}
		return returnObj;
	}
	
	/**override the function BookRank above that returns book genre rank in the library.
	 * @param bId - the id of the requested book for report. 
	 * @param gener - the requested genre to rank in.
	 * @param genreArrList - array of the genres that related to the book.
	 * @return BookET that contain the book details, for displaying in the manager panel.
	 */
	
	
	public BookET BookRank(int bId, String gener, Map<String, Object> genreArrList) {
		
		BookET returnObj = null;
		int count; /*1*/
		String tempString;
		ArrayList<Integer> genereIdArray = (ArrayList<Integer>)genreArrList.get("genereIdArr");
		String[] genereNameArray = (String[])genreArrList.get("genereNameArr");
		
		try {
			for(count = 0;count<genereNameArray.length;count++)
				if(genereNameArray[count].equals(gener))
					break;
			PreparedStatement pStmt = con
				.prepareStatement("SELECT * FROM (SELECT distinct book_id as bId FROM test.pairing where genere_id = ?) AS T1 JOIN (SELECT * FROM test.books) AS T2 ON T1.bId = T2.id order by numOfPurchace DESC");
			pStmt.setInt(1, ((ArrayList<Integer>) genreArrList.get("genereIdArr")).get(count));
			ResultSet rs = pStmt.executeQuery();
			count = 1;
			while(rs.next()){
				if(rs.getInt(1) == bId){
					returnObj = new BookET(rs.getInt(2),
							rs.getString(3),rs.getString(4),
							rs.getString(5),rs.getString(6),
							rs.getString(7),rs.getString(8),
							rs.getString(9),rs.getString(10),
							rs.getString(11),rs.getInt(12),
							rs.getInt(13),rs.getInt(14),
							0,count,rs.getInt(17));
					return returnObj;
				}
				else
					count++;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return returnObj; // book isn't exists	
		}
		return returnObj;
	}
	
	
	/**Function that close the connection with the database with server
	 * 
	 */
	public void closeSqlConnection(){

		

		boolean catched=false;
		// crashes if the connection is close when method starts
		// from http://stackoverflow.com/questions/2225221/closing-database-connections-in-java
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			System.out.println("SQL Connection closed");
			catched=true;
		}
		
		if(!catched){
			  window.lblSQL.setText("Offline");
			  window.lblSQL.setForeground(Color.RED);
		}else{
			window.lblSQL.setText("Online");
			  window.lblSQL.setForeground(Color.GREEN);
		}
	}
	
	/**Get user entity to put in database in user table.
	 * 
	 * First - retrieve from the database the max user id that the next id will be for the new user to add.
	 * Second - check if the userName already exist in database (show a massage if exist).
	 * Second - insert the user to the database to the user table(if not exist already).
	 * 
	 * @param obj - (get an object - in the function goes through casting) need to get a User entity to check and add to database to user table - UserET.
	 * @return -1 if SQLException , 0 if the userName exist already in database , 1 if succeed add the user to database in user table.
	 */
	public int AddUser(Object obj) {
		UserET user = (UserET) obj;
		try
		{
			Statement pStmt = con.createStatement();
			pStmt.execute("SELECT MAX(id) FROM user");
			ResultSet rs = pStmt.getResultSet();
			int id=0;
			if(rs.next())
				id=(int) rs.getObject(1);
			id+=1;
			PreparedStatement rStmt = con.prepareStatement("SELECT * FROM user WHERE userName = ?");
			rStmt.setString(1, user.getUserName());
			rs = rStmt.executeQuery();
			if (!rs.isBeforeFirst()) 
			{
				String SQL = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, id);
				pstmt.setString(2, user.getUserName());
				pstmt.setString(3, user.getPassWord());
				pstmt.setInt(4, 1);
				pstmt.setInt(5, 0);
				pstmt.setString(6, user.getFirstName());
				pstmt.setString(7, user.getLastName());
				pstmt.setString(8, user.getEmail());
				pstmt.setString(9, "profile_picture");
				pstmt.setInt(10, 0);
				pstmt.executeUpdate();
				pstmt.close();
				return 1;
			}
			display("The UserName already in the DB.");
			return 0;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	/**Function that Insert to databse the date of search of specific book and update the search count of the book
	 * @param bookId
	 * @return number that mean if the insert success
	 */
	public int ViewBook(int bookId){
		try{
			PreparedStatement qStmt = con
					.prepareStatement("UPDATE books SET numOfSearch = numOfSearch+1 WHERE id = ?");
				qStmt.setInt(1, bookId);
				qStmt.executeUpdate();
				
				qStmt = con
						.prepareStatement("UPDATE books SET numOfSearch = numOfSearch+1 WHERE id = ?");
					qStmt.setInt(1, bookId);
					qStmt.executeUpdate();	
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					String dates=new String(dateFormat.format(date));
					Statement  rStmt = con.createStatement();
					rStmt.executeUpdate("INSERT INTO search_book VALUES ("+bookId+","
																	+"\'"+dates+"\')");	
				
		}catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	/**Function that check which payment applications didnt confirmed yet 
	 * @return the list of Reader entity with details of payment
	 */
	public Object GetPaymentList(){
		ArrayList<ReaderET> reader=new ArrayList<ReaderET>();
		try{
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM reader Where confirm=0");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				reader.add(new ReaderET(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4), rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getInt(8), rs.getInt(9)));
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
		return reader;
	}
	
	/**Function that set the status of payment application
	 * @param id - the user id for recognize the application
	 * @param confirm - the status we want to set to the application
	 * @return number that mean if the update success
	 */
	public int pConfirm(int id,int confirm){
		try{
			PreparedStatement qStmt = con
					.prepareStatement("UPDATE reader SET confirm = ? WHERE id = ?");
				qStmt.setInt(1, confirm);
				qStmt.setInt(2, id);
				qStmt.executeUpdate();
				if(confirm==1){
				qStmt = con
						.prepareStatement("UPDATE user SET confirm = 1 WHERE id = ?");
					qStmt.setInt(1, id);
					qStmt.executeUpdate();
				}
		}catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
/**Function that display messge on server log screen
 * @param message
 */
private void display(String message) {
	window.display(message);
	
}

/**Get book id (Bid) to bring the book with this id.
 * First - retrieve book from books table (with this Bid) from the database(from books table).
 * second - put book details in a new book entity(BookET).
 * 
 * @param Bid - book id to bring this book from database - Integer.
 * @return -1 if SQLException , 0 if not exist book with Bid=book id in database , BookET of the book from the database from books table if succeed to bring it.
 */
public Object BringBook(int Bid) {
	BookET bookET;
	try {
		PreparedStatement pStmt = con
				.prepareStatement("SELECT * FROM books WHERE id=?");
		pStmt.setInt(1, Bid);
		ResultSet rs = pStmt.executeQuery();
		//pStmt.close();
		if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No user
			// found).
			display("no book in the ResultSet of bring book.");
			return 0;
		}
		else if(rs.next()){
			bookET= new BookET(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15),rs.getInt(16));
			
			return bookET;	
		}
		return 0;
	}
	catch(SQLException e){
		e.printStackTrace();
		return -1;
	}
	
}

/**Get book entity for update book details in database.
 * First - update the fields of the book entity in the database in books table.
 * 
 * @param bookET - book entity with the updated fields to insert to database in books table - BookET.
 * @return false if SQLException , true if succeed to update the fields in the books table.
 */
public boolean UpdateBook(BookET bookET)
{
	//Update book function
	try
	{
		PreparedStatement pStmt = con
				.prepareStatement("UPDATE books "
								+ "SET title=?,author=?,language=?,summary=?,content=?,keywords=? "
								+ "WHERE id = ? ");
		pStmt.setString(1, bookET.getBTitle());
		pStmt.setString(2, bookET.getBAuthor());
		pStmt.setString(3, bookET.getBLanguage());
		pStmt.setString(4, bookET.getBSummary());
		pStmt.setString(5, bookET.getBContent());
		pStmt.setString(6, bookET.getbKeywords());
		pStmt.setInt(7, bookET.getBID());
		pStmt.executeUpdate();
		//pStmt.close();
		return true;
	
	}
	catch(SQLException e){
		e.printStackTrace();
		return false;
	}
	
}
/**
 * First - retrieve all genres from genere table in the database and enter them all to ArrayList of GenreET.
 * Second - for every Genre entity retrieve all subject that pair with.
 * 
 * @return 0 if SQLException , ArrayList of all Genres entities(and in every GenreET a ArrayList of SubjectET of every subject pair with) from genere table and subject table.
 */
public Object BringGandS()
{
	ArrayList<GenreET> Genre = new ArrayList<GenreET>();
	
	try
	{
		PreparedStatement pStmt = con.prepareStatement("SELECT * FROM genere ");
		
		PreparedStatement rStmt = con.prepareStatement("SELECT * FROM subject WHERE genere_id = ? ");
		
		ResultSet rs = pStmt.executeQuery();
		
		while(rs.next())
		{
			
			Genre.add(new GenreET(rs.getInt(1), rs.getString(2)));
			rStmt.setInt(1, rs.getInt(1));
			ResultSet rs2 = rStmt.executeQuery();
			
			while(rs2.next())
			{
				Genre.get(Genre.size()-1).SetSubject(rs2.getInt(1), rs2.getString(3), rs2.getInt(2));
			}
		}
		return Genre;
	}catch(SQLException e){
	e.printStackTrace();
	return 0;
}
	
}

/**Get entity of book to add and ArrayList of FileEvent of the path to the book files(pdf,doc,fb2).
 * 
 * First - retrieve from the database the max book id that the next id will be for the new book to add.
 * Second - insert the book to the data base to the book table.
 *  
 * @param fileEvents - ArrayList of FileEvent of the path to the book files(pdf,doc,fb2).
 * @param newBook - the book entity to add to the database.
 * @return -1 if SQLException or if the pair function not succeed , 1 if the book add to the books table and pair function succeed to pair the book. 
 */
public int AddBook(BookET newBook,ArrayList<FileEvent> fileEvents)
{
	try
	{
		Statement rStmt = con.createStatement();
		rStmt.execute("SELECT MAX(id) FROM books");
		ResultSet rs = rStmt.getResultSet();
		int id=0;
		if(rs.next())
			id=(int) rs.getObject(1);
			id+=1;
			
			newBook.setBID(id);
			String SQL = "INSERT INTO books VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, id);
			pstmt.setString(2, newBook.getBTitle());
			pstmt.setString(3, newBook.getBAuthor());
			pstmt.setString(4, newBook.getBLanguage());
			pstmt.setString(5, newBook.getBSummary());
			pstmt.setString(6, newBook.getBContent());
			pstmt.setString(7, newBook.getbKeywords());
			pstmt.setString(8, newBook.getBGenre());
			pstmt.setString(9, newBook.getBSubject());
			pstmt.setString(10, newBook.getBPhoto());
			pstmt.setInt(11, 0);
			pstmt.setInt(12, 0);
			pstmt.setInt(13, 0);
			pstmt.setInt(14, 0);
			pstmt.setInt(15, 0);
			pstmt.setInt(16,newBook.getPrice());
			pstmt.setBytes(17, fileEvents.get(0).getFileData());
			pstmt.setBytes(18, fileEvents.get(1).getFileData());
			pstmt.setBytes(19, fileEvents.get(2).getFileData());
			pstmt.executeUpdate();
			pstmt.close();
			if(PairingBook(newBook)==1)
			{
				return id;
			}
			else return -1;
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		return -1;
	}
	
}
/**Get entity of book to add a pair to it.
 * First - check if the book exist in the database.(if not show a massage)
 * Second - retrieve from the database the genre id and subject id to pair the book to them.
 * Third - insert a row of pair of the book with the genre and the subject in the pairing table.
 * 
 * @param BookToPair - book entity(BookET) of the book to pair(PAIR the genre and the subject in the BookET)
 * @return 0 if the book not exist in database , -1 if SQLException , 1 if success insert a pair to database.
 */
public int PairingBook(BookET BookToPair)
{
	int Gid=0,Sid=0;
	try
	{
		PreparedStatement pstmt = con.prepareStatement("SELECT id FROM books WHERE id=?");
		pstmt.setInt(1, BookToPair.getBID());
		ResultSet rs = pstmt.executeQuery();
		if (!rs.isBeforeFirst()) 
		{ // Checks if ResultSet is empty (No book
			// found).
			display("The book not exist in DB!");
			return 0;
		}
		else 
		{
		pstmt = con.prepareStatement("SELECT * FROM genere g INNER JOIN subject s ON g.id=s.genere_id");
		 rs = pstmt.executeQuery();
		while(rs.next())
		{
			if(rs.getString(2).equals(BookToPair.getBGenre()) && rs.getString(5).equals(BookToPair.getBSubject()))
			{
				Sid=rs.getInt(3);
				Gid=rs.getInt(4);
			}
		}
		String str="INSERT INTO pairing VALUES (?, ?, ?)";
		pstmt=con.prepareStatement(str);
		pstmt.setInt(1, BookToPair.getBID());
		pstmt.setInt(2, Gid);
		pstmt.setInt(3, Sid);
		pstmt.executeUpdate();
		pstmt.close();
		return 1;
		}
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		return -1;
	}
}
/**Function that check on database which review still not confirmed
 * @return Array list of review entities which are not confirmed yet
 */
public Object GetReviewList(){
	ArrayList<ReviewET> review=new ArrayList<ReviewET>();
	try{
		PreparedStatement pStmt = con
				.prepareStatement("SELECT * FROM review Where confirm=0");
		ResultSet rs = pStmt.executeQuery();
		while(rs.next()){
			review.add(
					new ReviewET(rs.getInt(1),
							rs.getInt(2),rs.getString(3),
							rs.getString(4),rs.getInt(5),
							rs.getString(6),rs.getString(7),
							rs.getString(8),rs.getString(9),rs.getInt(10),
							rs.getInt(11)	,rs.getInt(12)
					));
		}
	}catch(SQLException e)
	{
		e.printStackTrace();
		return 0;
	}
	return review;
}
/**Function that set the status of review to confirm or reject
 * @param id - the id of the review
 * @param confirm - the status of the review confirm or reject
 * @return number that mean if the update success
 */
public int rConfirm(int id,int confirm){
	try{
		PreparedStatement qStmt = con
				.prepareStatement("UPDATE review SET confirm = ? WHERE id = ?");
			qStmt.setInt(1, confirm);
			qStmt.setInt(2, id);
			qStmt.executeUpdate();
	}catch(SQLException e)
	{
		e.printStackTrace();
		return 0;
	}
	return 1;
}
/**
 * Get review id for edit and the string review.
 * First update the review to the review table
 * 
 * 
 * @param id - the review id to edit - Integer.
 * @param review - the review itself to insert instead of that in the database in review table - String.
 * @return 0 if SQLException , 1 if update the review in the database.
 */
public int EditReview(int id,String review){
	try{
		PreparedStatement qStmt = con
				.prepareStatement("UPDATE review SET review = ? WHERE id = ?");
			qStmt.setString(1, review);
			qStmt.setInt(2, id);
			qStmt.executeUpdate();
			
			qStmt = con
					.prepareStatement("UPDATE review SET confirm = 1 WHERE id = ?");
				qStmt.setInt(1, id);
				qStmt.executeUpdate();
	}catch(SQLException e)
	{
		e.printStackTrace();
		return 0;
	}
	return 1;
}
/**
 * First retrieve all rows from books table.
 * Second check if has no books in database.
 * Third if there is more than one book, create a book entity for all books in database and add it to the ArrayList to return.
 * 
 * @return 0 if not exist books in database and if SQLException , ArrayList of all books entities from books tables.
 */
public Object BringArrayBooks()
{
	ArrayList<BookET> booksET = new ArrayList<BookET>();
	try
	{
		PreparedStatement pStmt = con
				.prepareStatement("SELECT * FROM books");
		ResultSet rs = pStmt.executeQuery();
		//pStmt.close();
		if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty 
			display("no books in DB.");
			return 0;
		}
		else {
			while(rs.next())
			{
				booksET.add(new BookET(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15),rs.getInt(16)));	
		    }
			return booksET;
		}
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		return 0;
	}
}
/**Get book id to remove. 
 * First delete the book from books table. 
 * second delete the book from pairing table. 
 * 
 * @param Bid - the book id to delete - integer type.
 * @return 0 if SQLException , 1 if the book removed from the database from the book table and the pairing table. 
 */
public int DeleteBook(int Bid)
{
	try
	{
			PreparedStatement qStmt = con.prepareStatement("SELECT id FROM books WHERE id=?");
			qStmt.setInt(1, Bid);
			ResultSet rs = qStmt.executeQuery();
			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No book
				// found).
				display("The book not exist in DB!");
				return 0;
			}
			else{
			 qStmt = con.prepareStatement("DELETE FROM books WHERE id = ?");
			qStmt.setInt(1, Bid);
			qStmt.executeUpdate();
			qStmt = con.prepareStatement("DELETE FROM pairing WHERE book_id = ?");
			qStmt.setInt(1, Bid);
			qStmt.executeUpdate();
			return 1;
			}
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		return -1;
	}
}
/**
 * Get entity of genre to add.
 * First - check if the genre already exist in the database.
 * Second - retrieve from the database the max genre id that the next id will be for the new genre to add.
 * Third - insert the genre to the data base to the genre table.
 * 
 * @param genre - the genre entity to add to the database.
 * @return -1 if SQLException , 0 if this genre exist in the database, 1 if the genre add to the genere table. 
 */
public int AddGenre(GenreET genre)
{
	try
	{
		PreparedStatement pStmt = con
				.prepareStatement("SELECT * FROM genere WHERE title=?");
		pStmt.setString(1, genre.getGenreTitle());
		ResultSet rs = pStmt.executeQuery();

		if (rs.isBeforeFirst()) { // Checks if ResultSet is empty 
			
			display("Genre is exist in DB! ");
			return 0;

		}
		else {
		pStmt = con.prepareStatement("SELECT MAX(id) FROM genere");
		rs = pStmt.executeQuery();
		int id=0;
		if(rs.next())
			id=(int) rs.getObject(1);
		id+=1;
		pStmt = con.prepareStatement("INSERT INTO genere VALUES (?, ?)");
		pStmt.setInt(1, id);
		pStmt.setString(2, genre.getGenreTitle());
		pStmt.executeUpdate();
		pStmt.close();
		return 1;
		}
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		return -1;
	}
}
/**
 * Get entity of subject to add.
 * First - check if the subject already exist in the database.
 * Second - retrieve from the database the max subject id that the next id will be for the new subject to add.
 * Third - insert the subject to the data base to the subject table.
 * 
 * @param subject - the subject entity to add to the database.
 * @return -1 if SQLException , 0 if this subject exist in the database, 1 if the subject add to the subject table. 
 */
public int AddSubject(SubjectET subject)
{
	try
	{
		PreparedStatement pStmt = con
				.prepareStatement("SELECT * FROM subject WHERE genere_id=? AND title=?");
		pStmt.setInt(1, subject.getGenreId());
		pStmt.setString(2, subject.getSubjectTitle());
		ResultSet rs = pStmt.executeQuery();

		if (rs.isBeforeFirst()) { // Checks if ResultSet is empty 
									// if there is this subject in DB
			display("Subject is exist in DB! ");
			return 0;
		}
		else {
		pStmt = con.prepareStatement("SELECT MAX(id) FROM subject");
		rs = pStmt.executeQuery();
		int id=0;
		if(rs.next())
			id=(int) rs.getObject(1);
		id+=1;
		pStmt = con.prepareStatement("INSERT INTO subject VALUES (?, ?, ?)");
		pStmt.setInt(1, id);
		pStmt.setInt(2, subject.getGenreId());
		pStmt.setString(3, subject.getSubjectTitle());
		pStmt.executeUpdate();
		pStmt.close();
		return 1;
		}
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		return -1;
	}
}
/**
 * Get a genre id.
 * First - check if there is a subject that attached to the genre to remove.
 * second - if Does not exist subject that attached to this genre delete the genre from the genere table.
 * 
 * 
 * @param Gid - the id of the genre to remove.
 * @return -1 if SQLException , 0 if there is a subject that attached to the genre , 1 if the genre has removed from database.
 */
public int RemoveGenre(int Gid)
{
	
	try
	{
			PreparedStatement pStmt = con.prepareStatement("SELECT * FROM subject WHERE genere_id=?");
			pStmt.setInt(1, Gid);
			ResultSet rs = pStmt.executeQuery();
			if (rs.isBeforeFirst()) { // Checks if ResultSet is empty 
										// if there is this subject in DB
					display("This Genre has subject/s attached in DB! ");
					return 0;
			}
			else
			{
				pStmt = con.prepareStatement("DELETE FROM genere WHERE id = ?");
				pStmt.setInt(1, Gid);
				pStmt.executeUpdate();
				return 1;
			}
		
		
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		return -1;
	}
}
/**
 * Get a genre id and subject title. 
 * First - retrieve from the database the id of the subject to remove.
 * Second - retrieve from database all books that pair to the subject to remove.
 * Third - checks for every book that pair to the subject if that subject is the only subject that attached to this book.
 * Fourth - if Does not exist book like this delete the subject from the subject table and from the pairing table. 
 * 
 * @param Titles - HashMap of Integer and Object the first object is of type integer the second is of type string 
 * @return -1 if SQLException , 0 if there is a book that attached to the subject , 1 if the subject removed from the database
 */
public int RemoveSubject(HashMap<Integer,Object> Titles)
{
	int GTitle=(int) Titles.get(1),Sid=0;
	ArrayList<Integer> book_id= new ArrayList<Integer>();
	String STitle=(String) Titles.get(2);
	try
	{
		
		PreparedStatement pStmt = con
				.prepareStatement("SELECT id FROM subject WHERE genere_id=? AND title=?");
		pStmt.setInt(1, GTitle);
		pStmt.setString(2, STitle);
		ResultSet rs = pStmt.executeQuery();
		if (rs.next()) { 
			Sid = rs.getInt(1);
		}
		pStmt=con.prepareStatement("SELECT * FROM pairing WHERE subject_id=?");
		pStmt.setInt(1, Sid);
		rs = pStmt.executeQuery();
		while(rs.next()){
			book_id.add(rs.getInt(1));
		}
		pStmt=con.prepareStatement("SELECT * FROM pairing WHERE book_id=?");
		for(Integer Bid: book_id) {
			pStmt.setInt(1, Bid);
			rs = pStmt.executeQuery();
			rs.last();
			if(rs.getRow()<2)
			{
				display("There is a book attached only to this subject! ");
				return 0;
			}
				
		}
		pStmt=con.prepareStatement("DELETE FROM subject WHERE id = ?");
		pStmt.setInt(1, Sid);
		pStmt.executeUpdate();
		pStmt=con.prepareStatement("DELETE FROM pairing WHERE subject_id=?");
		pStmt.setInt(1, Sid);
		pStmt.executeUpdate();
		return 1;
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		return -1;
	}
}

/**Function that get from the database files of books by specific format
 * and return the file in byte array
 * @param fileEvent - instance of FileEvent with details and data of the book file 
 * @return fileEvent - the same file with the byte array of the book file
 */
public FileEvent Download(FileEvent fileEvent){
	try{
		int col=0;
		if(fileEvent.getFilename().contains("pdf")) col=17;
		if(fileEvent.getFilename().contains("doc")) col=18;
		if(fileEvent.getFilename().contains("fb2")) col=19;
		String sql = "SELECT * FROM books WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, fileEvent.getBookid());
		ResultSet rs = stmt.executeQuery();
		rs.next();
		
		Blob blob=(Blob) rs.getBlob(col);
		int len=(int)blob.length();
		byte[] bl=blob.getBytes(1, len);
		fileEvent.setFileData(bl);
		fileEvent.setStatus("Success");
	}catch(SQLException e)
	{
		e.printStackTrace();
		return null;
	}
	return fileEvent;
}
/**Function that Set new subscription to user who confirmed his payment method
 * the function check if its possible
 * @param details - the details of the subscription request
 * @return - number that mean if the request success
 */
public int RenewSub(ArrayList<Object> details){
	
	try {
		String sql = "SELECT book_left FROM reader WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, (int)details.get(0));
		ResultSet rs = stmt.executeQuery();
		rs.next();
		if(rs.getInt(1)!=0 && (int)details.get(1)==0) return 0;
		
		PreparedStatement qStmt = con
				.prepareStatement("UPDATE reader SET book_left = book_left+? WHERE id = ?");
			qStmt.setInt(1, (int)details.get(2));
			qStmt.setInt(2, (int)details.get(0));
			qStmt.executeUpdate();
			
			qStmt = con
					.prepareStatement("UPDATE reader SET subscription = ? WHERE id = ?");
				qStmt.setInt(1, (int)details.get(1));
				qStmt.setInt(2, (int)details.get(0));
				qStmt.executeUpdate();
				
	} catch (SQLException e) {
		e.printStackTrace();
		return 0;
		
	}

	return 1;
}
/**Function that check if the library stuff have an notifications about reviews they have to confirm
 * @return the number of the review are not confirmed yet
 */
public int CheckNotif(){
	int count=0;
	try{
		
		String sql = "SELECT COUNT(*) FROM review WHERE confirm=0";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		count=rs.getInt(1);
	} catch (SQLException e) {
		e.printStackTrace();
		return 0;
	}
	return count;
}
/**
 * Set the book file in blob fields on the data base when the server restart
 */
public void SetBooksFiles(){
	byte[] fileBytes;
	byte[] fileBytes1;
	byte[] fileBytes2;
	String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
/*
	File file=new File(this.getClass().getClassLoader().getResource("").getPath()+"/1.pdf");
	File file1=new File(this.getClass().getClassLoader().getResource("").getPath()+"/1.docx");
	File file2=new File(this.getClass().getClassLoader().getResource("").getPath()+"/1.fb2");*/
	File file=new File(path+"/1.pdf");
	File file1=new File(path+"/1.docx");
	File file2=new File(path+"/1.fb2");
	if (file.isFile() && file1.isFile() && file2.isFile()) {
		try {
		DataInputStream diStream = new DataInputStream(new FileInputStream(file));
		long len = (int) file.length();
		fileBytes = new byte[(int) len];
		int read = 0;int numRead = 0;
		while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
		read = read + numRead;
		}
		diStream = new DataInputStream(new FileInputStream(file1));
		len = (int) file1.length();
		fileBytes1 = new byte[(int) len];
		read = 0;numRead = 0;
		while (read < fileBytes1.length && (numRead = diStream.read(fileBytes1, read, fileBytes1.length - read)) >= 0) {
		read = read + numRead;
		}
		diStream = new DataInputStream(new FileInputStream(file2));
		len = (int) file2.length();
		fileBytes2 = new byte[(int) len];
		read = 0;numRead = 0;
		while (read < fileBytes2.length && (numRead = diStream.read(fileBytes2, read, fileBytes2.length - read)) >= 0) {
		read = read + numRead;
		}
		PreparedStatement qStmt = con
				.prepareStatement("UPDATE books SET pdf = ? ,doc=? ,fb2=?");
		qStmt.setBytes(1,fileBytes);
		qStmt.setBytes(2,fileBytes1);
		qStmt.setBytes(3,fileBytes2);
			qStmt.executeUpdate();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}else {
		System.out.println("path specified is not pointing to a file");
		}
}
public int GetMaxBid()
{
	try
	{
		Statement rStmt = con.createStatement();
		rStmt.execute("SELECT MAX(id) FROM books");
		ResultSet rs = rStmt.getResultSet();
		int id=0;
		if(rs.next())
			id=(int) rs.getObject(1);
		return id;
	}
		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
}

}
