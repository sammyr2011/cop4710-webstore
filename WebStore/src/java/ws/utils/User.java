package ws.utils;

/**
 * User is the normal user account without administrator privlidges
 * @author Team 10
 */
public class User extends Account
{
	/**
	 * Creates a new user
	 * @param id - Unique user ID
	 * @param userName - Username
	 * @param email - E-mail address
	 * @param firstName - First name
	 * @param lastName - Last name
	 */
	public User(int id, String userName, String email, String firstName, String lastName, String phone, String address)
	{
		super(id, userName, email, firstName, lastName, phone, address, false);
	}
}
