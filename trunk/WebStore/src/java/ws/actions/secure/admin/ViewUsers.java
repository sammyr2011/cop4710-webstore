package ws.actions.secure.admin;

import ws.utils.Account;
import ws.utils.Database;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Vector;

/**
 * Lists all accounts
 * @author Team 10
 */
public class ViewUsers extends ActionSupport
{
	/**
	 * Obtains a list of all account
	 * @return A list of all account
	 */
	public Vector<Account> getUsers()
	{
		return Database.getInstance().getUsers();
	}
}
