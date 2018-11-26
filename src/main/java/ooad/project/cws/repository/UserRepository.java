package ooad.project.cws.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import ooad.project.cws.model.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
    @Query("MATCH (u:User) WHERE u.name={0} RETURN u")
    User findByName(String name);

    @Query("MATCH (u:User) WHERE u.name={0} return COUNT(u) > 0 as u")
    Boolean checkExists(String name);

    @Query("MATCH (u1:User)-[:FRIENDS_WITH]-(u2:User) WHERE u1.name={0} RETURN u2")
    Iterable<User> getFriends(String name);

    @Query("MATCH (u:User) where u.name CONTAINS {0} RETURN u")
    Iterable<User> searchUsers(String searchString);

}