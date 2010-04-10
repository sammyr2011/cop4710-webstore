package ws.utils;

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
import org.apache.commons.lang.xwork.StringUtils;

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
		Account,
		Product,
		Manufacturer,
		Review,
		Transaction
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
	 * @param phone - Phone number of the user to add
	 * @param address - Address of the user to add
	 * @param admin - Flag to determine if the user is an administrator
	 * @return True on success, false on failure
	 */
	public boolean addUser(String username, String email, String password, String firstName, String lastName, String phone, String address, Boolean admin)
	{
		// TODO: Might look better with String.format? It's a mess right now
		String query = "INSERT INTO `webstore`.`users` ( `UserID`, `Username`, `email`, `Password`, `FirstName`, `LastName`, `Address`, `Phone`, `IsAdmin` ) "
				+ "VALUES ( NULL , '"
				+ Utils.sanitize(username) + "', '"
				+ Utils.sanitize(email) + "', '"
				+ Utils.hash(password) + "', '"
				+ Utils.sanitize(firstName) + "', '"
				+ Utils.sanitize(lastName) + "', '"
				+ Utils.sanitize(address) + "', '"
				+ Utils.sanitize(phone) + "', '"
				+ (admin ? 1 : 0) + "');";

		System.out.println("Executing: " + query.toString());
		return executeQueryUpdate(query);
	}

	/**
	 * Adds a new product to the database
	 * @param name - Name of the product
	 * @param manufacturerId - ID of the product's manufacturer
	 * @param price - Price of the product
	 * @param stock - Amount of the product in stock
	 * @param image - Filename of the product image
	 * @param description - Description of the product
	 * @return True on success
	 */
	public boolean addProduct(String name, int manufacturerId, double price, int stock, String image, String description)
	{
		// TODO: Check manufacturer id to see if it exists?
		String query = "INSERT INTO `webstore`.`product` ( `ProductID`, `Name`, `ManufacturerID`, `Price`, `Stock`, `Image`, `Description` ) "
				+ "VALUES ( NULL , '"
				+ Utils.sanitize(name) + "', '"
				+ manufacturerId + "', '"
				+ price + "', '"
				+ stock + "', '"
				+ Utils.sanitize(image) + "', '"
				+ Utils.sanitize(description) + "');";

		System.out.println("Executing: " + query.toString());
		return executeQueryUpdate(query);
	}

	/**
	 * Adds a new manufacturer to the database
	 * @param name - Name of the manufacturer
	 * @param website - Manufacturer's website
	 * @return True on success
	 */
	public boolean addManufacturer(String name, String website)
	{
		String query = "INSERT INTO `webstore`.`manufacturer` ( `mID`, `Website`, `CompanyName` ) "
				+ "VALUES ( NULL , '"
				+ Utils.sanitize(website) + "', '"
				+ Utils.sanitize(name) + "');";

		System.out.println("Executing: " + query.toString());
		return executeQueryUpdate(query);
	}

	/**
	 * Adds a new review to the database
	 * @param userId - Unique ID of user making the review
	 * @param productId - Unique ID of product reviewed
	 * @param rating - User rating
	 * @param comment - Review text
	 * @return True on success
	 */
	public boolean addReview(int userId, int productId, int rating, String comment)
	{
		// TODO: Check product id to see if it exists?
		// TODO: Check user id to see if it exists?
		// TODO: Check rating to see if it's within range? [1..10]?
		String query = "INSERT INTO `webstore`.`reviews` ( `ReviewID`, `UserID`, `ProductID`, `Rating`, `Comment` ) "
				+ "VALUES ( NULL , '"
				+ productId + "', '"
				+ rating + "', '"
				+ Utils.sanitize(comment) + "');";

		System.out.println("Executing: " + query.toString());
		return executeQueryUpdate(query);
	}

	/**
	 * Adds a new transaction report to the database
	 * @param userId - Unique ID of user making the purchase
	 * @param productId - Unique ID of product purchased
	 * @param price - Price of product at time of transaction
	 * @param shippingPrice - Shipping price
	 * @param shippingAddress - Shipping address
	 * @return True on success
	 */
	public boolean addTransaction(int userId, int productId, double price, double shippingPrice, String shippingAddress)
	{
		// TODO: Check user id to see if it exists?
		// TODO: Check product id to see if it exists?
		String query = "INSERT INTO `webstore`.`purchases` ( `PurchaseID`, `UserID`, `ProductID`, `Price`, `ShippingAddress`, `Date`, `ShippingPrice` )"
				+ "VALUES ( NULL , '"
				+ productId + "', '"
				+ price + "', '"
				+ shippingPrice + "', '"
				+ Utils.sanitize(shippingAddress) + "');";

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
		String query = "SELECT * FROM `users` WHERE `userName` = '" + Utils.sanitize(userName) + "' AND `password` = '" + Utils.hash(password) + "' LIMIT 1 ;";

		Vector<Account> fetchedUsers = (Vector<Account>) executeQuery(query, DataType.Account);

		if (fetchedUsers.size() != 1)
		{
			return null;
		}

		return fetchedUsers.get(0);
	}

	/**
	 * Returns the product with specified ID
	 * @param id - Unique product id to get
	 * @return null on failure, product on success
	 */
	public Product getProduct(int id)
	{
		String query = "SELECT * FROM `product` WHERE `ProductId` = '" + id + "' LIMIT 1 ;";

		Vector<Product> fetchedData = (Vector<Product>) executeQuery(query, DataType.Product);

		if (fetchedData.size() != 1)
		{
			return null;
		}

		return fetchedData.get(0);
	}

	/**
	 * Returns the manufacturer with specified ID
	 * @param id - Unique ID of manufacturer to get
	 * @return null on failure, product on success
	 */
	public Manufacturer getManufacturer(int id)
	{
		String query = "SELECT * FROM `manufacturer` WHERE `mId` = '" + id + "' LIMIT 1 ;";

		Vector<Manufacturer> fetchedData = (Vector<Manufacturer>) executeQuery(query, DataType.Manufacturer);

		if (fetchedData.size() != 1)
		{
			return null;
		}

		return fetchedData.get(0);
	}

	
	/**
	 * Returns the manufacturer's name
	 * @param id - ID of manufacturer
	 * @return Name of manufacturer
	 */
	public String getManufacturerName(int id)
	{
		// TODO: Would be much better to get just the name from the database
		Manufacturer m = getManufacturer(id);

		return m.getName();
	}

	/**
	 * Returns the review with specified ID
	 * @param id - Unique ID of review to get
	 * @return null on failure, Review on success
	 */
	public Review getReview(int id)
	{
		String query = "SELECT * FROM `reviews` WHERE `ReviewId` = '" + id + "' LIMIT 1 ;";

		Vector<Review> fetchedData = (Vector<Review>) executeQuery(query, DataType.Review);

		if (fetchedData.size() != 1)
		{
			return null;
		}

		return fetchedData.get(0);
	}

	/**
	 * Returns the transaction with specified ID
	 * @param id - Unique ID of transaction to get
	 * @return null on failure, Transaction on success
	 */
	public Transaction getTransaction(int id)
	{
		String query = "SELECT * FROM `purchases` WHERE `purchaseId` = '" + id + "' LIMIT 1 ;";

		Vector<Transaction> fetchedData = (Vector<Transaction>) executeQuery(query, DataType.Transaction);

		if (fetchedData.size() != 1)
		{
			return null;
		}

		return fetchedData.get(0);
	}

	/**
	 * Edits user information. Fields may be null or empty if change is not desired.
	 * @param id - Unique user ID
	 * @param userName - Username
	 * @param email - E-mail address
	 * @param firstName - First name
	 * @param lastName - Last name
	 * @param phone - Phone number ( May be blank )
	 * @param address - Address
	 * @param admin - Flag to determine if the user is an administrator
	 * @param password - New user password
	 * @return True on success, false on failure
	 */
	public boolean editUser(int id, String userName, String email, String firstName, String lastName, String phone, String address, Boolean admin, String password)
	{
		StringBuilder query = new StringBuilder();

		query.append("UPDATE `users` SET   ");

		if (!StringUtils.isEmpty(userName))
		{
			query.append(String.format("`%s` = '%s', ", "username", Utils.sanitize(userName)));
		}
		if (!StringUtils.isEmpty(email))
		{
			query.append(String.format("`%s` = '%s', ", "email", Utils.sanitize(email)));
		}
		if (!StringUtils.isEmpty(firstName))
		{
			query.append(String.format("`%s` = '%s', ", "firstName", Utils.sanitize(firstName)));
		}
		if (!StringUtils.isEmpty(lastName))
		{
			query.append(String.format("`%s` = '%s', ", "lastName", Utils.sanitize(lastName)));
		}
		// Empty string is valid for phone number, but if it's null don't modify it
		if (phone != null)
		{
			query.append(String.format("`%s` = '%s', ", "phone", Utils.sanitize(phone)));
		}
		if (!StringUtils.isEmpty(address))
		{
			query.append(String.format("`%s` = '%s', ", "address", Utils.sanitize(address)));
		}
		if (admin != null)
		{
			query.append(String.format("`%s` = '%s', ", "isadmin", admin ? "1" : "0"));
		}
		if (!StringUtils.isEmpty(password))
		{
			query.append(String.format("`%s` = '%s', ", "password", Utils.hash(password)));
		}

		// Just to remove the trailing comma. There are much better ways of going about this, but
		//   I just want to get this done.
		query.delete(query.length() - 2, query.length() + 1);
		query.append(String.format("WHERE `UserId` = %d LIMIT 1", id));

		System.out.println("Executing: " + query.toString());
		return executeQueryUpdate(query.toString());
	}

	/**
	 * Edits product information. Fields may be null if change is not desired.
	 * @param id - ID of product to edit
	 * @param name - Name of the product
	 * @param manufacturerId - ID of the product's manufacturer
	 * @param price - Price of the product
	 * @param stock - Amount of the product in stock
	 * @param image - Filename of the product image
	 * @param description - Description of the product
	 * @return true on success
	 */
	public boolean editProduct(int id, String name, Integer manufacturerId, Double price, Integer stock, String image, String description)
	{
		StringBuilder query = new StringBuilder();

		query.append("UPDATE `product` SET ");

		if (StringUtils.isNotEmpty(name))
		{
			query.append(String.format("`%s` = '%s', ", "name", Utils.sanitize(name)));
		}
		if (manufacturerId != null)
		{
			query.append(String.format("`%s` = '%d', ", "ManufacturerId", manufacturerId));
		}
		if (price != null)
		{
			query.append(String.format("`%s` = '%f', ", "price", price));
		}
		if (stock != null)
		{
			query.append(String.format("`%s` = '%d', ", "stock", stock));
		}
		if (image != null)
		{
			query.append(String.format("`%s` = '%s', ", "image", Utils.sanitize(image)));
		}
		if (description != null)
		{
			query.append(String.format("`%s` = '%s', ", "description", Utils.sanitize(description)));
		}

		// Just to remove the trailing comma. There are much better ways of going about this, but
		//   I just want to get this done.
		query.delete(query.length() - 2, query.length() + 1);
		query.append(String.format("WHERE `ProductId` = %d LIMIT 1", id));

		System.out.println("Executing: " + query.toString());
		return executeQueryUpdate(query.toString());
	}

	/**
	 * Edits manufacturer information. Fields may be null if change is not desired.
	 * @param id - ID of manufacturer to edit
	 * @param name - Name of the manufacturer
	 * @param website - Manufacturer's website
	 * @return true on success
	 */
	public boolean editManufacturer(int id, String name, String website)
	{
		StringBuilder query = new StringBuilder();

		query.append("UPDATE `manufacturer` SET ");

		if (StringUtils.isNotEmpty(name))
		{
			query.append(String.format("`%s` = '%s', ", "CompanyName", Utils.sanitize(name)));
		}
		if (website != null)
		{
			query.append(String.format("`%s` = '%s', ", "Website", Utils.sanitize(website)));
		}

		// Just to remove the trailing comma. There are much better ways of going about this, but
		//   I just want to get this done.
		query.delete(query.length() - 2, query.length() + 1);
		query.append(String.format("WHERE `mId` = %d LIMIT 1", id));

		System.out.println("Executing: " + query.toString());
		return executeQueryUpdate(query.toString());
	}

	/**
	 * Edits review information. Fields may be null if change is not desired.
	 * @param id - ID of review to edit
	 * @param userId - Unique ID of user making the review
	 * @param productId - Unique ID of product reviewed
	 * @param rating - User rating
	 * @param comment - Review text
	 * @return true on success
	 */
	public boolean editReview(int id, Integer userId, Integer productId, Integer rating, String comment)
	{
		StringBuilder query = new StringBuilder();

		query.append("UPDATE `reviews` SET ");

		if (userId != null)
		{
			query.append(String.format("`%s` = '%d', ", "userId", userId));
		}
		if (productId != null)
		{
			query.append(String.format("`%s` = '%d', ", "productId", productId));
		}
		if (rating != null)
		{
			query.append(String.format("`%s` = '%d', ", "rating", productId));
		}
		if (StringUtils.isNotEmpty(comment))
		{
			query.append(String.format("`%s` = '%s', ", "comment", Utils.sanitize(comment)));
		}

		// Just to remove the trailing comma. There are much better ways of going about this, but
		//   I just want to get this done.
		query.delete(query.length() - 2, query.length() + 1);
		query.append(String.format("WHERE `ReviewId` = %d LIMIT 1", id));

		System.out.println("Executing: " + query.toString());
		return executeQueryUpdate(query.toString());
	}

	/**
	 * Edits transaction information. Fields may be null if change is not desired.
	 * @param id - Unique transaction ID
	 * @param userId - Unique ID of user making the purchase
	 * @param productId - Unique ID of product purchased
	 * @param price - Price of product at time of transaction
	 * @param shippingPrice - Shipping price
	 * @param shippingAddress - Shipping address
	 * @return true on success
	 */
	public boolean editTransaction(int id, Integer userId, Integer productId, Double price, Double shippingPrice, String shippingAddress)
	{
		StringBuilder query = new StringBuilder();

		query.append("UPDATE `purchases` SET ");

		if (userId != null)
		{
			query.append(String.format("`%s` = '%d', ", "userId", userId));
		}
		if (productId != null)
		{
			query.append(String.format("`%s` = '%d', ", "productId", productId));
		}
		if (price != null)
		{
			query.append(String.format("`%s` = '%f', ", "price", price));
		}
		if (shippingPrice != null)
		{
			query.append(String.format("`%s` = '%f', ", "shippingPrice", shippingPrice));
		}
		if (shippingAddress != null)
		{
			query.append(String.format("`%s` = '%s', ", "shippingAddress", Utils.sanitize(shippingAddress)));
		}

		// Just to remove the trailing comma. There are much better ways of going about this, but
		//   I just want to get this done.
		query.delete(query.length() - 2, query.length() + 1);

		query.append(String.format("WHERE `purchases` = '%d' LIMIT 1", id));

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
	 * Returns a list containing all products in the database
	 * @return List of all product
	 */
	public Vector<Product> getProducts()
	{
		String query = "SELECT * FROM `product`";

		return (Vector<Product>) executeQuery(query, DataType.Product);
	}

	/**
	 * Returns a list containing all products in the database with specified manufacturer ID
	 * @param manufacturerId - ID of manufacturer
	 * @return List of all product with specified manufacturer
	 */
	public Vector<Product> getProducts(int manufacturerId)
	{
		String query = String.format("SELECT * FROM `product` WHERE `manufacturerId` = '%d'", manufacturerId);

		return (Vector<Product>) executeQuery(query, DataType.Product);
	}

	/**
	 * Returns a list of products containing specified string
	 * @param searchString - String to search for in description in name of products
	 * @return List of products contining specified string
	 */
	public Vector<Product> getProducts(String searchString)
	{
		String query = String.format("SELECT * FROM `product` WHERE `description` LIKE '%%%d%%' OR `name` LIKE '%%%s%%'", searchString, searchString);

		return (Vector<Product>) executeQuery(query, DataType.Product);
	}

	/**
	 * Returns a list containing all manufacturers in the database
	 * @return List of all manufacturers
	 */
	public Vector<Manufacturer> getManufacturers()
	{
		String query = "SELECT * FROM `Manufacturer`";

		return (Vector<Manufacturer>) executeQuery(query, DataType.Manufacturer);
	}

	/**
	 * Returns a list containing all reviews in the database
	 * @return List of all reviews
	 */
	public Vector<Review> getReviews()
	{
		String query = "SELECT * FROM `reviews`";

		return (Vector<Review>) executeQuery(query, DataType.Review);
	}

	/**
	 * Returns a list containing all reviews of specified product
	 * @param productId - ID of product
	 * @return List of reviews for specified product
	 */
	public Vector<Review> getReviews(int productId)
	{
		String query = String.format("SELECT * FROM `reviews` WHERE `ProductId` = '%d'", productId);

		return (Vector<Review>) executeQuery(query, DataType.Review);
	}

	/**
	 * Returns a list containing all transactions in the database
	 * @return List of all transactions
	 */
	public Vector<Transaction> getTransactions()
	{
		String query = "SELECT * FROM `purchases`";

		return (Vector<Transaction>) executeQuery(query, DataType.Transaction);
	}

	/**
	 * Returns a list containing all transactions of a specified user
	 * @param userId - ID of user
	 * @return List of all transactions of user
	 */
	public Vector<Transaction> getTransactions(int userId)
	{
		String query = String.format("SELECT * FROM `purchases` WHERE `UserId` = '%d'", userId);

		return (Vector<Transaction>) executeQuery(query, DataType.Transaction);
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
				queryExistingData = "SELECT `UserId`, `username` FROM `users` WHERE (`username` = '" + Utils.sanitize((String) unique.get("username")) + "' ) LIMIT 1;";
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

	private boolean checkForExistingRow(int id, String tableName, String attributeName)
	{
		String query = String.format("SELECT 1 FROM `%s` WHERE (`%s` = '%d' ) LIMIT 1;", tableName, attributeName, id);

		Integer existingId = executeQuerySingleResultInt(query);

		return existingId != null;
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
		System.out.println("executeQuery: " + query);
		// TODO: ? Break this function down or get rid of it, it's a horrible mess
		Vector<Account> fetchedAccount = null;
		Vector<Product> fetchedProduct = null;
		Vector<Manufacturer> fetchedManufacturer = null;
		Vector<Review> fetchedReview = null;
		Vector<Transaction> fetchedTransaction = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		// Create only the dataType container that we will be using
		switch (dataType)
		{
			case Account:
				fetchedAccount = new Vector<Account>();
				break;
			case Product:
				fetchedProduct = new Vector<Product>();
				break;
			case Manufacturer:
				fetchedManufacturer = new Vector<Manufacturer>();
				break;
			case Review:
				fetchedReview = new Vector<Review>();
				break;
			case Transaction:
				fetchedTransaction = new Vector<Transaction>();
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
									Utils.unsanatize(result.getString("userName")),
									Utils.unsanatize(result.getString("email")),
									Utils.unsanatize(result.getString("firstName")),
									Utils.unsanatize(result.getString("lastName")),
									Utils.unsanatize(result.getString("phone")),
									Utils.unsanatize(result.getString("address"))));
						}
						else
						{
							fetchedAccount.add(
									new User(
									result.getInt("UserId"),
									Utils.unsanatize(result.getString("userName")),
									Utils.unsanatize(result.getString("email")),
									Utils.unsanatize(result.getString("firstName")),
									Utils.unsanatize(result.getString("lastName")),
									Utils.unsanatize(result.getString("phone")),
									Utils.unsanatize(result.getString("address"))));
						}
						break;
					case Product:
						fetchedProduct.add(
								new Product(
								result.getInt("ProductId"),
								Utils.unsanatize(result.getString("name")),
								result.getInt("ManufacturerID"),
								result.getDouble("price"),
								result.getInt("stock"),
								Utils.unsanatize(result.getString("image")),
								Utils.unsanatize(result.getString("description"))));
						break;
					case Manufacturer:
						fetchedManufacturer.add(
								new Manufacturer(
								result.getInt("mId"),
								Utils.unsanatize(result.getString("CompanyName")),
								Utils.unsanatize(result.getString("Website"))));
						break;
					case Review:
						fetchedReview.add(
								new Review(
								result.getInt("ReviewId"),
								result.getInt("UserId"),
								result.getInt("ProductId"),
								result.getInt("Rating"),
								Utils.unsanatize(result.getString("Comment"))));
						break;
					case Transaction:
						fetchedTransaction.add(
								new Transaction(
								result.getInt("PurchaseId"),
								result.getInt("UserId"),
								result.getInt("ProductId"),
								result.getDouble("Price"),
								result.getDouble("ShippingPrice"),
								Utils.unsanatize(result.getString("ShippingAddress")),
								result.getTimestamp("date")));
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
			case Manufacturer:
				return fetchedManufacturer;
			case Product:
				return fetchedProduct;
			case Review:
				return fetchedReview;
			case Transaction:
				return fetchedTransaction;
		}

		return new Vector<Object>();
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

