package ws.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * The home action is the default action users get when viewing the root directory.
 *  It will forward logged in users to the secure user home page and forward everyone
 *  else to the login page
 * @author Team 10
 */
public class Home extends ActionSupport implements SessionAware
{
	/**
	 * Current session map, set by struts
	 */
	private Map session;

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

		return LOGIN;
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
