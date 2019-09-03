package onet.grupa.isrentalapplication.service.users;

import onet.grupa.isrentalapplication.domain.User;
import onet.grupa.isrentalapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
