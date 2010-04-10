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
	private Product product;
	private Integer productId;

	/**
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
	 * @return the productId
	 */
	public Integer getProductId()
	{
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}

	public String getManufacturerName()
	{
		System.out.println("Get name = " + getProduct());
		if (getProduct() == null)
		{
			return "";
		}

		return Database.getInstance().getManufacturerName(getProduct().getManufacturerId());
	}
}
