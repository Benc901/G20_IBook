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
import iBookServer.IBookServer;
import iBookServer.mysqlConnection;
import iBookServer.serverUI;
import Controllers.LibrarianCT;
import Controllers.UserCT;
import Entities.BookET;
import Entities.FileEvent;
import Entities.GenreET;
import Entities.UserET;
import Mains.Main;
import fit.ActionFixture;

public class AddBookTest extends ActionFixture {
	
	final public static int DEFAULT_PORT = 5555;
	private LibririanUI libFrame;
	private LoginUI loginFrame;
	private UserET userET;
	private AddBookUI AddBook;
	private BookET bookET;
	private mysqlConnection mysql;
	private serverUI window;
	private ArrayList<FileEvent> fileEvents;
	private static LibrarianCT libCT ;
	private static UserCT userCoT;
	
	public AddBookTest()
	{
		String host="localhost";
		
			//iserver = new IBookServer(5555);
			//window = new serverUI(iserver);
		//mysql = new mysqlConnection(window, "root", "root");
		loginFrame = new LoginUI(host, DEFAULT_PORT);
		userCoT=UserCT.userCT;
		userCoT.GetLoginF().setUserName("ori");
		userCoT.GetLoginF().setPassword("1234");
		userCoT.GetLoginF().btnLogin.doClick();
		//userCoT.readerFrame.wbtnLibririan.doClick();
		userCoT.libririanFrame=new LibririanUI();
		
		fileEvents=new ArrayList<FileEvent>();
		bookET = new BookET();
		
		libCT=LibrarianCT.librarianCT;
	}
	public void setTitle(String title)
	{
		bookET.setBTitle(title);
	}
	public void setAuthor(String author)
	{
		bookET.setBAuthor(author);
	}
	public void setLanguage(String lan)
	{
		bookET.setBLanguage(lan);
	}
	public void setContent(String content)
	{
		bookET.setBContent(content);
	}
	public void setKeywords(String keyW)
	{
		bookET.setbKeywords(keyW);
	}
	public void setSummary(String summary)
	{
		bookET.setBSummary(summary);
	}
	public void setPrice(int price)
	{
		bookET.setPrice(price);
	}
	public void setGenre(String genre)
	{
		bookET.setBGenre(genre);
	}
	public void setSubject(String subject)
	{
		bookET.setBSubject(subject);
	}
	public void SetFilesLocationsPDF(String LocationPDF)
	{
		FileEvent fileEvent=new FileEvent();
		File file=new File(LocationPDF);
		if (file.isFile()) {
			try {
			DataInputStream diStream = new DataInputStream(new FileInputStream(file));
			long len = (int) file.length();
			byte[] fileBytes = new byte[(int) len];
			int read = 0;
			int numRead = 0;
			while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
			read = read + numRead;
			}
			fileEvent.setFileSize(len);
			fileEvent.setFileData(fileBytes);
			fileEvent.setStatus("Success");
			} catch (Exception e) {
			e.printStackTrace();
			fileEvent.setStatus("Error");
			}
		}else {
			System.out.println("path specified is not pointing to a file");
			fileEvent.setStatus("Error");
			}
		
		fileEvents.add(fileEvent);
	}
	public void SetFilesLocationsDOC(String LocationDOC)
	{
		FileEvent fileEvent=new FileEvent();
		File file=new File(LocationDOC);
		if (file.isFile()) {
			try {
			DataInputStream diStream = new DataInputStream(new FileInputStream(file));
			long len = (int) file.length();
			byte[] fileBytes = new byte[(int) len];
			int read = 0;
			int numRead = 0;
			while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
			read = read + numRead;
			}
			fileEvent.setFileSize(len);
			fileEvent.setFileData(fileBytes);
			fileEvent.setStatus("Success");
			} catch (Exception e) {
			e.printStackTrace();
			fileEvent.setStatus("Error");
			}
		}else {
			System.out.println("path specified is not pointing to a file");
			fileEvent.setStatus("Error");
			}
		
		fileEvents.add(fileEvent);
	}
	public void SetFilesLocationsFB2(String LocationFB2)
	{
		FileEvent fileEvent=new FileEvent();
		File file=new File(LocationFB2);
		if (file.isFile()) {
			try {
			DataInputStream diStream = new DataInputStream(new FileInputStream(file));
			long len = (int) file.length();
			byte[] fileBytes = new byte[(int) len];
			int read = 0;
			int numRead = 0;
			while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
			read = read + numRead;
			}
			fileEvent.setFileSize(len);
			fileEvent.setFileData(fileBytes);
			fileEvent.setStatus("Success");
			} catch (Exception e) {
			e.printStackTrace();
			fileEvent.setStatus("Error");
			}
		}else {
			System.out.println("path specified is not pointing to a file");
			fileEvent.setStatus("Error");
			}
		
		fileEvents.add(fileEvent);
	}
	public boolean checkAddBookTest()
	{	
		bookET.setBID(0);
		bookET.setBNumOfSearch(0);
		bookET.setBNumOfPurchace(0);
		bookET.setBTotalRank(0);
		bookET.setBGenreRank(0);
		bookET.setBHidden(0);
		bookET.setBPhoto("BookProfile");
		libCT.fileEvents=fileEvents;
		
		libCT.AddBook(bookET);
		
		if(libCT.id==-1)
			return false;
		else 
			return true;
	}
}
