package ws.actions.secure.user;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.interceptor.SessionAware;
import ws.utils.Account;
import ws.utils.Database;
import ws.utils.Product;

/**
 *
 * @author Team 10
 */
public class ReviewProduct extends ActionSupport implements SessionAware
{
	/**
	 * The text portion of the review
	 */
	private String comment;
	/**
	 * Rating given to product
	 */
	private Integer rating;
	/**
	 * Flag to determine if data was submitted
	 */
	private boolean submit;
	/**
	 * ID of the product being reviewed
	 */
	private Integer productId;
	/**
	 * Product being reviewed
	 */
	private Product product;
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
		Account currentUser = (Account) session.get("user");

		if (getProduct() == null)
		{
			addActionError("Invalid product specified");
			return ERROR;
		}

		if (Database.getInstance().checkForExistingReview(getProductId(), currentUser.getId()))
		{
			addActionError("You have already reviewed this product");
			return ERROR;
		}

		if (!isSubmit())
		{
			return INPUT;
		}

		if (!Database.getInstance().addReview(currentUser.getId(), productId, rating, comment))
		{
			addActionError("Failed to add new review");
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
		if (!isSubmit())
		{
			return;
		}

		if (StringUtils.isEmpty(getComment()))
		{
			addFieldError("comment", "Missing comment");
		}

		if (rating == null)
		{
			addFieldError("comment", "Missing rating");
		}
		else if (rating < 1 || rating > 10)
		{
			addFieldError("comment", "Invalid rating");
		}

		super.validate();
	}

	/**
	 * The text portion of the review
	 * @return the comment
	 */
	public String getComment()
	{
		return comment;
	}

	/**
	 * The text portion of the review
	 * @param comment the comment to set
	 */
	public void setComment(String comment)
	{
		this.comment = comment;
	}

	/**
	 * Rating given to product
	 * @return the rating
	 */
	public Integer getRating()
	{
		return rating;
	}

	/**
	 * Rating given to product
	 * @param rating the rating to set
	 */
	public void setRating(String rating)
	{
		try
		{
			this.rating = Integer.parseInt(rating);
		}
		catch (NumberFormatException numberFormatException)
		{
			this.rating = null;
		}
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
	 * ID of the product being reviewed
	 * @return the productId
	 */
	public Integer getProductId()
	{
		return productId;
	}

	/**
	 * ID of the product being reviewed
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

	/**
	 * Current session map
	 * @param session
	 */
	public void setSession(Map session)
	{
		this.session = session;
	}

	/**
	 * Product being reviewed
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
}
