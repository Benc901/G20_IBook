package iBookServer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.alee.laf.optionpane.WebOptionPane;


/**
 * Class that run th sql file and reset the data base
 *
 */
public class SET_DB_WITH_SQL_FILE {

	private static InputStream filePath = SET_DB_WITH_SQL_FILE.class.getResourceAsStream("/ibooktest.sql");
	
	private static Connection conn;
	public static String SQLusername = "root";
	public static String SQLpassword = "1234";
	private static boolean catched;
	
	public static void main(String[] args) {
		
		System.out.println("Running SQL file to DB");
		try{

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/",SQLusername,SQLpassword);
			System.out.println("SQL connection succeed on constructor"); 

		}
		catch(Exception e){
			System.err.println("DB Connection Error!!!");
		} 
		
		try {
			InputStream inputstream = filePath;
			importSQL(conn,inputstream);
		} catch (SQLException e) {
			catched = true;
			e.printStackTrace();
		}
		
		if (!catched)
			 WebOptionPane.showMessageDialog ( null, "Data Base reset complete", "DB reset", WebOptionPane.INFORMATION_MESSAGE );
		else{WebOptionPane.showMessageDialog ( null, "Data Base reset failed! ", "DB reset", WebOptionPane.WARNING_MESSAGE );}
		
		IBookServer.sqlCon.SetBooksFiles();

	}

	public static void importSQL(Connection conn, InputStream in) throws SQLException
	{
		Scanner s = new Scanner(in);
		s.useDelimiter("(;(\r)?\n)|(--\n)");
		Statement st = null;
		try
		{
			st = conn.createStatement();
			while (s.hasNext())
			{
				String line = s.next();
				if (line.startsWith("/*!") && line.endsWith("*/"))
				{
					int i = line.indexOf(' ');
					line = line.substring(i + 1, line.length() - " */".length());
				}

				if (line.trim().length() > 0)
				{
					st.execute(line);
				}
			}
		}
		finally
		{
			if (st != null) st.close();
		}
	}
}
