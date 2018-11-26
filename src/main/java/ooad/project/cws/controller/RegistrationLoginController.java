package ooad.project.cws.controller;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ooad.project.cws.model.User;
import ooad.project.cws.repository.UserRepository;
import ooad.project.cws.service.UserService;
import ooad.project.cws.types.NameType;
import ooad.project.cws.types.UserRegisterType;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api/auth")
public class RegistrationLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // public RegistrationLoginController(UserService userService, 
    // BCryptPasswordEncoder bCryptPasswordEncoder, 
    // UserRepository userRepository) {
    //     this.userRepository = userRepository;
    //     this.userService = userService;
    //     this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    // }

    // @PostMapping(value="/register")
    // public UserRegisterType register(@RequestBody UserRegisterType user) {
    //     user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    //     User newUser = new User(user.getName(), user.getPassword(), user.getEmail());
    //     userRepository.save(newUser);
    //     return user;
    // }

    @PostMapping(value="/check")
    public Boolean checkName(@RequestBody NameType name) {
        // Boolean response = userRepository.checkExists(name, "name");
        Boolean response = userRepository.checkExists(name.getName());
        return response;
    }

    @PostMapping(value="/register")
    public UserRegisterType register(@RequestBody UserRegisterType user) {

        System.out.println(user.getEmail() + user.getName());

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User newUser = new User(user.getName(), user.getPassword(), user.getEmail());
        userRepository.save(newUser);
        return user;
    }

    // @PostMapping(value="/login")
    // public void login(HttpServletRequest request) {
        
    // }

    



}
