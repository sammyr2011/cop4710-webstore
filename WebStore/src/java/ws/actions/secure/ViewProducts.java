package ws.actions.secure;

import ws.utils.Database;
import ws.utils.Product;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Vector;

/**
 *
 * @author Team 10
 */
public class ViewProducts extends ActionSupport
{
	private Vector<Product> products;

	@Override
	public String execute() throws Exception
	{
		return super.execute();
	}

	/**
	 * @return the products
	 */
	public Vector<Product> getProducts()
	{
		if (products == null)
		{
			products = Database.getInstance().getProducts();
		}

		return products;
	}
}
