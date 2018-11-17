package ooad.project.cws.types;

public class CreateNodeType {
    private String text;
    private String creator;
    private Long parentId;

	public String getText()
	{
		return this.text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getCreator()
	{
		return this.creator;
	}

	public void setCreator(String creator)
	{
		this.creator = creator;
	}

	public Long getParentId()
	{
		return this.parentId;
	}

	public void setParentid(Long parentId)
	{
		this.parentId = parentId;
	}

}