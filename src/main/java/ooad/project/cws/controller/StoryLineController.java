package ooad.project.cws.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ooad.project.cws.model.Node;
import ooad.project.cws.repository.NodeRepository;
import ooad.project.cws.repository.StoryLineRepository;
import ooad.project.cws.repository.UserRepository;
import ooad.project.cws.serializable.StoryLine;
import ooad.project.cws.service.StoryLineService;

@RestController
@RequestMapping(value="/api/storyline")
public class StoryLineController {

    @Autowired
    StoryLineRepository storyLineRepository;

    @Autowired 
    UserRepository userRepository;

    @Autowired
    NodeRepository nodeRepository;

    @Autowired 
    NodeController nodeController;

    @Autowired
    StoryLineService storyLineService;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public StoryLine getStoryLine(@PathVariable Long id) {
        // Optional<StoryLine> storyLine = storyLineRepository.findById(id);
        // if (storyLine.isPresent()) {
        //     return storyLine.get();
        // }
        // return null;
        
        Node node = nodeRepository.fetchNodeById(id);
        return storyLineService.getStoryLine(node);
    }
}