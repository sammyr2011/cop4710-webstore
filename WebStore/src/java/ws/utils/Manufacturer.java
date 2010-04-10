package ws.utils;

/**
 *
 * @author Team 10
 */
public class Manufacturer
{
	/**
	 * Unique manufacturer ID
	 */
	private int id;
	/**
	 * Name of the manufacturer
	 */
	private String name;
	/**
	 * Manufacturer's website
	 */
	private String website;

	/**
	 *
	 * @param id - Unique manufacturer ID
	 * @param name - Name of the manufacturer
	 * @param website - Manufacturer's website
	 */
	public Manufacturer(int id, String name, String website)
	{
		this.id = id;
		this.name = name;
		this.website = website;
	}

	/**
	 * Unique manufacturer ID
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Name of the manufacturer
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Name of the manufacturer
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Manufacturer's website
	 * @return the website
	 */
	public String getWebsite()
	{
		return website;
	}

	/**
	 * Manufacturer's website
	 * @param website the website to set
	 */
	public void setWebsite(String website)
	{
		this.website = website;
	}
}
