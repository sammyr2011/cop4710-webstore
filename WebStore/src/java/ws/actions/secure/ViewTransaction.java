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
	private Map session;
	private Integer transactionId;
	private Transaction transaction;
	private Product product;
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

	@Override
	public void validate()
	{
		super.validate();
	}

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
	public void setTransactionId(Integer transactionId)
	{
		this.transactionId = transactionId;
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
