package Controllers;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;


/**
 * class of statics methods which provides validation tests for the user input.
 *
 */
public class Metds 
{
	static final Pattern userPattern = Pattern.compile("[A-Za-z0-9_]+");
	static final Pattern idPattern = Pattern.compile("[0-9]+");
	
	/**
	 * isUserValid - this methods checks for username correctness.
	 * The username can ONLY contains english letters and numbers.
	 * 
	 * @param str - the username input. 
	 * @return
	 * 		Boolean value. True if the username is valid, False otherwise.
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
	 * isPasswordValid - this methods checks for password correctness.
	 * The password can NOT contains spaces.
	 * @param str - the password input. 
	 * @return
	 * 		Boolean value. True if the password is valid, False otherwise.
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
	 * isIDValid - Static method that checks if patient id is valid.
	 * Means that the id is not empty and contains only digits.
	 *  
	 * @param str - (String) The patient id.
	 * @return Boolean value. True if the id is valid, False otherwise.
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
	 * isEMailValid - this methods checks for the email correctness.
	 * The email can NOT contains spaces.
	 * @param str - the email input. 
	 * @return
	 * 		Boolean value.  True if the email is valid, False otherwise.
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
	 * isEmptyDiagnosis - Checks if the diagnosis string pass to it is empty.
	 * 
	 * @param text -(String) The diagnosis text
	 * 
	 * @return Boolean value. False if the text is empty, True otherwise.
	 */
	public static boolean isEmptyDiagnosis(String text) {
		if(text.equals("")) {
			JOptionPane.showMessageDialog(null, "Must type a diagnosis", "Empty Text", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
}
