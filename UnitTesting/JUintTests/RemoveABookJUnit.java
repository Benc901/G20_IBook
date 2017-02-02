package JUintTests;

import iBookServer.IBookServer;
import iBookServer.mysqlConnection;
import iBookServer.serverUI;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

public class RemoveABookJUnit extends TestCase{

	int zero=0, one=1,nagtive=-1;
	private serverUI window = new serverUI(new IBookServer(5555));
	private mysqlConnection mysql = new mysqlConnection(window, "root", "root");
	
	@Test
	public void testSuccessRemove() {
		
		Assert.assertEquals(mysql.DeleteBook(1), one);
	}
	public void testRemoveFailed()
	{
		Assert.assertEquals(mysql.DeleteBook(0), zero);
	}
	public void testDoubleRemoveFailed()
	{
		Assert.assertEquals(mysql.DeleteBook(20), one);
		Assert.assertEquals(mysql.DeleteBook(20), zero);
	}
	public void testNagtiveFailed()
	{
		Assert.assertEquals(mysql.DeleteBook(-4), zero);
	}
}
