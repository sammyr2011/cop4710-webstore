package ws.utils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

/**
 * An interceptor for making sure the user making the request is currently authorized to do so.
 *   Used for making sure only authorized users have access to actions in the namespace the
 *   interceptor is defined in.
 * @author Team 10
 */
public class AuthenticationInterceptor implements Interceptor
{
	/**
	 * Unused
	 */
	public void destroy()
	{
	}

	/**
	 * Unused
	 */
	public void init()
	{
	}

	/**
	 * Verifies that the user is authenticated before processing the request
	 * @param actionInvocation
	 * @return Action.LOGIN when user making the request is not authenticated
	 * @throws Exception
	 */
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{
		Map session = actionInvocation.getInvocationContext().getSession();

		// Only logged in users have a "user" object in the session
		if (!session.containsKey("user"))
		{
			// User not logged in, give them the login page
			return Action.LOGIN;
		}

		return actionInvocation.invoke();
	}
}
