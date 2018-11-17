package ooad.project.cws.serializable;

import ooad.project.cws.model.User;

// Just like SerializableUser, this doesn't actually implement Serialize.
public class SerializableNode {
    private Long id;
    private String text;
    private SerializableUser creator;

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getText()
	{
		return this.text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public SerializableUser getCreator()
	{
		return this.creator;
	}

	public void setCreator(SerializableUser creator)
	{
		this.creator = creator;
	}


    // Didn't want to bring in the NodeRepository to find me
    // the creator name. Much better to use the setCreator method wherever 
    // this object is being instantiated, methinks.
    public SerializableNode(Long id, String text) {
        this.id = id;
        this.text = text;
    }
}