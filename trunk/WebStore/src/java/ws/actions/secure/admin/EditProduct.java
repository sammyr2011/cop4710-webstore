package ws.actions.secure.admin;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.xwork.StringUtils;
import ws.utils.Database;
import ws.utils.Product;
import ws.utils.Constants;

/**
 *
 * @author Team 10
 */
public class EditProduct extends ActionSupport
{
	/**
	 * Name of the product
	 */
	private String name;
	/**
	 * ID of the product's manufacturer
	 */
	private Integer manufacturerId;
	/**
	 * Price of the product
	 */
	private Double price;
	/**
	 * Amount of the product in stock
	 */
	private Integer stock;
	/**
	 * Filename of the product image
	 */
	private String image;
	/**
	 * Description of the product
	 */
	private String description;
	/**
	 * Flag to determine if data was submitted
	 */
	private boolean submit;
	/**
	 * Flag to determine if product is to be deleted
	 */
	private boolean delete;
	/**
	 * ID of the product to edit if we are editing a product
	 */
	private Integer productId;
	/**
	 * Original product to be edited
	 */
	private Product product;

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
			addActionMessage("Cannot edit specified product");
			return ERROR;
		}

		if(isDelete())
		{
			Database.getInstance().DeleteProduct(getProductId());
			product = null;
			return SUCCESS;
		}

		if (!isSubmit())
		{
			return INPUT;
		}

		if (!Database.getInstance().editProduct(productId, name, manufacturerId, price, stock, image, description))
		{
			addActionMessage("Failed to edit product");
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
		if(isDelete())
		{
			return;
		}

		if (StringUtils.isNotEmpty(getName()))
		{
			if (getName().length() > Constants.LEN_PRODUCT_NAME)
			{
				addFieldError("name", "Name too long");
			}
		}

		if (StringUtils.isNotEmpty(getImage()))
		{
			if (getImage().length() > Constants.LEN_PRODUCT_NAME)
			{
				addFieldError("image", "Image path too long");
			}
		}

		if (StringUtils.isEmpty(getDescription()))
		{
			addFieldError("description", "Missing description");
		}

		if (getManufacturerId() == null)
		{
			addFieldError("manufacturerId", "Missing manufacturer");
		}
		else if (Database.getInstance().getManufacturer(getManufacturerId()) == null)
		{
			addFieldError("manufacturerId", "No such manufacturer id");
		}

		if (getStock() == null)
		{
			addFieldError("stock", "Missing stock count");
		}

		if (getPrice() == null)
		{
			addFieldError("price", "Missing price");
		}

		super.validate();
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
	public Integer getManufacturerId()
	{
		return manufacturerId;
	}

	/**
	 * ID of the product's manufacturer
	 * @param manufacturerId the manufacturerId to set
	 */
	public void setManufacturerId(String manufacturerId)
	{
		try
		{
			this.manufacturerId = Integer.parseInt(manufacturerId);
		}
		catch (NumberFormatException numberFormatException)
		{
			this.manufacturerId = null;
		}
	}

	/**
	 * Price of the product
	 * @return the price
	 */
	public Double getPrice()
	{
		return price;
	}

	/**
	 * Price of the product
	 * @param price the price to set
	 */
	public void setPrice(String price)
	{
		try
		{
			this.price = Double.parseDouble(price);
		}
		catch (NumberFormatException numberFormatException)
		{
			this.price = null;
		}
	}

	/**
	 * Amount of the product in stock
	 * @return the stock
	 */
	public Integer getStock()
	{
		return stock;
	}

	/**
	 * Amount of the product in stock
	 * @param stock the stock to set
	 */
	public void setStock(String stock)
	{
		try
		{
			this.stock = Integer.parseInt(stock);
		}
		catch (NumberFormatException numberFormatException)
		{
			this.stock = null;
		}
	}

	/**
	 * Filename of the product image
	 * @return the image
	 */
	public String getImage()
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
	public void setSubmit(String submit)
	{
		this.submit = Boolean.parseBoolean(submit);
	}

	/**
	 * ID of the product to edit if we are editing a product
	 * @return the editProductId
	 */
	public Integer getProductId()
	{
		return productId;
	}

	/**
	 * ID of the product to edit if we are editing a product
	 * @param productId - ID of the product to edit
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
	 * Original product to be edited
	 * @return the productToEdit
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
	 * Flag to determine if product is to be deleted
	 * @return the delete
	 */
	public boolean isDelete()
	{
		return delete;
	}

	/**
	 * Flag to determine if product is to be deleted
	 * @param delete the delete to set
	 */
	public void setDelete(String delete)
	{
		this.delete = Boolean.parseBoolean(delete);
	}
}
