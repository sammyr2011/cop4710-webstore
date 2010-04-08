package ws.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Removes authroized user from the current session
 * @author Team 10
 */
public class Logout extends ActionSupport implements SessionAware
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
			session.remove("user");
		}

		((SessionMap) session).invalidate();
		session = ActionContext.getContext().getSession();

		return SUCCESS;
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
