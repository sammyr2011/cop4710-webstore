package ws.utils;

/**
 *
 * @author Team 10
 */
public class Product
{
	/**
	 * Unique product ID
	 */
	private int id;
	/**
	 * Name of the product
	 */
	private String name;
	/**
	 * ID of the product's manufacturer
	 */
	private int manufacturerId;
	/**
	 * Price of the product
	 */
	private double price;
	/**
	 * Amount of the product in stock
	 */
	private int stock;
	/**
	 * Filename of the product image
	 */
	private String image;
	/**
	 * Description of the product
	 */
	private String description;

	/**
	 *
	 * @param id - Unique product ID
	 * @param name - Name of the product
	 * @param manufacturerId - ID of the product's manufacturer
	 * @param price - Price of the product
	 * @param stock - Amount of the product in stock
	 * @param image - Filename of the product image
	 * @param description - Description of the product
	 */
	public Product(int id, String name, int manufacturerId, double price, int stock, String image, String description)
	{
		this.id = id;
		this.name = name;
		this.manufacturerId = manufacturerId;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.description = description;
	}

	/**
	 * Unique product ID
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Name of the product
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Name of the product
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * ID of the product's manufacturer
	 * @return the manufacturerId
	 */
	public int getManufacturerId()
	{
		return manufacturerId;
	}

	/**
	 * ID of the product's manufacturer
	 * @param manufacturerId the manufacturerId to set
	 */
	public void setManufacturerId(int manufacturerId)
	{
		this.manufacturerId = manufacturerId;
	}

	/**
	 * Price of the product
	 * @return the price
	 */
	public double getPrice()
	{
		return price;
	}

	/**
	 * Price of the product
	 * @param price the price to set
	 */
	public void setPrice(double price)
	{
		this.price = price;
	}

	/**
	 * Amount of the product in stock
	 * @return the stock
	 */
	public int getStock()
	{
		return stock;
	}

	/**
	 * Amount of the product in stock
	 * @param stock the stock to set
	 */
	public void setStock(int stock)
	{
		this.stock = stock;
	}

	/**
	 * Filename of the product image with default path included
	 * @return the image
	 */
	public String getImage()
	{
		return Constants.PRODUCT_IMAGE_PATH + image;
	}

	/**
	 * Filename of the product image without extra path information
	 * @return the image
	 */
	public String getRawImage()
	{
		return image;
	}

	/**
	 * Filename of the product image
	 * @param image the image to set
	 */
	public void setImage(String image)
	{
		this.image = image;
	}

	/**
	 * Description of the product
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Description of the product
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
}
