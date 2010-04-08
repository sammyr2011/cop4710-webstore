package ws.actions.secure.user;

import com.opensymphony.xwork2.ActionContext;
import ws.utils.Account;
import ws.utils.Database;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

public class EditAccount extends ActionSupport
{
	/**
	 * Account of the user to edit
	 */
	private Account user;
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

		if (!Database.getInstance().editUser(getUser().getId(), null, email, firstName, lastName, phone, address, null, password))
		{
			addActionError("Failed to update account information");
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

	// TODO: Move all the empty string checking crap to a utils class somewhere, way too much duplicate code
	private boolean checkPhone(String str)
	{
		// TODO: Check various phone formats

		return checkString(str);
	}

	// TODO: Move all the empty string checking crap to a utils class somewhere, way too much duplicate code
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
	 * @return the user
	 */
	public Account getUser()
	{
		if(user == null)
		{
			Map session = ActionContext.getContext().getSession();
			user = (Account)session.get("user");
		}

		return user;
	}

}
