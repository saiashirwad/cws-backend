package ooad.project.cws.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.beans.factory.annotation.Autowired;

import ooad.project.cws.relationship.Parenthood;
import ooad.project.cws.repository.NodeRepository;
import ooad.project.cws.serializable.SerializableNode;

@NodeEntity
public class Node {

    @Id
    @GeneratedValue
    private Long id;


	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

    private String text;


	public String getText()
	{
		return this.text;
	}

	public void setText(String text)
	{
		this.text = text;
	}
    private Long parentId;

    @JsonIgnore
    @Relationship(type = "CREATED_BY", direction = Relationship.OUTGOING)
    private User creator;


	public User getCreator()
	{
		return this.creator;
	}

	public void setCreator(User creator)
	{
		this.creator = creator;
	}

    private String creatorName;


	public String getCreatorname()
	{
		return this.creatorName;
	}

	public void setCreatorname(String creatorName)
	{
		this.creatorName = creatorName;
	}
    // Still here to remind myself of my stupididy; ugh
    // @JsonIgnore
    // @Relationship(type = "PARENT_OF", direction = Relationship.INCOMING)
    // // private Node parent;
    // private Parenthood isParentOf = new Parenthood();

    @JsonIgnore
    @Relationship(type= "PARENT_OF", direction = Relationship.INCOMING)
    // private Parenthood parent = new Parenthood();
    // private Optional<Node> parent;
    private Node parent;

    @JsonIgnore
    @Relationship(type = "CONTAINED_IN", direction = Relationship.OUTGOING)
    private StoryBoard storyBoard;

    public void setStoryBoard(StoryBoard storyBoard) {
        this.storyBoard = storyBoard;
    }

    @JsonIgnore
    public StoryBoard getStoryBoard() {
        return this.storyBoard;
    }


    @JsonIgnore
    @Relationship(type = "LIKED_BY", direction = Relationship.OUTGOING)
    private Set<User> likers;


    public Node() {
    }

    public Node(String text, User creator) {
        this.text = text;
        this.creator = creator;
    }

    public Node(String text) {
        this.text = text;
    }


    // public void setParent(Optional<Node> parent) {
    //     this.parent = parent;
    // }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @JsonIgnore
    public SerializableNode getSerializableNode() {

        return new SerializableNode(this.id, this.text, this.creatorName);
    }

}