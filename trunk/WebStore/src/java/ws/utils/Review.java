package ws.utils;

/**
 *
 * @author Team 10
 */
public class Review
{
	/**
	 * Unique review ID
	 */
	private int id;
	/**
	 * Unique ID of user making the review
	 */
	private int userId;
	/**
	 * Unique ID of product reviewed
	 */
	private int productId;
	/**
	 * User rating
	 */
	private int rating;
	/**
	 * Review text
	 */
	private String comment;

	/**
	 *
	 * @param id - Unique review ID
	 * @param userId - Unique ID of user making the review
	 * @param productId - Unique ID of product reviewed
	 * @param rating - User rating
	 * @param comment - Review text
	 */
	public Review(int id, int userId, int productId, int rating, String comment)
	{
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.rating = rating;
		this.comment = comment;
	}

	/**
	 * Unique review ID
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Unique ID of user making the review
	 * @return the userId
	 */
	public int getUserId()
	{
		return userId;
	}

	/**
	 * Unique ID of user making the review
	 * @param userId the userId to set
	 */
	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	/**
	 * Unique ID of product reviewed
	 * @return the productId
	 */
	public int getProductId()
	{
		return productId;
	}

	/**
	 * Unique ID of product reviewed
	 * @param productId the productId to set
	 */
	public void setProductId(int productId)
	{
		this.productId = productId;
	}

	/**
	 * User rating
	 * @return the rating
	 */
	public int getRating()
	{
		return rating;
	}

	/**
	 * User rating
	 * @param rating the rating to set
	 */
	public void setRating(int rating)
	{
		this.rating = rating;
	}

	/**
	 * Review text
	 * @return the comment
	 */
	public String getComment()
	{
		return comment;
	}

	/**
	 * Review text
	 * @param comment the comment to set
	 */
	public void setComment(String comment)
	{
		this.comment = comment;
	}
}
