package ooad.project.cws.service;

// import javax.mail.internet.MimeMessage;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ooad.project.cws.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // @Autowired JavaMailSender sender;

    // public void sendEmail(String recipient, String textBody, String textTitle, String subject) throws Exception {
    //     MimeMessageHelper helper = new MimeMessageHelper(message);

    //     helper.setTo(recipient);
    //     helper.setText(String.format("<html><body><h2>%1$s</h2><br><br><br><p>%2$s</p></body></html>", textTitle, textBody), true);
    //     helper.setSubject(subject);

    //     sender.send(message);
    // }

    public void createFriendRequest(String sender, String receiver) {

    }

    public void addFriend(String sender, String receiver) {

    }

    public void removeFriendRequest(String sender, String receiver) {
        
    }
}