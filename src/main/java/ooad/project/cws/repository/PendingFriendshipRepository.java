package ooad.project.cws.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import ooad.project.cws.relationship.PendingFriendship;

@Repository
public interface PendingFriendshipRepository extends Neo4jRepository<PendingFriendship, Long> {
    @Query("MATCH (sender:User)-[r:SENT_FRIEND_REQUEST_TO]->(receiver:User)" +
           "WHERE sender.name={0} and receiver.name={1}" +
           "RETURN COUNT(r) > 0 as r"
    )
    Boolean checkExists(String senderName, String receiverName);

    @Query("MATCH (sender:User)-[r:SENT_FRIEND_REQUEST_TO]->(receiver:User)" +
           "WHERE sender.name={0} and receiver.name={1}" +
           "RETURN r"
    )
    PendingFriendship getPendingFriendship(String senderName, String receiverName);
}