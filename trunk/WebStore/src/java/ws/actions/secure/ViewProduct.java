package ws.actions.secure;

import com.opensymphony.xwork2.ActionSupport;
import ws.utils.Database;
import ws.utils.Product;

/**
 *
 * @author Team 10
 */
public class ViewProduct extends ActionSupport
{
	/**
	 * Product being viewed
	 */
	private Product product;
	/**
	 * ID of product to view
	 */
	private Integer productId;

	/**
	 * @return
	 * @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception
	{
		if (getProduct() == null)
		{
			addActionError("Invalid product");
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * Product being viewed
	 * @return the product
	 */
	public Product getProduct()
	{
		if (product == null)
		{
			if (getProductId() == null)
			{
				return null;
			}

			product = Database.getInstance().getProduct(getProductId());
		}

		return product;
	}

	/**
	 * ID of product to view
	 * @return the productId
	 */
	public Integer getProductId()
	{
		return productId;
	}

	/**
	 * ID of product to view
	 * @param productId the productId to set
	 */
	public void setProductId(String productId)
	{
		try
		{
			this.productId = Integer.parseInt(productId);
		}
		catch (NumberFormatException numberFormatException)
		{
			this.productId = null;
		}
	}
}
