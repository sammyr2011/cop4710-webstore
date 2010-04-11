package ws.actions.secure.admin;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Vector;
import ws.utils.Database;
import ws.utils.Manufacturer;

/**
 *
 * @author Team 10
 */
public class ViewManufacturers extends ActionSupport
{
	private Vector<Manufacturer> manufacturers;

	/**
	 * @return the manufacturers
	 */
	public Vector<Manufacturer> getManufacturers()
	{
		if (manufacturers == null)
		{
			manufacturers = Database.getInstance().getManufacturers();
		}

		return manufacturers;
	}
}
