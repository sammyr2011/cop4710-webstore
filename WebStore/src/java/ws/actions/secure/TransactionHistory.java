package ws.actions.secure;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import java.util.Vector;
import org.apache.struts2.interceptor.SessionAware;
import ws.utils.Account;
import ws.utils.Database;
import ws.utils.Transaction;

/**
 *
 * @author Team 10
 */
public class TransactionHistory extends ActionSupport implements SessionAware
{
	/**
	 * Current session map
	 */
	private Map session;
	/**
	 * List of valid transactions
	 */
	private Vector<Transaction> transactions;

	/**
	 * Retrieves a list of transactions. For users, only their own transactions are retrieved. Admin
	 *   account will get all transactions
	 *
	 * @return Returns a list of current user's transactions, or every transaction if user is an admin.
	 */
	public Vector<Transaction> getTransactions()
	{
		if (transactions == null)
		{
			Account currentUser = (Account) session.get("user");

			if (currentUser.isAdmin())
			{
				transactions = Database.getInstance().getTransactions();
			}
			else
			{
				transactions = Database.getInstance().getTransactions(currentUser.getId());
			}
		}

		return transactions;
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
