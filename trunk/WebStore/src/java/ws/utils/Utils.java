package ws.utils;

import com.opensymphony.xwork2.ActionContext;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.dispatcher.SessionMap;

/**
 *
 * @author Team 10
 */
public class Utils
{
	/**
	 * Sanitizes a string for storing. Encodes all non alphanumeric characters
	 * @param input - String to be sanitized
	 * @return Sanitized string
	 */
	public static String unsanatize(String input)
	{
		try
		{
			return URLDecoder.decode(input, "UTF-8");
		}
		catch (UnsupportedEncodingException ex)
		{
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Unable to encode string", ex);
			return "ERROR";
		}
	}

	/**
	 * Unsanitizes a string. Decodes all non alphanumeric characters into their normal format
	 * @param input - String to be unsanitized
	 * @return unsanitized string
	 */
	public static String sanitize(String input)
	{
		try
		{
			return URLEncoder.encode(input, "UTF-8");
		}
		catch (UnsupportedEncodingException ex)
		{
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Unable to encode string", ex);
			return "ERROR";
		}
	}

	/**
	 * Hashes an input string and returns the result
	 * @param input - Input string to be hashed
	 * @return - Hashed string of input
	 */
	public static String hash(String input)
	{
		byte[] digest = null;
		StringBuilder sb = new StringBuilder();

		MessageDigest md;
		try
		{
			md = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException ex)
		{
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Failed to get MessageDigest instance", ex);
			return "";
		}

		digest = md.digest(input.getBytes());
		for (byte b : digest)
		{
			sb.append(String.format("%02X", b));
		}

		return sb.toString();
	}

	// TODO: Check thread saftey
	/**
	 * Forces the current session to login as a different user
	 *
	 * @param username - Username of user to login as
	 * @param password - Password of user to login as
	 * @return true on successful login
	 */
	public static boolean forceUserLogin(String username, String password)
	{
		Account newUser = Database.getInstance().getUser(username, password);

		if (newUser == null)
		{
			return false;
		}

		Map session = ActionContext.getContext().getSession();
		((SessionMap) session).invalidate();

		session = ActionContext.getContext().getSession();
		session.put("user", newUser);

		return true;
	}

	// TODO: Check thread saftey
	/**
	 * Updates the current user object in the current session. If user is edited without
	 *  updating then the changes won't take place until the user relogs
	 *
	 * @param user - User to update
	 */
	public static void updateUserSession(Account user)
	{
		Account updatedUser = Database.getInstance().getUser(user.getId());

		Map session = ActionContext.getContext().getSession();
		((SessionMap) session).invalidate();

		session = ActionContext.getContext().getSession();
		session.put("user", updatedUser);
	}
}
