package ooad.project.cws.serializable;


// Doesn't actually implement Serializable
// Just something I have because I can't figure out how to choose
// which of the member attributes of entities I need to serialize
public class SerializableUser {
    private Long id;
    private String name;

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
    }
    
    public SerializableUser(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}