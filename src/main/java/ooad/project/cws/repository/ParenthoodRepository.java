package ooad.project.cws.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import ooad.project.cws.relationship.Parenthood;

@Repository
public interface ParenthoodRepository extends Neo4jRepository<Parenthood, Long> {
    
}