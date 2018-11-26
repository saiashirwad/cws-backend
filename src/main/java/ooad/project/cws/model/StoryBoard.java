package ooad.project.cws.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class StoryBoard {
    
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "CREATED_BY", direction = Relationship.OUTGOING)
    private User createdBy;


    @JsonIgnore
    @Relationship(type = "COLLABORATING_ON", direction = Relationship.INCOMING)
    private Set<User> collaborators;

    
    public void addCollaborator(User collaborator) {
        if (this.collaborators == null) {
            this.collaborators = new HashSet<>();
        }
        this.collaborators.add(collaborator);
    }

    @JsonIgnore
    public Set<User> getCollaborators() {
        return this.collaborators;
    }

    public void setCollaborators(Set<User> collaborators) {
        this.collaborators = collaborators;
    }


    private String title;

    private String visibility;

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getVisibility() {
        return this.visibility;
    }

    public StoryBoard(String title, User createdBy) {
        this.title = title;
        this.createdBy = createdBy;
    }

    public void setcreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getcreatedBy() {
        return this.createdBy;
    }

    public String getTitle() {
        return this.title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public StoryBoard() {

    }
    
}