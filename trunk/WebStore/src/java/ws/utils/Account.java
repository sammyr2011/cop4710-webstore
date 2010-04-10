package ws.utils;

/**
 * Account is the base class for all users of the system. Holds basic information and provides access to it
 * @author Team 10
 */
public abstract class Account
{
	/**
	 * Unique account ID
	 */
	protected int id;
	/**
	 * Unique user login
	 */
	protected String userName;
	/**
	 * Email address of account
	 */
	protected String email;
	/**
	 * First name of account
	 */
	private String firstName;
	/**
	 * Last name of account
	 */
	private String lastName;
	/**
	 * Phone number of account
	 */
	private String phone;
	/**
	 * Address of account
	 */
	private String address;
	/**
	 * Flag set only on creation of Admin accounts. Determins if an account is an administrator.
	 */
	private boolean admin;

	/**
	 * Creates a new account
	 * @param id - Unique ID
	 * @param userName - Username
	 * @param email - Email address
	 * @param firstName - First name
	 * @param lastName - Last name
	 * @param phone - Phone number
	 * @param address - Address
	 * @param admin - True if user is an admin
	 */
	public Account(int id, String userName, String email, String firstName, String lastName, String phone, String address, boolean admin)
	{
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.admin = admin;
	}

	/**
	 * @return the username
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * @return the email address
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @return true when user is an admin
	 */
	public boolean isAdmin()
	{
		return admin;
	}

	@Override
	public String toString()
	{
		return userName;
	}

	/**
	 * @return the unqiue ID of the user
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone()
	{
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
}
