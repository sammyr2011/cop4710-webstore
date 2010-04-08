package ws.actions;

import ws.utils.Database;
import ws.utils.Account;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import java.util.Vector;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 * The login action authenticates the user and adds them to the session
 * @author Team 10
 */
public class Login extends ActionSupport implements SessionAware
{
	/**
	 * Current session map, set by struts
	 */
	private Map session;
	/**
	 * Name of user to login as
	 */
	private String userName;
	/**
	 * Password of user to login as
	 */
	private String userPassword;

	/**
	 * @return 
	 * @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception
	{
		if (session.containsKey("user"))
		{
			return "alreadyAuthenticated";
		}

		Database db = Database.getInstance();

		Account user = db.getUser(getUserName(), getUserPassword());

		if (user == null)
		{
			addFieldError("error", "Invalid username or password");
			return INPUT;
		}

		((SessionMap) session).invalidate();

		session = ActionContext.getContext().getSession();
		session.put("user", user);

		return SUCCESS;
	}

	/**
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate()
	{
		if (getUserName() == null || getUserPassword() == null)
		{
			addFieldError("error", "");
			return;
		}

		if (getUserName().length() == 0)
		{
			addFieldError("userName", "Missing Name");
		}

		if (getUserPassword().length() == 0)
		{
			addFieldError("userPassword", "Missing password");
		}
	}

	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword()
	{
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	/**
	 * Obtains access to the session map, set automatically
	 * @param session - Current session map
	 */
	public void setSession(Map session)
	{
		this.session = session;
	}
}
