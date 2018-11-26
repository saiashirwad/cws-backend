package ooad.project.cws.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import ooad.project.cws.model.Node;

@Repository
public interface NodeRepository extends Neo4jRepository<Node, Long> {
    
    @Query("MATCH (n:Node) WHERE id(n)={0} RETURN n")
    Node fetchNodeById(Long id);

    @Query("MATCH (n:Node) WHERE n.nid={0} return n")
    Node getNodeByNid(String nid);

    @Query("MATCH (n:Node) WHERE n.creatorName={0} return n")
    Iterable<Node> getNodesByUser(String name);

    @Query("MATCH (n:Node) where n.text CONTAINS {0}")
    Iterable<Node> searchNodes(String searchString);

    // @Query(
    //     ""
    // )

}