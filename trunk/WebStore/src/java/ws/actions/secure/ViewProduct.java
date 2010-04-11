package ws.actions.secure;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Vector;
import ws.utils.Database;
import ws.utils.Product;
import ws.utils.Review;

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
	 * List of product's reviews
	 */
	private Vector<Review> reviews;
	/**
	 * Average review rating
	 */
	private Double averageRating;

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
	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}

	/**
	 * Returns the name of the product's manufacturer
	 * @return Manufacturer's name
	 */
	public String getManufacturerName()
	{
		if (getProduct() == null)
		{
			return "";
		}

		return Database.getInstance().getManufacturerName(getProduct().getManufacturerId());
	}

	/**
	 * List of product's reviews
	 * @return the reviews
	 */
	public Vector<Review> getReviews()
	{
		if (reviews == null)
		{
			if (getProductId() == null)
			{
				return null;
			}

			reviews = Database.getInstance().getReviews(getProductId());
		}

		return reviews;
	}

	/**
	 * Average review rating
	 * @return the averageRating
	 */
	public Double getAverageRating()
	{
		if (averageRating == null)
		{
			double average = 0.0;

			if (getReviews() == null)
			{
				return null;
			}

			if (reviews.size() == 0)
			{
				return null;
			}

			for (Review r : reviews)
			{
				average += r.getRating();
			}

			averageRating = average / reviews.size();
		}

		return averageRating;
	}
}
