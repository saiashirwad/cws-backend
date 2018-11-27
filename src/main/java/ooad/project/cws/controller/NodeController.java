package ooad.project.cws.controller;

import java.util.Optional;

import org.neo4j.ogm.exception.core.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ooad.project.cws.model.Node;
import ooad.project.cws.model.User;
import ooad.project.cws.relationship.Parenthood;
import ooad.project.cws.repository.NodeRepository;
import ooad.project.cws.repository.ParenthoodRepository;
import ooad.project.cws.repository.UserRepository;
import ooad.project.cws.serializable.SerializableNode;
import ooad.project.cws.types.CreateNodeType;
import ooad.project.cws.types.LikeStatusType;
import ooad.project.cws.types.LikeType;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/api/node")
public class NodeController {

    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ParenthoodRepository parenthoodRepository;

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<Node> getAll() {
        return nodeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public SerializableNode getNode(@PathVariable Long id) {
        Optional<Node> node = nodeRepository.findById(id);
        if (node.isPresent()) {
            return node.get().getSerializableNode();
        }
        // Check to make sure all hell does not break loose!!!
        return null;
    }


    // // Create all nodes but for the parent node of a Storyboard. 
    // // Could have created a general approach but what the hell
    // @RequestMapping(value = "/new", method=RequestMethod.POST)
    // public Boolean createNode(@RequestBody CreateNodeType node) {

    //     User creator = userRepository.findByName(node.getCreator());

    //     Node newNode = nodeRepository.save(new Node(node.getText(), creator));

    //     Node parent = nodeRepository.fetchNodeById(node.getParentId());
        
    //     Parenthood parenthood = new Parenthood(parent, newNode);
    //     parenthoodRepository.save(parenthood);
        
    //     return true;
    // }

    // @RequestMapping(value ="/test", method=RequestMethod.POST)
    // public Node create(@RequestBody CreateNodeType node) {
    //     User creator = userRepository.findByName(node.getCreator());

    //     Node newNode = nodeRepository.save(new Node(node.getText(), creator));

        


    // }

    @RequestMapping(value="/new", method=RequestMethod.POST) 
    public Long createNode(@RequestBody CreateNodeType node) {
        String creatorName = node.getCreator();
        String text = node.getText();
        Long parentId = node.getParentId();

        User creator = userRepository.findByName(creatorName);
        Optional<Node> parent = nodeRepository.findById(parentId);

        Node newNode = new Node(text);
        
        if (parent.isPresent() && creator != null) {
            //  Has parent and creator. 
            newNode.setParent(parent.get());
            newNode.setParentId(parentId);
            newNode.setCreator(creator);
            newNode.setCreatorname(creatorName);
        }

        else if (creator != null) {
            //  Orphan Node
            //  Not sure how these are significant in the system but I'll
            //  allow them anyway.
            newNode.setCreator(creator);
            newNode.setParentId(Long.valueOf(-1));
            newNode.setCreatorname(creatorName);
        }

        else {
            //  Eh, just return false
            return null;
        }

        Node nn = nodeRepository.save(newNode);

        return nn.getId();
    }



    @RequestMapping(value="/user/{name}", method=RequestMethod.GET)
    public Iterable<Node> getNodesByUser(@PathVariable("name") String name) {
        try {
            return nodeRepository.getNodesByUser(name);
        }
        catch (Exception e) {
            return null;
        }
    }



    @RequestMapping(value="/like", method=RequestMethod.POST)
    public LikeStatusType like(@RequestBody LikeType like, Authentication auth) {

        try {

            Long postId = like.getPostId();
            Optional<Node> n = nodeRepository.findById(postId);
            String name = auth.getName();

            LikeStatusType status = new LikeStatusType();

            if (n.isPresent()) {

                if (! nodeRepository.isLikedBy(postId, name)) {
                    
                    nodeRepository.like(postId, name);

                    status.setStatus("Liked");
                }
                else {
                    nodeRepository.unlike(postId, name);

                    status.setStatus("Unliked");
                }
            }
            else {
                status.setStatus("Node not present");
            }

            return status;

        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    @RequestMapping(value="/like", method=RequestMethod.GET)
    public Long getLikes(@RequestBody LikeType post) {

        return nodeRepository.likeCount(post.getPostId());

    }


    

    
}
