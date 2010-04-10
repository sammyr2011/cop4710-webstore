package ws.actions.secure.user;

import com.opensymphony.xwork2.ActionContext;
import ws.utils.Account;
import ws.utils.Database;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import ws.utils.Constants;
import ws.utils.Utils;

/**
 * Allows the editing of current user
 * @author Team 10
 */
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
	 * Phone number of the user to add
	 */
	private String phone;
	/**
	 * Address of the user to add
	 */
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

		Utils.updateUserSession(user);

		user = null;
		user = getUser();

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

		if (StringUtils.isEmpty(getEmail()))
		{
			addFieldError("email", "Missing e-mail");
		}
		else if (getEmail().length() > Constants.LEN_USER_EMAIL)
		{
			addFieldError("email", "Email too long");
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
		if (user == null)
		{
			Map session = ActionContext.getContext().getSession();
			user = (Account) session.get("user");
		}

		return user;
	}
}
