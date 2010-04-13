package ws.utils;

/**
 * Admin is the administrator class of user.
 * @author Team 10
 */
public class Admin extends Account
{
	/**
	 * Creates an administrator account
	 * @param id - Unique user ID
	 * @param userName - Username
	 * @param email - E-mail address
	 * @param firstName - First name
	 * @param lastName - Last name
	 * @param phone - Phone number
	 * @param address - Address
	 */
	public Admin(int id, String userName, String email, String firstName, String lastName, String phone, String address)
	{
		super(id, userName, email, firstName, lastName, phone, address, true);
	}
}
