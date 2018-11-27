package ooad.project.cws.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import ooad.project.cws.model.Node;
import ooad.project.cws.model.User;

@Repository
public interface NodeRepository extends Neo4jRepository<Node, Long> {
    
    @Query("MATCH (n:Node) WHERE id(n)={0} RETURN n")
    Node fetchNodeById(Long id);

    @Query("MATCH (n:Node) WHERE n.nid={0} return n")
    Node getNodeByNid(String nid);

    @Query("MATCH (n:Node) WHERE n.creatorName={0} return n")
    Iterable<Node> getNodesByUser(String name);

    @Query("MATCH (n:Node) where n.text CONTAINS {0} RETURN n")
    Iterable<Node> searchNodes(String searchString);

    @Query("match (n:Node)<-[l:LIKED_BY]-(u:User) where id(n)={0} and u.name={1} return count(l) > 0 as l")
    Boolean isLikedBy(Long id, String name);

    @Query("match (n:Node), (u:User) where id(n)={0} and u.name={1} create (u)-[:LIKED_BY]->(n)")
    void like(Long id, String name);

    @Query("match (n:Node)<-[l:LIKED_BY]-(u:User) where id(n)={0} and u.name={1} delete(l)")
    void unlike(Long id, String name);

    @Query("match (n:Node)<-[:LIKED_BY]-(u:User) where id(n)={0} return count(u) as likes")
    Long likeCount(Long id);


    // @Query(
    //     ""
    // )

}