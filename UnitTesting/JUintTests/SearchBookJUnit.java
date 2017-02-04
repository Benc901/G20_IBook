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
	private ArrayList<BookET> result;
	private ArrayList<Integer> tempIntArrList;
	private ArrayList<String> tempStrArrList;
	String one= "1";
	
	
	@Test
	public void testSearchBookWithEmptyFields() {
		Assert.assertEquals(mysql.SearchBook(null,null), one);
		Assert.assertEquals(mysql.SearchBook("harry",null), one);
		Assert.assertEquals(mysql.SearchBook(null,tempIntArrList), one);
	}
	public void testSearchBookByTitle(){
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.add(0,2);
		result = (ArrayList<BookET>) mysql.SearchBook("A Brief History of Time",tempIntArrList);
		Assert.assertEquals((int)(((BookET)result.get(0)).getBID()), 9);
	}
	
	public void testSearchNotExistBookByTitle(){
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.add(0,2);
		result = (ArrayList<BookET>) mysql.SearchBook("Gidi",tempIntArrList);
		Assert.assertEquals(result, null);
	}
	
	public void testSearchBookByAuthor(){
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.add(0,3);
		result = (ArrayList<BookET>) mysql.SearchBook("Tony Schwartz",tempIntArrList);
		Assert.assertEquals((int)(((BookET)result.get(0)).getBID()), 10);
	}
	
	public void testSearchNotExistBookByAuthor(){
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.add(0,3);
		result = (ArrayList<BookET>) mysql.SearchBook("Gidi",tempIntArrList);
		Assert.assertEquals(result, null);
	}
	
	public void testSearchBookBySummery()
	{
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.clear();
		tempIntArrList.add(0,5);
		result = (ArrayList<BookET>) mysql.SearchBook("2",tempIntArrList);
		Assert.assertEquals((int)(((BookET)result.get(0)).getBID()), 14);
	}
	
	public void testSearchNotExistBookBySummery()
	{
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.clear();
		tempIntArrList.add(0,5);
		result = (ArrayList<BookET>) mysql.SearchBook("Gidi",tempIntArrList);
		Assert.assertEquals(result, null);
	}
	
	public void testSearchBookByGenre(){
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.add(0,20);
		result = (ArrayList<BookET>) mysql.SearchBook("8",tempIntArrList);
		Assert.assertEquals((int)(((BookET)result.get(0)).getBID()), 18);
	}
	
	public void testSearchNotExistBookByGenre(){
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.add(0,20);
		result = (ArrayList<BookET>) mysql.SearchBook("Gidi",tempIntArrList);
		Assert.assertEquals(result, null);
	}
	
	public void testSearchBookByLanguage(){
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.add(0,4);
		result = (ArrayList<BookET>) mysql.SearchBook("Indian",tempIntArrList);
		Assert.assertEquals((int)(((BookET)result.get(0)).getBID()), 9);
	}
	
	public void testSearchNotExistBookByLanguage(){
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.add(0,4);
		result = (ArrayList<BookET>) mysql.SearchBook("Gidi",tempIntArrList);
		Assert.assertEquals(result, null);
	}
	
	public void testSearchBookByKeywords(){
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.add(0,7);
		result = (ArrayList<BookET>) mysql.SearchBook("Dr",tempIntArrList);
		Assert.assertEquals((int)(((BookET)result.get(0)).getBID()), 23);
	}
	
	public void testSearchNotExistBookByKeywords(){
		tempIntArrList = new ArrayList<Integer>();
		tempIntArrList.add(0,7);
		result = (ArrayList<BookET>) mysql.SearchBook("Gidi",tempIntArrList);
		Assert.assertEquals(result, null);
	}
							/* Advance Search Tests */
	
	public void testSearchAdvBookByTitleAndAuthor(){
		tempStrArrList = new ArrayList<String>();
		tempStrArrList.add("a");
		tempStrArrList.add("b");
		tempStrArrList.add("");
		tempStrArrList.add("");
		tempStrArrList.add("");
		tempStrArrList.add("");
		result = (ArrayList<BookET>) mysql.SearchAdv(tempStrArrList);
		Assert.assertEquals((int)(((BookET)result.get(0)).getBID()), 11);
	}
	
	public void testSearchAdvBookByTitleAuthorAndLanguage(){
		tempStrArrList = new ArrayList<String>();
		tempStrArrList.add("a");
		tempStrArrList.add("b");
		tempStrArrList.add("e");
		tempStrArrList.add("");
		tempStrArrList.add("");
		tempStrArrList.add("");
		result = (ArrayList<BookET>) mysql.SearchAdv(tempStrArrList);
		Assert.assertEquals((int)(((BookET)result.get(0)).getBID()), 11);
	}
	
	public void testSearchAdvBookByTitleAuthorLanguageAndSummary(){
		tempStrArrList = new ArrayList<String>();
		tempStrArrList.add("a");
		tempStrArrList.add("b");
		tempStrArrList.add("e");
		tempStrArrList.add("a");
		tempStrArrList.add("");
		tempStrArrList.add("");
		result = (ArrayList<BookET>) mysql.SearchAdv(tempStrArrList);
		Assert.assertEquals((int)(((BookET)result.get(0)).getBID()), 11);
	}
	
	public void testSearchAdvBookByTitleAuthorLanguageSummaryAndGenre(){
		tempStrArrList = new ArrayList<String>();
		tempStrArrList.add("a");
		tempStrArrList.add("b");
		tempStrArrList.add("e");
		tempStrArrList.add("a");
		tempStrArrList.add("1");
		tempStrArrList.add("");
		result = (ArrayList<BookET>) mysql.SearchAdv(tempStrArrList);
		Assert.assertEquals((int)(((BookET)result.get(0)).getBID()), 11);
	}
	
	public void testSearchAdvBookByTitleAuthorLanguageSummaryGenreAndKeywords(){
		tempStrArrList = new ArrayList<String>();
		tempStrArrList.add("a");
		tempStrArrList.add("b");
		tempStrArrList.add("e");
		tempStrArrList.add("a");
		tempStrArrList.add("1");
		tempStrArrList.add("key");
		result = (ArrayList<BookET>) mysql.SearchAdv(tempStrArrList);
		Assert.assertEquals((int)(((BookET)result.get(0)).getBID()), 11);
	}
	

}
