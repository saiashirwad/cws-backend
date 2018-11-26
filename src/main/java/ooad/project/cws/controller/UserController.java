package ooad.project.cws.controller;

import java.awt.List;
import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ooad.project.cws.model.User;
import ooad.project.cws.relationship.Friendship;
import ooad.project.cws.relationship.PendingFriendship;
import ooad.project.cws.repository.FriendshipRepository;
import ooad.project.cws.repository.PendingFriendshipRepository;
import ooad.project.cws.repository.UserRepository;
import ooad.project.cws.service.UserService;
import ooad.project.cws.types.Dummy;
import ooad.project.cws.types.EditBioType;
import ooad.project.cws.types.FriendRequestType;
import ooad.project.cws.types.NameType;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    // private UserRepository userRepository;
    // private UserService userService;

    // @Autowired
    // public UserController(UserRepository userRepository) {
    //     this.userRepository = userRepository;
    //     this.userService = userService;
    // }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private PendingFriendshipRepository pendingFriendshipRepository;

    @RequestMapping(value="/{name}")
    public User getUser(@PathVariable String name) {
        return userRepository.findByName(name);
    }

    //  Edit user bio
    @RequestMapping(value="/bio", method=RequestMethod.POST)
    public Boolean editBio(@RequestBody EditBioType bio, Authentication principal) {
        // System.out.println(principal.getName());
        System.out.println(bio.getBio());
        try {
            User user = userRepository.findByName(principal.getName());
            user.setBio(bio.getBio());
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(value="/email", method=RequestMethod.POST)
    public Boolean editEmail(@RequestBody EditBioType email, Authentication principal) {
 
        try {
            User user = userRepository.findByName(principal.getName());
            user.setEmail(email.getBio());
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(value="/friends/{name}", method=RequestMethod.GET)
    public Iterable<User> getFriends(Authentication principal, @PathVariable("name") String name) {
        try {
            // User user = userRepository.findByName(principal.getName());
            if (name.toString().length() == 0) {
                return userRepository.getFriends(principal.getName());
            }
            else {
                return userRepository.getFriends(name);
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @RequestMapping(value="/sendemail")
    public String sendEmail() {
        return "Email sent!";
    }

    @RequestMapping(value="/friendrequest/send",method=RequestMethod.POST)
    public Boolean sendFriendRequest(@RequestBody FriendRequestType request) {
        User sender = userRepository.findByName(request.getSendername());
        User receiver = userRepository.findByName(request.getReceivername());
        PendingFriendship newPendingFriendship = new PendingFriendship(sender, receiver);
        pendingFriendshipRepository.save(newPendingFriendship);
        return true;

    }

    @RequestMapping(value="/friendrequest/accept", method=RequestMethod.POST)
    public Boolean acceptFriendRequest(@RequestBody FriendRequestType request) {

        if(pendingFriendshipRepository.checkExists(request.getSendername(), request.getReceivername())) {

            PendingFriendship pendingFriendship = pendingFriendshipRepository.getPendingFriendship(request.getSendername(), request.getReceivername());
            System.out.println("jklksdfjskdlfksjdfksldfkdfadkfjaldkfjaldfkjldkfgjladfkgjladkfgjalkdfgjlakdfjglkdfjlgskjflgksjdflgksdjf");
            pendingFriendshipRepository.delete(pendingFriendship);

            System.out.println("DELETED PENDING REQUEST");
            User sender = userRepository.findByName(request.getSendername());
            User receiver = userRepository.findByName(request.getReceivername());
            Friendship newFriendship = new Friendship(sender, receiver);
            System.out.println("CREATED FRIENDSHIP");
            friendshipRepository.save(newFriendship);
            return true;
        }
        return false;
    }

    @RequestMapping(value="/friendrequest/delete", method=RequestMethod.POST)
    public void deleteFriendRequest(@RequestParam FriendRequestType request) {
        this.userService.removeFriendRequest(request.getSendername(), request.getReceivername());
    }

    @RequestMapping(value="/addfriend", method=RequestMethod.POST)
    public Boolean addFriend(@RequestBody FriendRequestType request) {
        // userRepository.sa
        User sender = userRepository.findByName(request.getSendername());
        User receiver = userRepository.findByName(request.getReceivername());
        Friendship newFriendship = new Friendship(sender, receiver);
        friendshipRepository.save(newFriendship);
        return true;
    }

    @RequestMapping(value="/getfriends", method=RequestMethod.GET)
    public Iterable<User> getFriends(@RequestParam String name) {
        return userRepository.getFriends(name);
    }

//     @RequestMapping(value="/editDetails", method=RequestMethod.POST)
//     public User editDetails(@RequestBody User user) {
//         User curUser = userRepository.findByName(user.getName())
// ;

    // }
    
    

}