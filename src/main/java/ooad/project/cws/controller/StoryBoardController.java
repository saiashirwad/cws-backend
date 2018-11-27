package ooad.project.cws.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ooad.project.cws.model.Node;
import ooad.project.cws.model.StoryBoard;
import ooad.project.cws.model.User;
import ooad.project.cws.repository.NodeRepository;
import ooad.project.cws.repository.StoryBoardRepository;
import ooad.project.cws.repository.UserRepository;
import ooad.project.cws.types.CreateStoryBoardType;

@RestController
@RequestMapping(value = "/api/storyboard")
public class StoryBoardController {

    @Autowired
    StoryBoardRepository storyBoardRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NodeRepository nodeRepository;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Map<String, Object> viewStoryBoard(@PathVariable Long id) {

        Optional<StoryBoard> sBoard = storyBoardRepository.findById(id);
        StoryBoard storyBoard = new StoryBoard();
        if (sBoard.isPresent()) {
            storyBoard = sBoard.get();

            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("createdBy", storyBoard.getcreatedBy());
            map.put("title", storyBoard.getTitle());
            map.put("id", storyBoard.getId());
            map.put("storyLines", storyBoardRepository.getNodes(id));

            // Iterable<User> collaborators = storyBoardRepository.getCollaborators(id);
            map.put("collaborators", storyBoardRepository.getCollaborators(id));

            map.put("visibility", storyBoard.getVisibility());

            return map;
        }
        
        else {
            return null;
        }

    }

    @RequestMapping(value="/new", method=RequestMethod.POST)
    public Long createStoryBoard(@RequestBody CreateStoryBoardType storyBoardReq, Authentication auth) {

        String title = storyBoardReq.getTitle();
        String text = storyBoardReq.getText();
        String createdBy = auth.getName();
        String visibility = storyBoardReq.getVisibility();
        User user = userRepository.findByName(createdBy);

        StoryBoard storyBoard = new StoryBoard(title, user);
        storyBoard.setVisibility(visibility);
        StoryBoard sb = storyBoardRepository.save(storyBoard);

        // Should have reused Node creation logic; sigh
        Node newNode = new Node(text);
        newNode.setParentId(Long.valueOf(-1));
        newNode.setCreator(user);
        newNode.setCreatorname(createdBy);
        newNode.setStoryBoard(sb);

        nodeRepository.save(newNode);

        return sb.getId();
    }
}