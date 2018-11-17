package ooad.project.cws.model;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String bio;

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getBio()
	{
		return this.bio;
	}

	public void setBio(String bio)
	{
		this.bio = bio;
	}

    private String password;
    private String email;

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

    private Set<String> interests;

    @Relationship(type = "FRIENDS_WITH", direction=Relationship.UNDIRECTED)
	private Set<User> friends;
	
	@Relationship(type = "SENT_FRIEND_REQUEST_TO", direction=Relationship.OUTGOING)
	private Set<User> requestedFriends;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.email = email;
        this.password = password;
	}
	
	public void addFriend(User user) {
		if (this.friends == null) {
			this.friends = new HashSet<>();
		}
		this.friends.add(user);
	}

	public Set<User> getFriends() {
		return this.friends;
	}

    public User() {

	}
	 

}