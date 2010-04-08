package ws.actions.secure.admin;

import ws.utils.Account;
import ws.utils.Database;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Allows the editing of user information
 * @author Team 10
 */
public class EditUser extends ActionSupport
{
	/**
	 * Account of the user to edit
	 */
	private Account user;
	/**
	 * Unique ID of the account to edit
	 */
	private Integer userId;
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

	private Boolean admin;
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
		if (getUserId() == null)
		{
			addActionError("Invalid user ID specified");
			return ERROR;
		}

		if (!isSubmit())
		{
			return INPUT;
		}

		if (!Database.getInstance().editUser(userId, null, email, firstName, lastName, phone, address, admin, password))
		{
			addActionError("Failed to edit user");
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

		if (!checkString(getEmail()))
		{
			addFieldError("email", "Missing e-mail");
		}
		else if(getEmail().length() > 96)
		{
			addFieldError("email", "Email too long");
		}

		if (getPassword() != null && getPassword().length() > 0)
		{
			if(getPassword().length() > 32)
			{
				addFieldError("password", "Password too long");
			}
			if (getPasswordCheck() == null || getPasswordCheck().length() == 0)
			{
				addFieldError("passwordCheck", "Missing password");
			}
			else if (getPassword().compareTo(getPasswordCheck()) != 0)
			{
				addFieldError("passwordCheck", "Passwords do not match");
			}
		}
		else
		{
			if (getPasswordCheck() != null && getPasswordCheck().length() > 0)
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
	 * @return the userId
	 */
	public Integer getUserId()
	{
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId)
	{
		try
		{
			this.userId = Integer.parseInt(userId);
		}
		catch (Exception ex)
		{
			this.userId = null;
		}
	}

	/**
	 * @return the user
	 */
	public Account getUser()
	{
		if(user == null)
		{
			if(userId == null)
			{
				return null;
			}
			
			user = Database.getInstance().getUser(userId);
		}

		return user;
	}

	/**
	 * @return the submit
	 */
	public boolean isSubmit()
	{
		return submit;
	}

	/**
	 * @param submit the submit to set
	 */
	public void setSubmit(boolean submit)
	{
		this.submit = submit;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the passwordCheck
	 */
	public String getPasswordCheck()
	{
		return passwordCheck;
	}

	/**
	 * @param passwordCheck the passwordCheck to set
	 */
	public void setPasswordCheck(String passwordCheck)
	{
		this.passwordCheck = passwordCheck;
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
	 * @return the admin
	 */
	public boolean isAdmin()
	{
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
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
