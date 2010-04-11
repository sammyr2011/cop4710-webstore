package ws.actions.secure.admin;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.xwork.StringUtils;
import ws.utils.Database;
import ws.utils.Constants;

/**
 *
 * @author Team 10
 */
public class AddManufacturer extends ActionSupport
{
	/**
	 * Name of the manufacturer
	 */
	private String name;
	/**
	 * Manufacturer's website
	 */
	private String website;
	/**
	 * Flag set when user has submitted data
	 */
	private boolean submit;

	/**
	 * @return
	 * @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception
	{
		if (!isSubmit())
		{
			return INPUT;
		}

		if (!Database.getInstance().addManufacturer(getName(), getWebsite()))
		{
			addActionError("Failed to add manufacturer");
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

		if (StringUtils.isEmpty(getName()))
		{
			addFieldError("name", "Missing name");
		}
		else if (getName().length() > Constants.LEN_MANUFACTURER_COMPANYNAME)
		{
			addFieldError("name", "Name too long");
		}

		if (StringUtils.isEmpty(getWebsite()))
		{
			addFieldError("website", "Missing website");
		}
		else if (getWebsite().length() > Constants.LEN_MANUFACTURER_WEBSITE)
		{
			addFieldError("website", "Website too long");
		}

		super.validate();
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

	/**
	 * Flag set when user has submitted data
	 * @return the submit
	 */
	public boolean isSubmit()
	{
		return submit;
	}

	/**
	 * @param submit the submit to set
	 */
	public void setSubmit(boolean submit)
	{
		this.submit = submit;
	}
}
