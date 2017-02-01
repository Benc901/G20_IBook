package JUintTests;

import iBookServer.IBookServer;
import iBookServer.mysqlConnection;
import iBookServer.serverUI;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

public class RemoveABookJUnit extends TestCase{

	int zero=0, one=1,nagtive=-1;
	private mysqlConnection mysql;
	private serverUI window = new serverUI(new IBookServer(5555));
	@Test
	public void test() {
		mysql = new mysqlConnection(window, "root", "root");
		Assert.assertEquals(mysql.DeleteBook(20), one);
		Assert.assertEquals(mysql.DeleteBook(20), zero);
		Assert.assertEquals(mysql.DeleteBook(0), zero);
		Assert.assertEquals(mysql.DeleteBook(-4), zero);
	}

}
