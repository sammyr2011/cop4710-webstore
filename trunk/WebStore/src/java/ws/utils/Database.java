package ws.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Primary data object for project.
 * @author Team 10
 */
public class Database
{
	/**
	 * Connection string of the database
	 */
	private static final String connectionString = "jdbc:mysql://localhost:3306/webstore";
	/**
	 * Name of the user to connect to the database as
	 */
	private static final String connectionName = "4710";
	/**
	 * Password of the user to connect to the database as
	 */
	private static final String connectionPass = "asdf";

	/**
	 * Type of data to fetch from database. Mainly used in executeQuery(String, DataType)
	 */
	private enum DataType
	{
		/**
		 * Account data
		 */
		Account,
	}

	/**
	 * Part of singleton pattern
	 * http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
	 */
	private static class LazyHolder
	{
		private static final Database database = new Database();
	}

	/**
	 * Retrieves the database instance
	 * @return Instance of the database.
	 */
	public static Database getInstance()
	{
		return LazyHolder.database;
	}

	/**
	 * Creates a connection to the database
	 * @return Connection to the database
	 */
	private Connection connect() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		return DriverManager.getConnection(connectionString, connectionName, connectionPass);
	}

	/**
	 * Adds a new account to the database
	 * @param username - Username of the user to add
	 * @param email - Email address of the user to add
	 * @param password - Password of the user to add
	 * @param firstName - First name of the user to add
	 * @param lastName - Last name of the user to add
	 * @param admin - Flag to determine if the user is an administrator
	 * @return True on success, false on failure
	 */
	public boolean addUser(String username, String email, String password, String firstName, String lastName, String phone, String address, Boolean admin)
	{
		String query = "INSERT INTO `webstore`.`users` (`UserID`, `Username`, `email`, `Password`, `FirstName`, `LastName`, `Address`, `Phone`, `IsAdmin`) "
				+ "VALUES ( NULL , '"
				+ sanitize(username) + "', '"
				+ sanitize(email) + "', '"
				+ hash(sanitize(password)) + "', '"
				+ sanitize(firstName) + "', '"
				+ sanitize(lastName) + "', '"
				+ sanitize(address) + "', '"
				+ sanitize(phone) + "', '"
				+ (admin ? 1 : 0) + "');";

		System.out.println("Executing: " + query.toString());
		return executeQueryUpdate(query);
	}

	/**
	 * Retrieves a user based on unique ID
	 * @param userId - Unique ID of the user to retrieve
	 * @return Null when user ID is invalid. Account of user with ID of userId when successful
	 */
	public Account getUser(int userId)
	{
		String query = "SELECT * FROM `users` WHERE `UserId` = " + userId + " LIMIT 1 ;";

		Vector<Account> fetchedUsers = (Vector<Account>) executeQuery(query, DataType.Account);

		if (fetchedUsers.size() != 1)
		{
			return null;
		}

		return fetchedUsers.get(0);
	}

	/**
	 * Retrieves a user based on a username and password
	 * @param userName - User username
	 * @param password - User password
	 * @return Null when invalid credentials. Account of user on success
	 */
	public Account getUser(String userName, String password)
	{
		String query = "SELECT * FROM `users` WHERE `userName` = '" + sanitize(userName) + "' AND `password` = '" + hash(sanitize(password)) + "' LIMIT 1 ;";

		Vector<Account> fetchedUsers = (Vector<Account>) executeQuery(query, DataType.Account);

		if (fetchedUsers.size() != 1)
		{
			return null;
		}

		return fetchedUsers.get(0);
	}

	/**
	 * Edits user information. Fields may be null or empty if change is not desired.
	 * @param id - Unique user ID
	 * @param userName - Username
	 * @param email - E-mail address
	 * @param firstName - First name
	 * @param lastName - Last name
	 * @param admin - Flag to determine if the user is an administrator
	 * @param password - New user password
	 * @return True on success, false on failure
	 */
	public boolean editUser(int id, String userName, String email, String firstName, String lastName, String phone, String address, Boolean admin, String password)
	{
		StringBuilder query = new StringBuilder();

		query.append("UPDATE `users` SET   ");

		if (userName != null && userName.length() > 0)
		{
			query.append(String.format("`%s` = '%s', ", "username", sanitize(userName)));
		}
		if (email != null && email.length() > 0)
		{
			query.append(String.format("`%s` = '%s', ", "email", sanitize(email)));
		}
		if (firstName != null && firstName.length() > 0)
		{
			query.append(String.format("`%s` = '%s', ", "firstName", sanitize(firstName)));
		}
		if (lastName != null && lastName.length() > 0)
		{
			query.append(String.format("`%s` = '%s', ", "lastName", sanitize(lastName)));
		}
		if (phone != null && phone.length() > 0)
		{
			query.append(String.format("`%s` = '%s', ", "phone", sanitize(phone)));
		}
		if (address != null && address.length() > 0)
		{
			query.append(String.format("`%s` = '%s', ", "address", sanitize(address)));
		}
		if (admin != null)
		{
			query.append(String.format("`%s` = '%s', ", "isadmin", admin ? "1" : "0"));
		}
		if (password != null && password.length() > 0)
		{
			query.append(String.format("`%s` = '%s', ", "password", hash(sanitize(password))));
		}

		// Just to remove the trailing comma. There are much better ways of going about this, but
		//   I just want to get this done.
		query.delete(query.length() - 2, query.length() + 1);

		query.append("WHERE `UserId` = ");
		query.append(id);
		query.append(" LIMIT 1 ;");

		System.out.println("Executing: " + query.toString());
		return executeQueryUpdate(query.toString());
	}

	/**
	 * Obtains a list of all the users. For use in user management system.
	 * @return List of all users
	 */
	public Vector<Account> getUsers()
	{
		String query = "SELECT * FROM `users`";

		return (Vector<Account>) executeQuery(query, DataType.Account);
	}

	/**
	 * Obtains a list of all usernames. Currently only for the login page for testing purposes only
	 * @return list of all usernames
	 */
	public Vector<String> getUsernames()
	{
		String query = "SELECT * FROM `users`";
		Vector<String> usernames = new Vector<String>();

		Vector<Account> users = (Vector<Account>) executeQuery(query, DataType.Account);

		for (Account account : users)
		{
			usernames.add(account.getUserName());
		}

		return usernames;
	}

	/**
	 * Checks to see if a username already exists
	 * @param username - Username to check to see if it's a duplicate
	 * @return null when data does not exist, ID of data entry if data already exists
	 */
	public Integer checkForExistingAccount(String username)
	{
		Hashtable<String, Object> values = new Hashtable<String, Object>();

		values.put("username", username);

		return checkForExistingData(values, DataType.Account);
	}

	/**
	 * Checks to see if data with specific values already exist in the database
	 *		Account - username
	 * @param unique - Map of unique values to check for
	 * @param dataType - Type of data to check
	 * @return null when data does not exist, ID of data entry if data already exists
	 */
	private Integer checkForExistingData(Map<String, Object> unique, DataType dataType)
	{
		String queryExistingData = null;

		switch (dataType)
		{
			case Account:
				queryExistingData = "SELECT `UserId`, `username` FROM `users` WHERE (`username` = '" + sanitize((String) unique.get("username")) + "' ) LIMIT 1;";
				break;
			default:
				return null;
		}

		Integer existingId = executeQuerySingleResultInt(queryExistingData);
		if (existingId != null)
		{
			return existingId;
		}

		return null;
	}

	/**
	 * Executes a single query string and returns the first result as an Integer
	 * @param query - SQL Query to execute
	 * @return Integer result of query. Null on failure.
	 */
	private Integer executeQuerySingleResultInt(String query)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try
		{
			connection = connect();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			if (!result.next())
			{
				return null;
			}

			// Would use result.getObject(1), but Object <-> Integer breaks my copy of java in bad ways
			return result.getInt(1);
		}
		catch (Exception ex)
		{
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Failed to executeQuerySingleResultInt", ex);
		}
		finally
		{
			closeConnections(connection, statement, result);
		}

		return null;
	}

	/**
	 * Executes an SQL query and retrieves a list of the results
	 * @param query - SQL query string
	 * @param dataType - Type of data to be retrieved
	 * @return Results of the query, a list of objects based on dataType
	 */
	private Vector<?> executeQuery(String query, DataType dataType)
	{
		Vector<Account> fetchedAccount = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		// Create only the dataType container that we will be using
		switch (dataType)
		{
			case Account:
				fetchedAccount = new Vector<Account>();
				break;
		}

		try
		{
			connection = connect();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			while (result.next())
			{
				// Fill the dataType container with information from the query result
				switch (dataType)
				{
					case Account:
						if (result.getBoolean("isadmin"))
						{
							fetchedAccount.add(
									new Admin(
									result.getInt("UserId"),
									unsanatize(result.getString("userName")),
									unsanatize(result.getString("email")),
									unsanatize(result.getString("firstName")),
									unsanatize(result.getString("lastName")),
									unsanatize(result.getString("phone")),
									unsanatize(result.getString("address"))));
						}
						else
						{
							fetchedAccount.add(
									new User(
									result.getInt("UserId"),
									unsanatize(result.getString("userName")),
									unsanatize(result.getString("email")),
									unsanatize(result.getString("firstName")),
									unsanatize(result.getString("lastName")),
									unsanatize(result.getString("phone")),
									unsanatize(result.getString("address"))));
						}
						break;
				}
			}
		}
		catch (Exception ex)
		{
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Failed to get data type " + dataType.name() + " in executeQuery", ex);
		}
		finally
		{
			closeConnections(connection, statement, result);
		}

		// Return the dataType container
		switch (dataType)
		{
			case Account:
				return fetchedAccount;
		}

		return new Vector<Object>();
	}

	/**
	 * Sanitizes a string for storing. Encodes all non alphanumeric characters
	 * @param input - String to be sanitized
	 * @return Sanitized string
	 */
	private String unsanatize(String input)
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
	private String sanitize(String input)
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
	private String hash(String input)
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

	/**
	 * Creates a hash out of supplied text after being made lowercase and with whitespace removed
	 * @param text - Text to create a hash of
	 * @return Hashed of text
	 */
	private String hashStrippedText(String text)
	{
		return hash(text.toLowerCase().replaceAll("\\s+", ""));
	}

	/**
	 * Executes an update query
	 * @param query - Query to execute
	 * @return true on success, false on failure
	 */
	private boolean executeQueryUpdate(String query)
	{
		Connection connection = null;
		Statement statement = null;

		try
		{
			connection = connect();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			return true;
		}
		catch (Exception ex)
		{
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Failed to executeQueryUpdate", ex);
		}
		finally
		{
			closeConnections(connection, statement, null);
		}

		return false;
	}

	/**
	 * Closes all of the listed connections.
	 * @param connection - Connection to be closed. May be null.
	 * @param statement - Statement to be closed. May be null.
	 * @param result - ResultSet to be closed. May be null.
	 */
	private void closeConnections(Connection connection, Statement statement, ResultSet result)
	{
		if (connection != null)
		{
			try
			{
				connection.close();
			}
			catch (SQLException ex)
			{
				Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Failed to close connection", ex);
			}
		}

		if (statement != null)
		{
			try
			{
				statement.close();
			}
			catch (SQLException ex)
			{
				Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Failed to close statement", ex);
			}
		}

		if (result != null)
		{
			try
			{
				result.close();
			}
			catch (SQLException ex)
			{
				Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Failed to close result", ex);
			}
		}
	}
}

