package JUintTests;

import iBookServer.IBookServer;
import iBookServer.mysqlConnection;
import iBookServer.serverUI;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;

import org.junit.Test;

import Entities.BookET;

public class SearchBookJUnit extends TestCase{

	private serverUI window = new serverUI(new IBookServer(5555));
	private mysqlConnection mysql = new mysqlConnection(window, "root", "1234");
	private ArrayList<BookET> expected;
	private ArrayList<BookET> result;
	private ArrayList<Integer> cb; //= new ArrayList<Integer>();
	String zero= "0", one= "1",nagtive="-1";
	
	
	@Test
	public void testSearchBookWithEmptyFields() {
		Assert.assertEquals(mysql.SearchBook(null,null), one);
		Assert.assertEquals(mysql.SearchBook("harry",null), one);
		Assert.assertEquals(mysql.SearchBook(null,cb).equals(cb), one);
	}
	public void testSearchBookByTitle(){
		cb = new ArrayList<Integer>();
		cb.add(0,2);
		expected = (ArrayList<BookET>) mysql.SearchBook("A Brief History of Time",cb);
		Assert.assertEquals((int)(((BookET)expected.get(0)).getBID()), 9);
	}
	
	public void testSearchBookByAuthor(){
		cb = new ArrayList<Integer>();
		cb.add(0,3);
		expected = (ArrayList<BookET>) mysql.SearchBook("Tony Schwartz",cb);
		Assert.assertEquals((int)(((BookET)expected.get(0)).getBID()), 10);
	}
	
	public void testSearchBookBySummery()
	{
		cb = new ArrayList<Integer>();
		cb.clear();
		cb.add(0,5);
		expected = (ArrayList<BookET>) mysql.SearchBook("2",cb);
		Assert.assertEquals((int)(((BookET)expected.get(0)).getBID()), 14);
	}
	
	public void testSearchBookByGenre(){
		cb = new ArrayList<Integer>();
		cb.add(0,20);
		expected = (ArrayList<BookET>) mysql.SearchBook("8",cb);
		Assert.assertEquals((int)(((BookET)expected.get(0)).getBID()), 18);
	}
	
	public void testSearchBookByLanguage(){
		cb = new ArrayList<Integer>();
		cb.add(0,4);
		expected = (ArrayList<BookET>) mysql.SearchBook("Indian",cb);
		Assert.assertEquals((int)(((BookET)expected.get(0)).getBID()), 9);
	}
	
	public void testSearchBookByKeywords(){
		cb = new ArrayList<Integer>();
		cb.add(0,7);
		expected = (ArrayList<BookET>) mysql.SearchBook("Dr",cb);
		Assert.assertEquals((int)(((BookET)expected.get(0)).getBID()), 23);
	}

}
