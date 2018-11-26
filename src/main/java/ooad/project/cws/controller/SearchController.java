package ooad.project.cws.controller;

import java.awt.List;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ooad.project.cws.model.Node;
import ooad.project.cws.model.User;
import ooad.project.cws.repository.NodeRepository;
import ooad.project.cws.repository.UserRepository;
import ooad.project.cws.types.SearchType;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @RequestMapping(value="/", method=RequestMethod.POST)
    public Map<String, Object> search(@RequestBody SearchType searchString, Authentication auth) {
        
        try {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            System.out.println(searchString.getSearchString());
            map.put("users", searchUsers(searchString));
            map.put("nodes", searchNodes(searchString));

            return map;
        }
        catch (Exception e) {
            return null;
        }
    }

    // public Iterable searchStoryBoards(String searchString) {

    // }

    @RequestMapping(value="/user", method=RequestMethod.POST)
    public Iterable<User> searchUsers(@RequestBody SearchType searchString) {
        return userRepository.searchUsers(searchString.getSearchString());
    }

    @RequestMapping(value="/node", method=RequestMethod.POST)
    public Iterable<Node> searchNodes(@RequestBody SearchType searchString) {
        return nodeRepository.searchNodes(searchString.getSearchString());
    }



}