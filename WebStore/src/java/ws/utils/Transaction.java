package ws.utils;

import java.sql.Timestamp;

/**
 * 
 * @author Team 10
 */
public class Transaction
{
	/**
	 * Unique transaction ID
	 */
	private int id;
	/**
	 * Unique ID of user making the purchase
	 */
	private int userId;
	/**
	 * Unique ID of product purchased
	 */
	private int productId;
	/**
	 * Price of product at time of transaction
	 */
	private double price;
	/**
	 * Shipping price
	 */
	private double shippingPrice;
	/**
	 * Shipping address
	 */
	private String shippingAddress;
	/**
	 * Date of transaction
	 */
	private Timestamp date;
	/**
	 * Name of product purchased
	 */
	private String productName;
	/**
	 * Name of user making transaction
	 */
	private String userName;

	/**
	 *
	 * @param id - Unique transaction ID
	 * @param userId - Unique ID of user making the purchase
	 * @param productId - Unique ID of product purchased
	 * @param price - Price of product at time of transaction
	 * @param shippingPrice - Shipping price
	 * @param shippingAddress - Shipping address
	 * @param date - Date of transaction
	 */
	public Transaction(int id, int userId, int productId, double price, double shippingPrice, String shippingAddress, Timestamp date)
	{
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.price = price;
		this.shippingPrice = shippingPrice;
		this.shippingAddress = shippingAddress;
		this.date = date;
	}

	/**
	 * Unique transaction ID
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Unique ID of user making the purchase
	 * @return the userId
	 */
	public int getUserId()
	{
		return userId;
	}

	/**
	 * Unique ID of user making the purchase
	 * @param userId the userId to set
	 */
	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	/**
	 * Unique ID of product purchased
	 * @return the productId
	 */
	public int getProductId()
	{
		return productId;
	}

	/**
	 * Unique ID of product purchased
	 * @param productId the productId to set
	 */
	public void setProductId(int productId)
	{
		this.productId = productId;
	}

	/**
	 * Price of product at time of transaction
	 * @return the price
	 */
	public double getPrice()
	{
		return price;
	}

	/**
	 * Price of product at time of transaction
	 * @param price the price to set
	 */
	public void setPrice(double price)
	{
		this.price = price;
	}

	/**
	 * Shipping price
	 * @return the shippingPrice
	 */
	public double getShippingPrice()
	{
		return shippingPrice;
	}

	/**
	 * Shipping price
	 * @param shippingPrice the shippingPrice to set
	 */
	public void setShippingPrice(double shippingPrice)
	{
		this.shippingPrice = shippingPrice;
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

	/**
	 * Name of the product transaction is for
	 * @return the product name
	 */
	public String getProductName()
	{
		if(productName == null)
		{
			productName = Database.getInstance().getProductName(getProductId());
		}
		
		return productName;
	}

	/**
	 * Name of the user transaction is for
	 * @return the user name
	 */
	public String getUserName()
	{
		if(userName == null)
		{
			userName = Database.getInstance().getUserName(getUserId());
		}

		return userName;
	}

	/**
	 * Date of transaction
	 * @return the date
	 */
	public Timestamp getDate()
	{
		return date;
	}
}
