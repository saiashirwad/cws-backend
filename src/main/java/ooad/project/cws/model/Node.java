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

import ooad.project.cws.relationship.Parenthood;
import ooad.project.cws.serializable.SerializableNode;

@NodeEntity
public class Node {

    @Id
    @GeneratedValue
    private Long id;

    private String text;
    private Long parentId;

    @JsonIgnore
    @Relationship(type = "CREATED_BY", direction = Relationship.OUTGOING)
    private User creator;

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

    public void setCreator(User creator) {
        this.creator = creator;
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

    public SerializableNode getSerializableNode() {
        return new SerializableNode(this.id, this.text);
    }

    

