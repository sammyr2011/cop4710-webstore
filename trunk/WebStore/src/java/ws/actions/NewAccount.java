package ws.actions;

import ws.utils.Database;
import ws.utils.Constants;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.xwork.StringUtils;
import ws.utils.Utils;

/**
 *
 * @author Team 10
 */
public class NewAccount extends ActionSupport
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
	 * Phone number of user to add (Optional)
	 */
	private String phone;
	/**
	 * Address of user to add
	 */
	private String address;
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

		if (!Database.getInstance().addUser(username, email, password, firstName, lastName, phone, address, false))
		{
			addActionError("Failed to add user to database");
			return ERROR;
		}

		if (!Utils.forceUserLogin(username, password))
		{
			addActionError("Unable to login as new user");
			return ERROR;
		}

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

		if (StringUtils.isEmpty(getUsername()))
		{
			addFieldError("username", "Missing username");
		}
		else
		{
			if (getUsername().length() > Constants.LEN_USER_USERNAME)
			{
				addFieldError("username", "Username too long");
			}
			if (Database.getInstance().checkForExistingAccount(getUsername()))
			{
				addFieldError("username", "Username already exists");
			}
		}

		if (StringUtils.isEmpty(getEmail()))
		{
			addFieldError("email", "Missing email");
		}
		else if (getEmail().length() > Constants.LEN_USER_EMAIL)
		{
			addFieldError("email", "Email too long");
		}

		if (StringUtils.isEmpty(getPassword()))
		{
			addFieldError("password", "Missing password");
		}

		if (StringUtils.isEmpty(getPasswordCheck()))
		{
			addFieldError("passwordCheck", "Missing password");
		}

		if (!StringUtils.equals(getPassword(), getPasswordCheck()))
		{
			addFieldError("passwordCheck", "Passwords do not match");
		}

		if (StringUtils.isEmpty(getFirstName()))
		{
			addFieldError("firstName", "Missing first name");
		}
		else if (getFirstName().length() > Constants.LEN_USER_FIRSTNAME)
		{
			addFieldError("firstName", "First name too long");
		}

		if (StringUtils.isEmpty(getLastName()))
		{
			addFieldError("lastName", "Missing last name");
		}
		else if (getLastName().length() > Constants.LEN_USER_LASTNAME)
		{
			addFieldError("lastName", "Last name too long");
		}

		if (StringUtils.isEmpty(getPhone()))
		{
			phone = "";
		}
		else if (getPhone().length() > Constants.LEN_USER_PHONE)
		{
			addFieldError("phone", "Phone number too long");
		}

		if (StringUtils.isEmpty(getAddress()))
		{
			addFieldError("address", "Missing address");
		}
		else if (getAddress().length() > Constants.LEN_USER_ADDRESS)
		{
			addFieldError("address", "Address too long");
		}
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
