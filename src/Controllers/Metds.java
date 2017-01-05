package Controllers;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;


/**
 * <b>Metds</b> - A class of statics methods which provides validation tests for the user input.
 * 
 * @author G11
 *
 */
public class Metds 
{
	static final Pattern userPattern = Pattern.compile("[A-Za-z0-9_]+");
	static final Pattern idPattern = Pattern.compile("[0-9]+");
	
	/**
	 * <b>isUserValid</b> - this methods checks for username correctness.
	 * <br>The username can <b>ONLY</b> contains english letters and numbers.
	 * <br> 
	 * @param str - the username input. 
	 * @return
	 * 		<b>Boolean</b> value. <b>True</b> if the username is valid, <b>False</b> otherwise.
	 */
	public static Boolean isUserValid(String str)
	{
		if(str.equals("")) {
			JOptionPane.showMessageDialog(null, "Username is empty", "Invalid characters", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if(!userPattern.matcher(str).matches()) {
			JOptionPane.showMessageDialog(null, "Username can contain only the following characters:\n0-9\na-z\nA-Z\n_", "Invalid characters", JOptionPane.ERROR_MESSAGE);
			return false;
		}
			
		return true;
	}//End isUserValid
	
	/**
	 * <b>isPasswordValid</b> - this methods checks for password correctness.
	 * <br>The password can <b>NOT</b> contains spaces.
	 * <br> 
	 * @param str - the password input. 
	 * @return
	 * 		<b>Boolean</b> value. <b>True</b> if the password is valid, <b>False</b> otherwise.
	 */
	public static Boolean isPasswordValid(String str) 
	{
		if(str.equals("")) {
			JOptionPane.showMessageDialog(null, "Password is empty", "Invalid characters", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (str.contains(" ")) {
			JOptionPane.showMessageDialog(null, "Password can't contain spaces", "Invalid characters", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}//End isPasswordValid
	
	
	/**
	 * <b>isIDValid</b> - Static method that checks if patient id is valid.
	 * <br>Means that the id is not empty and contains only digits.
	 *  
	 * @param str - <em>(String)</em> The patient id.
	 * @return <b>Boolean</b> value. <b>True</b> if the id is valid, <b>False</b> otherwise.
	 */
	public static Boolean isIDValid(String str) 
	{
		if(str.equals("")) {
			JOptionPane.showMessageDialog(null, "ID is empty", "Invalid characters", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (!idPattern.matcher(str).matches()) {
			JOptionPane.showMessageDialog(null, "ID can contain only 0-9", "Invalid characters", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}//End isPasswordValid
	
	
	/**
	 * <b>isEMailValid</b> - this methods checks for the email correctness.
	 * <br>The email can <b>NOT</b> contains spaces.
	 * <br> 
	 * @param str - the email input. 
	 * @return
	 * 		<b>Boolean</b> value.  <b>True</b> if the email is valid, <b>False</b> otherwise.
	 */
	public static Boolean isEMailValid(String str)
	{	
		if(str.equals("")) {
			JOptionPane.showMessageDialog(null, "E-Mail is empty", "Invalid characters", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(!str.contains("@")) {
			JOptionPane.showMessageDialog(null, "E-Mail must contain exactly one @", "Invalid characters", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;	
	}//End isEMailValid

	
	/**
	 * <b>isEmptyDiagnosis</b> - Checks if the diagnosis string pass to it is empty.
	 * 
	 * @param text - <em>(String)</em> The diagnosis text
	 * 
	 * @return <b>Boolean</b> value. <b>False</b> if the text is empty, <b>True</b> otherwise.
	 */
	public static boolean isEmptyDiagnosis(String text) {
		if(text.equals("")) {
			JOptionPane.showMessageDialog(null, "Must type a diagnosis", "Empty Text", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
}//End Metds
