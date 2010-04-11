package ws.actions.secure;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import ws.utils.Account;
import ws.utils.Database;
import ws.utils.Product;
import ws.utils.Transaction;

/**
 *
 * @author Team 10
 */
public class ViewTransaction extends ActionSupport implements SessionAware
{
	/**
	 * Current session map
	 */
	private Map session;
	/**
	 * ID of the transaction to view
	 */
	private Integer transactionId;
	/**
	 * Transaction to view
	 */
	private Transaction transaction;
	/**
	 * Product of the transaction
	 */
	private Product product;
	/**
	 * User who made the transaction
	 */
	private Account transactionUser;
	
	/**
	 * @return
	 * @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception
	{
		if (getTransactionId() == null)
		{
			addActionError("Invalid transaction ID specified");
			return ERROR;
		}

		if (getTransaction() == null)
		{
			addActionError("Invalid transaction requested");
			return ERROR;
		}

		Account currentUser = (Account) session.get("user");
		if (!currentUser.isAdmin() && getTransaction().getUserId() != currentUser.getId())
		{
			addActionError("You don't have permission to view the specified transaction");
			return "unauthorized";
		}

		return SUCCESS;
	}

	/**
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate()
	{
		super.validate();
	}

	/**
	 * Obtains access to the session map, set automatically
	 * @param session - Current session map
	 */
	public void setSession(Map session)
	{
		this.session = session;
	}

	/**
	 * @return the transactionId
	 */
	public Integer getTransactionId()
	{
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId)
	{
		try
		{
			this.transactionId = Integer.parseInt(transactionId);
		}
		catch (NumberFormatException numberFormatException)
		{
			this.transactionId = null;
		}
	}

	/**
	 * @return the transaction
	 */
	public Transaction getTransaction()
	{
		if (transaction == null)
		{
			if (transactionId == null)
			{
				return null;
			}

			transaction = Database.getInstance().getTransaction(getTransactionId());
		}

		return transaction;
	}

	/**
	 * @return the product
	 */
	public Product getProduct()
	{
		if (product == null)
		{
			if (getTransaction() == null)
			{
				return null;
			}

			product = Database.getInstance().getProduct(transaction.getProductId());
		}

		return product;
	}

	/**
	 * @return the customer
	 */
	public Account getTransactionUser()
	{
		if (transactionUser == null)
		{
			if (getTransaction() == null)
			{
				return null;
			}

			transactionUser = Database.getInstance().getUser(getTransaction().getUserId());
		}
		return transactionUser;
	}
}
