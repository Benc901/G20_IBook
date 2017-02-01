package Fixtures;

import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import Entities.UserET;
import Mains.Main;
import fit.ActionFixture;

public class AddBookTest extends ActionFixture {
	
	private IBookServer iserver;
	private LibririanUI libFrame;
	private LoginUI loginFrame;
	private UserET userET;
	private AddBookUI AddBook;
	private BookET bookET;
	private mysqlConnection mysql;
	private serverUI window;
	private ArrayList<FileEvent> fileEvents;
	private LibrarianCT libCT ;
	private UserCT userCT;
	
	public AddBookTest()
	{
		//Main main= new Main();
		
		iserver = new IBookServer(5555);
		window = new serverUI(iserver);
		mysql = new mysqlConnection(window, "root", "root");
		fileEvents=new ArrayList<FileEvent>();
		bookET = new BookET();
		bookET.setBPhoto("BookProfile");
		
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
		if(mysql.AddBook(bookET, fileEvents)>0)
			return true;
		else return false;
	}
}
