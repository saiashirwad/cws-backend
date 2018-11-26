package ooad.project.cws.controller;

import java.awt.List;

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

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @RequestMapping(value="/", method=RequestMethod.POST)
    public List search(@RequestBody String searchString, Authentication auth) {
        
        System.out.println(auth.getName());

        try {

        }
        catch (Exception e) {
            return null;
        }
    }

    // public Iterable searchStoryBoards(String searchString) {

    // }

    public Iterable<User> searchUsers(String searchString) {
        return userRepository.searchUsers(searchString);
    }

    public Iterable<Node> searchNodes(String searchString) {
        return nodeRepository.searchNodes(searchString);
    }



}