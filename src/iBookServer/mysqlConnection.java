package iBookServer;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

public class mysqlConnection {
	Connection con;
	private serverUI window;
	//final private String url = "jdbc:mysql://localhost/database";
	//final private String username = "root";
	//final private String password = "1234";
	final private String url = "jdbc:mysql://localhost:3306/test";
	final private String username;
	final private String password;
	
	
	/**
	 * Constructor
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
		if(!catched){
			
			  window.lblSQL.setText("Online");
			  window.lblSQL.setForeground(Color.GREEN);
		}else{
			window.lblSQL.setText("Offline");
			  window.lblSQL.setForeground(Color.RED);
		}
	}
	
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
	
	
	public BookET BookRank(int bId, int choice){
		
		BookET returnObj = null;
		int count = 1;
		String myTemp;
		
		try {/*choice = 0 - Total Rank, 1 - Genre Rank */
			if(choice == 0){
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
								count,0);
						return returnObj;
					}
					else
						count++;
				}
			}else if(choice == 1){
				PreparedStatement pStmt = con
						.prepareStatement("SELECT books.genre FROM test.books WHERE id = ?;");
				pStmt.setInt(1, bId);
				ResultSet rs = pStmt.executeQuery();
				rs.next();
				myTemp = rs.getString(1);
				pStmt = con.prepareStatement("SELECT * FROM test.books WHERE genre = ? order by numOfPurchace DESC;");
				pStmt.setString(1, myTemp);
				rs = pStmt.executeQuery();
				while(rs.next()){
					if(rs.getInt(1) == bId){
						returnObj = new BookET(rs.getInt(1),
								rs.getString(2),rs.getString(3),
								rs.getString(4),rs.getString(5),
								rs.getString(6),rs.getString(7),
								rs.getString(8),rs.getString(9),
								rs.getString(10),rs.getInt(11),
								rs.getInt(12),rs.getInt(13),
								0,count);
						return returnObj;
					}
					else
						count++;
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return returnObj; // book isn't exists	
		}
		return returnObj;
	}
	
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
	
private void display(String message) {
	window.display(message);
	
}

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
		display(" "+ Genre.size());
		return Genre;
	}catch(SQLException e){
	e.printStackTrace();
	return 0;
}
	
}
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
			if(PairingBook(newBook))
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
public boolean PairingBook(BookET BookToPair)
{
	int Gid=0,Sid=0;
	try
	{
		PreparedStatement pstmt = con.prepareStatement("SELECT * FROM genere g INNER JOIN subject s ON g.id=s.genere_id");
		ResultSet rs = pstmt.executeQuery();
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
		return true;
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		return false;
	}
}
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
 * @return
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
		if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No user
			// found).
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
			PreparedStatement qStmt = con.prepareStatement("DELETE FROM books WHERE id = ?");
			qStmt.setInt(1, Bid);
			qStmt.executeUpdate();
			qStmt = con.prepareStatement("DELETE FROM pairing WHERE book_id = ?");
			qStmt.setInt(1, Bid);
			qStmt.executeUpdate();
			return 1;
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		return 0;
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
public int sendfile(FileEvent fileEvent){
	try{
		
		String sql = "INSERT INTO files (id, file) VALUES (?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
	    stmt.setInt(1, 1);
	    stmt.setBytes(2, fileEvent.getFileData());
	    stmt.execute();
	}catch(SQLException e)
	{
		e.printStackTrace();
		return 0;
	}
	return 1;
}
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

}
