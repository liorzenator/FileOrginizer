
public class Extension 
{
	private String extension;
	private String definition;
	
	public Extension (String extension, String definition)
	{
		this.extension = extension;
		this.definition = definition;
	}

	public String getExtension() 
	{
		return extension;
	}

	public void setExtension(String extension) 
	{
		this.extension = extension;
	}

	public String getDefinition() 
	{
		return definition;
	}

	public void setDefinition(String definition) 
	{
		this.definition = definition;
	}
}
