package ooad.project.cws.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import ooad.project.cws.model.Node;
import ooad.project.cws.model.StoryBoard;
import ooad.project.cws.model.User;

@Repository
public interface StoryBoardRepository extends Neo4jRepository<StoryBoard, Long> {
    @Query(" match (sb:StoryBoard)<-[:CONTAINED_IN]-(sl:Node) where sb.id={0} return sl")
    Iterable<Node> getNodes(Long id);

    @Query("match (sb:StoryBoard)<-[:COLLABORATING_ON]-(u:User) where sb.id={0} return u")
    Iterable<User> getCollaborators(Long id);
}