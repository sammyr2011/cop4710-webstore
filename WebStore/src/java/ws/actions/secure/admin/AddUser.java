package ws.actions.secure.admin;

import ws.utils.Database;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Allows for the creation of new users by administrators
 * @author Team 7
 */
public class AddUser extends ActionSupport
{
	/**
	 * Username of the user to add
	 */
	private String username;
	/**
	 * Email of the user to add
	 */
	private String email;
	/**
	 * Password of the user to add
	 */
	private String password;
	/**
	 * Password to confirm the correct password was entered, not mistyped
	 */
	private String passwordCheck;
	/**
	 * First name of the user to add
	 */
	private String firstName;
	/**
	 * Last name of the user to add
	 */
	private String lastName;
	/**
	 * Flag to determine if the user is an administrator
	 */

	private String phone;
	private String address;

	private boolean admin;
	/**
	 * Flag to determine if a user was previously added (For view purposes only)
	 */
	private boolean userAdded;
	/**
	 * Flag to determine if data was submitted
	 */
	private boolean submit;

	/**
	 * @return
	 * @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception
	{
		if (!isSubmit())
		{
			return INPUT;
		}

		if (!Database.getInstance().addUser(username, email, password, firstName, lastName, phone, address, admin))
		{
			addActionError("Failed to add user to database");
			userAdded = false;
			return ERROR;
		}

		userAdded = true;
		return SUCCESS;
	}

	/**
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate()
	{
		if (!isSubmit())
		{
			return;
		}

		if (!checkString(getUsername()))
		{
			addFieldError("username", "Missing username");
		}
		else
		{
			if(getUsername().length() > 32)
			{
				addFieldError("username", "Username too long");
			}
			if (Database.getInstance().checkForExistingAccount(getUsername()) != null)
			{
				addFieldError("username", "Username already exists");
			}
		}

		if (!checkString(getEmail()))
		{
			addFieldError("email", "Missing email");
		}
		else if(getEmail().length() > 96)
		{
			addFieldError("email", "Email too long");
		}

		if (getPassword() == null || getPassword().length() == 0)
		{
			addFieldError("password", "Missing password");
		}
		else if(getPassword().length() > 32)
		{
			addFieldError("password", "Password too long");
		}
		
		if (getPasswordCheck() == null || getPasswordCheck().length() == 0)
		{
			addFieldError("passwordCheck", "Missing password");
		}

		if (getPassword() != null && getPasswordCheck() != null)
		{
			if (getPassword().compareTo(getPasswordCheck()) != 0)
			{
				addFieldError("passwordCheck", "Passwords do not match");
			}
		}

		if (!checkString(getFirstName()))
		{
			addFieldError("firstName", "Missing first name");
		}
		else if(getFirstName().length() > 75)
		{
			addFieldError("firstName", "First name too long");
		}

		if (!checkString(getLastName()))
		{
			addFieldError("lastName", "Missing last name");
		}
		else if(getLastName().length() > 75)
		{
			addFieldError("lastName", "Last name too long");
		}

		if(!checkPhone(getPhone()))
		{
			addFieldError("phone", "Invalid phone number");
		}
		else if(getPhone().length() > 16)
		{
			addFieldError("phone", "Phone number too long");
		}

		if(!checkString(getAddress()))
		{
			addFieldError("address", "Missing address");
		}
		else if(getAddress().length() > 256)
		{
			addFieldError("address", "Address too long");
		}
	}

	private boolean checkPhone(String str)
	{
		// TODO: Check various phone formats

		return checkString(str);
	}

	/**
	 * Checks to see if a string contains any characters aside from whitespace
	 * @param str - String to check
	 * @return True if string contains characters aside from whitespace, false if string is either
	 *   missing or just whitespace
	 */
	private boolean checkString(String str)
	{

		if (str == null || str.trim().length() == 0)
		{
			return false;
		}

		return true;
	}

	/**
	 * @return Username of the user to add
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username - Username of the user to add
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return The email of the user to add
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email - The email of the user to add
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return The password of the user to add
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password - The password of the user to add
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return The first Name of the user to add
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName - The firstName of the user to add
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return The last name of the user to add
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName - The last name of the user to add
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return Flag to determine if the user is an administrator
	 */
	public boolean isAdmin()
	{
		return admin;
	}

	/**
	 * @param admin - Flag to determine if the user is an administrator
	 */
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}

	/**
	 * @return The flag to determin if a user was successfully added
	 */
	public boolean isUserAdded()
	{
		return userAdded;
	}

	/**
	 * @return The password to confirm the correct password was entered, not mistyped
	 */
	public String getPasswordCheck()
	{
		return passwordCheck;
	}

	/**
	 * @param passwordCheck - The password to confirm the correct password was entered, not mistyped
	 */
	public void setPasswordCheck(String passwordCheck)
	{
		this.passwordCheck = passwordCheck;
	}

	/**
	 * @return The flag to determine if the user submitted data
	 */
	public boolean isSubmit()
	{
		return submit;
	}

	/**
	 * @param submit - The flag to determine if the user submitted data
	 */
	public void setSubmit(boolean submit)
	{
		this.submit = submit;
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
