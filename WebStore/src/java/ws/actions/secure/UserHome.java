package ws.actions.secure;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The default action for the secure namespace. Displays the home page of the user
 * @author Team 10
 */
public class UserHome extends ActionSupport
{
	/**
	 * @return
	 * @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception
	{
		return SUCCESS;
	}
}
