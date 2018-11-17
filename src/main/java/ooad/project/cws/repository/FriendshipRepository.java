package ooad.project.cws.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import ooad.project.cws.relationship.Friendship;

@Repository
public interface FriendshipRepository extends Neo4jRepository<Friendship, Long> {
    
}
