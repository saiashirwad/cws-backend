package ooad.project.cws.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class StoryBoard {
    
    @Id
    @GeneratedValue
    private Long id;

    
}