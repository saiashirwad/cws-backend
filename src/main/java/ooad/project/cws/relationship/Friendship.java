package ooad.project.cws.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import ooad.project.cws.model.User;

@RelationshipEntity(type="FRIENDS_WITH")
public class Friendship {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return this.id;
    }

    @StartNode private User user1;
    @EndNode private User user2;

    public User getUser1() {
        return this.user1;
    }

    public User getUser2() {
        return this.user2;
    }

    public Friendship(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

}