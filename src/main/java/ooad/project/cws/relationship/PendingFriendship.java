package ooad.project.cws.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import ooad.project.cws.model.User;

@RelationshipEntity(type="SENT_FRIEND_REQUEST_TO")
public class PendingFriendship {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode private User sender;
    @EndNode private User receiver;

    public User getSender() {
        return this.sender;
    }

    public User getReceiver() {
        return this.receiver;
    }

    public PendingFriendship(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
}