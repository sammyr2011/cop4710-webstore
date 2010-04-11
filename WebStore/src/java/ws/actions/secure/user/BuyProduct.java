package ws.actions.secure.user;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.interceptor.SessionAware;
import ws.utils.Account;
import ws.utils.Database;
import ws.utils.Product;
import ws.utils.Constants;

/**
 *
 * @author Team 10
 */
public class BuyProduct extends ActionSupport implements SessionAware
{
	/**
	 * ID of product to purchase
	 */
	private Integer productId;
	/**
	 * Product to purchase
	 */
	private Product product;
	/**
	 * Flag to determine if data was submitted
	 */
	private boolean submit;
	/**
	 * Shipping address
	 */
	private String shippingAddress;
	/**
	 * Current session map
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
		if(getProduct() == null)
		{
			addActionError("Invalid product");
			return ERROR;
		}

		if(!isSubmit())
		{
			return INPUT;
		}

		if(getProduct().getStock() <= 0)
		{
			addActionError("Product is out of stock");
			return ERROR;
		}

		Account currentUser = (Account)session.get("user");

		if(!Database.getInstance().addTransaction(currentUser.getId(), product.getId(), product.getPrice(), 5.0, shippingAddress))
		{
			addActionError("Failed to create transaction report");
			return ERROR;
		}

		if(!Database.getInstance().editProduct(getProductId(), null, null, null, getProduct().getStock()-1, null, null))
		{
			addActionError("Failed to decrease product stock");
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate()
	{
		if(!isSubmit())
		{
			return;
		}

		if(StringUtils.isEmpty(getShippingAddress()))
		{
			addFieldError("shippingAddress", "Missing shipping address");
		}
		else if(getShippingAddress().length() > Constants.LEN_PURCHASE_SHIPPINGADDRESS)
		{
			addFieldError("shippingAddress", "Address too long");
		}
	}

	/**
	 * ID of product to purchase
	 * @return the productId
	 */
	public Integer getProductId()
	{
		return productId;
	}

	/**
	 * ID of product to purchase
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}

	/**
	 * Product to purchase
	 * @return the product
	 */
	public Product getProduct()
	{
		if(product == null)
		{
			if(getProductId() == null)
			{
				return null;
			}

			product = Database.getInstance().getProduct(getProductId());
		}

		return product;
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
	 * Flag to determine if data was submitted
	 * @return the submit
	 */
	public boolean isSubmit()
	{
		return submit;
	}

	/**
	 * Flag to determine if data was submitted
	 * @param submit the submit to set
	 */
	public void setSubmit(boolean submit)
	{
		this.submit = submit;
	}

	/**
	 * Shipping address
	 * @return the shippingAddress
	 */
	public String getShippingAddress()
	{
		return shippingAddress;
	}

	/**
	 * Shipping address
	 * @param shippingAddress the shippingAddress to set
	 */
	public void setShippingAddress(String shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}
}
