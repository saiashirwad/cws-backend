package ooad.project.cws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ooad.project.cws.serializable.StoryLine;

@Repository
public interface StoryLineRepository extends CrudRepository<StoryLine, Long> {
    
}